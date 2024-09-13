package com.mbarca.VegaFerlin.repository;

import com.mbarca.VegaFerlin.model.DentalEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DentalEvaluationRepository extends JpaRepository<DentalEvaluation, Long> {
    Optional<DentalEvaluation> findByPatientId(Long patientId);
}
