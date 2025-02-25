package com.mbarca.VegaFerlin.repository;

import com.mbarca.VegaFerlin.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByStartDateBetween(Date startDate, Date endDate);
}
