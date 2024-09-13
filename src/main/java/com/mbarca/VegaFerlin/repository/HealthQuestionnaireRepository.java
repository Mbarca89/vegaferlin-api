package com.mbarca.VegaFerlin.repository;

import com.mbarca.VegaFerlin.model.HealthQuestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface HealthQuestionnaireRepository extends JpaRepository<HealthQuestionnaire, Long> {
    Optional<HealthQuestionnaire> findByPatientId(Long patientId);
}
