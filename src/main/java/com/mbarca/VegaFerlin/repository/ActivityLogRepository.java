package com.mbarca.VegaFerlin.repository;

import com.mbarca.VegaFerlin.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    Optional<List<ActivityLog>> findByPatientIdOrderByTimestampDesc(Long patientId);
}
