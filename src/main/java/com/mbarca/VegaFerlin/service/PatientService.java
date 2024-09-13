package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.exceptions.RepositoryException;
import com.mbarca.VegaFerlin.model.*;
import com.mbarca.VegaFerlin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    ActivityLogRepository activityLogRepository;
    @Autowired
    DentalEvaluationRepository dentalEvaluationRepository;
    @Autowired
    DentalPredictionRepository dentalPredictionRepository;
    @Autowired
    GalleryRepository galleryRepository;
    @Autowired
    HealthQuestionnaireRepository healthQuestionnaireRepository;
    @Autowired
    LabsRepository labsRepository;
    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;

    public Patient createPatient(Patient patient) {
        Patient savedPatient = patientRepository.save(patient);

        DentalEvaluation dentalEvaluation = new DentalEvaluation();
        dentalEvaluation.setPatient(savedPatient);
        dentalEvaluationRepository.save(dentalEvaluation);

        DentalPrediction dentalPrediction = new DentalPrediction();
        dentalPrediction.setPatient(savedPatient);
        dentalPredictionRepository.save(dentalPrediction);

        Gallery gallery = new Gallery();
        gallery.setPatient(savedPatient);
        galleryRepository.save(gallery);

        HealthQuestionnaire healthQuestionnaire = new HealthQuestionnaire();
        healthQuestionnaire.setPatient(savedPatient);
        healthQuestionnaireRepository.save(healthQuestionnaire);

        Labs labs = new Labs();
        labs.setPatient(savedPatient);
        labsRepository.save(labs);

        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setPatient(savedPatient);
        medicalHistoryRepository.save(medicalHistory);

        return savedPatient;
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new NotFoundException("Paciente no encontrado"));
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> getPatientsByName (String searchTerm) throws RepositoryException {
        Optional<List<Patient>> patientsOptional = patientRepository.searchByName(searchTerm);
        if (patientsOptional.isPresent()) {
            return patientsOptional.get();
        }
        else {
            throw new RepositoryException("Hubo un error al obtener los pacientes");
        }
    }

    public String deletePatient(Long id) {
        patientRepository.deleteById(id);
        return "Paciente eliminado correctamente";
    }

    public void updatePatient(Patient newPatient, Long patientId) {
        patientRepository.findById(patientId).map(patient -> {
            patient.setName(newPatient.getName());
            patient.setSurname(newPatient.getSurname());
            patient.setDocType(newPatient.getDocType());
            patient.setDoc(newPatient.getDoc());
            patient.setGender(newPatient.getGender());
            patient.setBirth(newPatient.getBirth());
            patient.setNationality(newPatient.getNationality());
            patient.setCivilState(newPatient.getCivilState());
            patient.setCountry(newPatient.getCountry());
            patient.setState(newPatient.getState());
            patient.setCity(newPatient.getCity());
            patient.setAddress(newPatient.getAddress());
            patient.setDerivedBy(newPatient.getDerivedBy());
            patient.setPhone(newPatient.getPhone());
            patient.setEmail(newPatient.getEmail());
            patient.setOccupation(newPatient.getOccupation());
            patient.setStudies(newPatient.getStudies());
            patient.setWorkAddress(newPatient.getWorkAddress());
            patient.setWorkingHours(newPatient.getWorkingHours());
            patient.setSocial(newPatient.getSocial());
            patient.setSocialNumber(newPatient.getSocialNumber());
            patient.setObservations(newPatient.getObservations());
            if (newPatient.getImage() != null) {
                patient.setImage(newPatient.getImage());
            }
            return patientRepository.save(patient);
        }).orElseThrow(() -> new RuntimeException("Paciente no encontrado!"));
    }

}
