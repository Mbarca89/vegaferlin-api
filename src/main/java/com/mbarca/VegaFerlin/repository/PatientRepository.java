package com.mbarca.VegaFerlin.repository;

import com.mbarca.VegaFerlin.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p FROM Patient p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(p.surname) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Optional<List<Patient>> searchByName(@Param("searchTerm") String searchTerm);
}
