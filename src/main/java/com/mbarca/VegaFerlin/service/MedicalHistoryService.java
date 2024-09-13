package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.model.MedicalHistory;
import com.mbarca.VegaFerlin.repository.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MedicalHistoryService {
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistory getMedicalHistoryByPatientId(Long patientId) {
        Optional<MedicalHistory> medicalHistoryOptional = medicalHistoryRepository.findByPatientId(patientId);
        if (medicalHistoryOptional.isPresent()) {
            return medicalHistoryOptional.get();
        } else {
            throw new NotFoundException("Historia clínica no encontrada");
        }
    }

    public void updateMedicalHistory(Long patientId, MedicalHistory newMedicalHistory) {
        medicalHistoryRepository.findByPatientId(patientId)
                .map(clinicalHistory -> {
                    clinicalHistory.setParents(newMedicalHistory.getParents());
                    clinicalHistory.setSiblings(newMedicalHistory.getSiblings());
                    clinicalHistory.setChildren(newMedicalHistory.getChildren());
                    clinicalHistory.setActualDiseaseHistory(newMedicalHistory.getActualDiseaseHistory());
                    clinicalHistory.setPathologicalHistory(newMedicalHistory.getPathologicalHistory());
                    clinicalHistory.setTraumaHistory(newMedicalHistory.getTraumaHistory());
                    clinicalHistory.setSurgeries(newMedicalHistory.getSurgeries());
                    clinicalHistory.setMedication(newMedicalHistory.getMedication());
                    clinicalHistory.setAllergies(newMedicalHistory.getAllergies());
                    clinicalHistory.setAlcohol(newMedicalHistory.isAlcohol());
                    clinicalHistory.setTobacco(newMedicalHistory.isTobacco());
                    clinicalHistory.setDrugs(newMedicalHistory.isDrugs());
                    clinicalHistory.setDrugsDetail(newMedicalHistory.getDrugsDetail());
                    return medicalHistoryRepository.save(clinicalHistory);
                })
                .orElseThrow(() -> new NotFoundException("Historia clínica no encontrada"));
    }
}
