package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.model.DentalPrediction;
import com.mbarca.VegaFerlin.repository.DentalPredictionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DentalPredictionService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private DentalPredictionRepository dentalPredictionRepository;
    public DentalPrediction getDentalMapByPatientId(Long patientId) {
        Optional<DentalPrediction> dentalPredictionOptional = dentalPredictionRepository.findByPatientId(patientId);
        if (dentalPredictionOptional.isPresent()) {
            return dentalPredictionOptional.get();
        } else {
            throw new NotFoundException("Pronóstico por pieza no encontrado");
        }
    }

    @Transactional
    public void updateDentalPrediction(Long patientId, DentalPrediction newDentalPrediction) {
        try {
            Optional<DentalPrediction> dentalPredictionOptional = dentalPredictionRepository.findByPatientId(patientId);
            if (dentalPredictionOptional.isPresent()) {
                DentalPrediction dentalPrediction = dentalPredictionOptional.get();
                if (newDentalPrediction.getTop() != null) {
                    dentalPrediction.setTop(newDentalPrediction.getTop());
                }
                if (newDentalPrediction.getBottom() != null) {
                    dentalPrediction.setBottom(newDentalPrediction.getBottom());
                }

                dentalPredictionRepository.save(dentalPrediction);
                entityManager.flush();
            } else {
                throw new NotFoundException("Pronóstico por pieza no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el pronóstico por pieza", e);
        }
    }
}
