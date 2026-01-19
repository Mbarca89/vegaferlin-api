package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.model.Appointment;
import com.mbarca.VegaFerlin.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    public String createAppointment (Appointment appointment) {
        appointmentRepository.save(appointment);
        return "Cita agendada correctamente!";
    }

    public String deleteAppointment (Long id) {
        appointmentRepository.deleteById(id);
        return "Cita eliminada correctamente";
    }

    public List<Appointment> getAppointments (Date dateStart, Date dateEnd) {
        return appointmentRepository.findAllByStartDateBetween(dateStart, dateEnd);
    }

    public Appointment getAppointmentById (Long id) {
        return appointmentRepository.findById(id).orElseThrow( () -> new NotFoundException("El evento no existe"));
    }

    public String updateAppointment (Appointment newAppointment) {
        Appointment appointment = appointmentRepository.findById(newAppointment.getId()).orElseThrow(() -> new NotFoundException("Cita no encontrada"));
        appointment.setEndDate(newAppointment.getEndDate());
        appointment.setStartDate(newAppointment.getStartDate());
        appointment.setTitle(newAppointment.getTitle());
        appointment.setName(newAppointment.getName());
        appointment.setPhone(newAppointment.getPhone());
        appointment.setMessageSent(newAppointment.getMessageSent());
        appointmentRepository.save(appointment);
        return "Cita modificada correctamente!";
    }

    @Transactional
    public void markMessageSent(Long appointmentId) {
        Appointment a = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        a.setMessageSent(true);
    }
}
