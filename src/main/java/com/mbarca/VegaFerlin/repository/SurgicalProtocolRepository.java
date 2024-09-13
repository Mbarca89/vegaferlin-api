package com.mbarca.VegaFerlin.repository;

import com.mbarca.VegaFerlin.model.SurgicalProtocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SurgicalProtocolRepository extends JpaRepository<SurgicalProtocol, Long> {
    Optional<List<SurgicalProtocol>> findByPatientId(Long patientId);
}
