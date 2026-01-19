package com.mbarca.VegaFerlin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbarca.VegaFerlin.model.Appointment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class NotificationsScheduler {

    private final AppointmentService appointmentService;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public NotificationsScheduler(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    // 10:00 todos los dias
    @Scheduled(cron = "0 0 10 * * *")
    public void sendAppointmentReminders() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        Date dateStart = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateEnd = Date.from(tomorrow.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());

        List<Appointment> appointments = appointmentService.getAppointments(dateStart, dateEnd);

        if (appointments == null || appointments.isEmpty()) {
            log.info("[REMINDERS] No hay turnos para enviar (mañana: {}).", tomorrow);
            return;
        }

        log.info("[REMINDERS] Procesando {} turno(s) para mañana {}.", appointments.size(), tomorrow);

        for (Appointment appointment : appointments) {
            try {
                // ✅ No reenviar si ya fue enviado
                if (Boolean.TRUE.equals(appointment.getMessageSent())) {
                    log.info("[REMINDERS] Saltado (ya enviado). appointmentId={}, name={}, phone={}",
                            appointment.getId(), appointment.getName(), appointment.getPhone());
                    continue;
                }

                // Validaciones rápidas para evitar basura
                if (appointment.getPhone() == null || appointment.getPhone().isBlank()) {
                    log.warn("[REMINDERS] Sin teléfono. appointmentId={}, name={}", appointment.getId(), appointment.getName());
                    continue;
                }
                if (appointment.getStartDate() == null) {
                    log.warn("[REMINDERS] Sin startDate. appointmentId={}, name={}", appointment.getId(), appointment.getName());
                    continue;
                }

                String formattedTime = new SimpleDateFormat("HH:mm").format(appointment.getStartDate());

                // ✨ Mensaje más lindo
                String message =
                        "👋 *¡Hola " + safe(appointment.getName()) + "!*\n\n" +
                                "📅 *Recordatorio de turno*\n" +
                                "🕒 Mañana a las *" + formattedTime + " hs*\n" +
                                "🛠️ Trabajo: *" + safe(appointment.getTitle()) + "*\n\n" +
                                "✅ Te esperamos. Si no podés asistir, avisá con anticipación 🙏";

                // Armado de JSON seguro (sin concatenar strings)
                String bodyJson = objectMapper.writeValueAsString(Map.of(
                        "number", appointment.getPhone(),
                        "message", message
                ));

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:3001/ws/send"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(bodyJson))
                        .version(HttpClient.Version.HTTP_1_1)
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                int status = response.statusCode();
                String respBody = response.body();

                if (status >= 200 && status < 300) {
                    // 💾 Mejor: marcar enviado en DB por ID (evita problemas de entidad "suelta")
                    appointmentService.markMessageSent(appointment.getId());

                    log.info("[REMINDERS] ✅ Enviado. appointmentId={}, phone={}, status={}, response={}",
                            appointment.getId(), appointment.getPhone(), status, shrink(respBody));
                } else {
                    log.warn("[REMINDERS] ❌ Falló envío. appointmentId={}, phone={}, status={}, response={}",
                            appointment.getId(), appointment.getPhone(), status, shrink(respBody));
                }

            } catch (Exception e) {
                // No reventar todo el scheduler por 1 turno
                log.error("[REMINDERS] 💥 Error enviando. appointmentId={}, phone={}. {}",
                        appointment != null ? appointment.getId() : null,
                        appointment != null ? appointment.getPhone() : null,
                        e.getMessage(), e);
            }
        }

        log.info("[REMINDERS] Finalizó el proceso para mañana {}.", tomorrow);
    }

    // Helpers chiquitos para evitar nulls y logs gigantes
    private static String safe(String s) {
        return s == null ? "" : s.trim();
    }

    private static String shrink(String s) {
        if (s == null) return "";
        s = s.replace("\n", " ").replace("\r", " ").trim();
        return s.length() > 300 ? s.substring(0, 300) + "..." : s;
    }
}

