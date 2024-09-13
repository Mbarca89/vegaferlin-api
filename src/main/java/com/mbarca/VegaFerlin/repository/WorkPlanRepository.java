package com.mbarca.VegaFerlin.repository;

import com.mbarca.VegaFerlin.model.WorkPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface WorkPlanRepository extends JpaRepository<WorkPlan, Long> {
    Optional<List<WorkPlan>> findByPatientId(Long patientId);
}
