package com.mbarca.VegaFerlin.repository;

import com.mbarca.VegaFerlin.model.Labs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabsRepository extends JpaRepository<Labs, Long> {
    Optional<Labs> findByPatientId(Long patientId);
}
