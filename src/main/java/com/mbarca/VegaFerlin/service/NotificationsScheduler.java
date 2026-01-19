package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Component
public class NotificationsScheduler {

    @Autowired
    private AppointmentService appointmentService;

    @Scheduled(cron = "0 00 10 * * *")
    public void sendAppointmentReminders() throws Exception {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        Date dateStart = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateEnd = Date.from(tomorrow.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());

        List<Appointment> appointments = appointmentService.getAppointments(dateStart, dateEnd);
        for (Appointment appointment : appointments) {
            String formattedTime = new SimpleDateFormat("HH:mm").format(appointment.getStartDate());

            String message = "!Hola " + appointment.getName() + "!\n" +
                    "Te recordamos tu turno de mañana a las " + formattedTime + " hs.\n" +
                    "Trabajo a realizar: " + appointment.getTitle() + ".\n" +
                    "De no poder asistir, por favor avisar con anticipación.";

            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:3001/ws/send"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString("{\"number\": \"" + appointment.getPhone() + "\",\"message\": \"" + message.replace("\n", "\\n") + "\"}"))
                        .version(HttpClient.Version.HTTP_1_1)
                        .build();
                HttpResponse<String> response;
                try (HttpClient http = HttpClient.newHttpClient()) {
                    response = http.send(request, HttpResponse.BodyHandlers.ofString());
                }
                if (response.statusCode() == 200) {
                    appointment.setMessageSent(true);
                    System.out.println(appointment.toString());
                    appointmentService.updateAppointment(appointment);
                }
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new Exception(e.getMessage());
            }
        }
    }
}

