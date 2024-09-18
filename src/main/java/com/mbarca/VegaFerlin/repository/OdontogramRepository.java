package com.mbarca.VegaFerlin.repository;

import com.mbarca.VegaFerlin.model.Odontogram;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OdontogramRepository extends JpaRepository<Odontogram, Long> {
    Optional<List<Odontogram>> findByPatientId(Long patientId);
    Optional<Odontogram> findTopByPatientIdOrderByOdontogramDateDesc(Long patientId);
}