package com.mbarca.VegaFerlin.repository;

import com.mbarca.VegaFerlin.model.DentalPrediction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DentalPredictionRepository extends JpaRepository<DentalPrediction, Long> {
    Optional<DentalPrediction> findByPatientId(Long patientId);
}

