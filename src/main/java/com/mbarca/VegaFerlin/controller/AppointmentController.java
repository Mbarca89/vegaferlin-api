package com.mbarca.VegaFerlin.controller;

import com.mbarca.VegaFerlin.dto.response.AppointmentResponseDto;
import com.mbarca.VegaFerlin.mapper.AppointmentMapper;
import com.mbarca.VegaFerlin.model.Appointment;
import com.mbarca.VegaFerlin.service.AppointmentService;
import com.mbarca.VegaFerlin.service.NotificationsScheduler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@CrossOrigin
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;
    @Autowired
    NotificationsScheduler notificationsScheduler;

    @GetMapping("/get")
    public ResponseEntity<?> getAllAppointments(@RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Date start = formatter.parse(startDate);
        Date end = formatter.parse(endDate);
        List<Appointment> appointments = appointmentService.getAppointments(start, end);
        List<AppointmentResponseDto> response = appointments.stream().map(AppointmentMapper.INSTANCE::toDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAppointment(@RequestBody @Valid Appointment appointment) {
        String response = appointmentService.createAppointment(appointment);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Long id) throws ParseException {
        Appointment appointment = appointmentService.getAppointmentById(id);
        AppointmentResponseDto response = AppointmentMapper.INSTANCE.toDto(appointment);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAppointment(@RequestBody @Valid Appointment appointment) {
        String response = appointmentService.updateAppointment(appointment);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("deleteById")
    public ResponseEntity<?> deleteAppointmentById(@RequestParam Long id) {
        String response = appointmentService.deleteAppointment(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @CrossOrigin
    @PostMapping("/resend")
    public ResponseEntity<String> resendMessagesHandler() {
        try {
            notificationsScheduler.sendAppointmentReminders();
            return ResponseEntity.status(HttpStatus.OK).body("Mensajes reenviados");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
