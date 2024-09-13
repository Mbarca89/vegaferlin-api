package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.model.DentalEvaluation;
import com.mbarca.VegaFerlin.repository.DentalEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DentalEvaluationService {
    @Autowired
    private DentalEvaluationRepository dentalEvaluationRepository;

    public DentalEvaluation getDentalEvaluationByPatientId(Long patientId) {
        Optional<DentalEvaluation> dentalEvaluationOptional = dentalEvaluationRepository.findByPatientId(patientId);
        if (dentalEvaluationOptional.isPresent()) {
            return dentalEvaluationOptional.get();
        } else {
            throw new NotFoundException("Evaluación dental no encontrada");
        }
    }

    public void updateDentalEvaluation(Long patientId, DentalEvaluation newDentalEvaluation) {
        Optional<DentalEvaluation> dentalEvaluationOptional = dentalEvaluationRepository.findByPatientId(patientId);
        if (dentalEvaluationOptional.isPresent()) {
            DentalEvaluation dentalEvaluation = dentalEvaluationOptional.get();
            dentalEvaluation.setBrush(newDentalEvaluation.isBrush());
            dentalEvaluation.setBrushFrequency(newDentalEvaluation.getBrushFrequency());
            dentalEvaluation.setFloss(newDentalEvaluation.isFloss());
            dentalEvaluation.setFlossFrequency(newDentalEvaluation.getFlossFrequency());
            dentalEvaluation.setInterdentalBrush(newDentalEvaluation.isInterdentalBrush());
            dentalEvaluation.setInterdentalBrushFrequency(newDentalEvaluation.getInterdentalBrushFrequency());
            dentalEvaluation.setBiotype(newDentalEvaluation.getBiotype());
            dentalEvaluation.setSmile(newDentalEvaluation.getSmile());
            dentalEvaluation.setVerticalLoss(newDentalEvaluation.isVerticalLoss());
            dentalEvaluation.setJawPosition(newDentalEvaluation.isJawPosition());
            dentalEvaluation.setDispersion(newDentalEvaluation.isDispersion());
            dentalEvaluation.setWear(newDentalEvaluation.isWear());
            dentalEvaluation.setWearType(newDentalEvaluation.getWearType());
            dentalEvaluation.setInternalExam(newDentalEvaluation.getInternalExam());
            dentalEvaluation.setExternalExam(newDentalEvaluation.getExternalExam());
            dentalEvaluationRepository.save(dentalEvaluation);
        } else {
            throw new NotFoundException("Evaluación dental no encontrada");
        }
    }
}

