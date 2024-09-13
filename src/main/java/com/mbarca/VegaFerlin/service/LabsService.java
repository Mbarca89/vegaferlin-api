package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.model.Labs;
import com.mbarca.VegaFerlin.model.Patient;
import com.mbarca.VegaFerlin.repository.LabsRepository;
import com.mbarca.VegaFerlin.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class LabsService {

    @Autowired
    private LabsRepository labsRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public void uploadLab(Long patientId, MultipartFile[] files, String labType, Long labId) {
        Optional<Labs> optionalLabs = labsRepository.findById(labId);
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        String patientName;
        if(patientOptional.isPresent()) {
            patientName = patientOptional.get().getSurname() + "_" + patientOptional.get().getName();
            patientName = patientName.replaceAll("[^a-zA-Z0-9_-]", "_");

        }else {
            throw new RuntimeException("El paciente no existe!");
        }
        if (optionalLabs.isPresent()) {
            Labs labs = optionalLabs.get();
            String subDir = patientName + "/Laboratorios/" + labType;
            List<String> filePaths = fileStorageService.store(files, subDir);
            switch (labType) {
                case "Hemograma":
                    filePaths.addAll(labs.getHemogram());
                    labs.setHemogram(filePaths);
                    break;
                case "Glucemia":
                    filePaths.addAll(labs.getGlycemia());
                    labs.setGlycemia(filePaths);
                    break;
                case "Hemoglobina":
                    filePaths.addAll(labs.getHemoglobin());
                    labs.setHemoglobin(filePaths);
                    break;
                case "Uremia":
                    filePaths.addAll(labs.getUraemia());
                    labs.setUraemia(filePaths);
                    break;
                case "Coagulograma":
                    filePaths.addAll(labs.getCoagulagram());
                    labs.setCoagulagram(filePaths);
                    break;
                case "Orina":
                    filePaths.addAll(labs.getUrine());
                    labs.setUrine(filePaths);
                    break;
                case "antitetanus":
                    filePaths.addAll(labs.getAntitetanus());
                    labs.setAntitetanus(filePaths);
                    break;
                case "CTX":
                    filePaths.addAll(labs.getCtx());
                    labs.setCtx(filePaths);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de estudio inválido: " + labType);
            }
            labsRepository.save(labs);
        } else {
            throw new RuntimeException("No se encontró el laboratorio con ID " + labId);
        }
    }

    public Labs getAllLabsByPatientId(Long patientId) {
        Optional<Labs> labsOptional = labsRepository.findByPatientId(patientId);
        if(labsOptional.isPresent()) {
        return labsOptional.get();
        } else {
            throw new NotFoundException("Error al obtener las prácticas complementarias");
        }
    }

    public void updateLabs (Long patientId, Labs newLabs) {
        Optional<Labs> labsOptional = labsRepository.findByPatientId(patientId);
        if(labsOptional.isPresent()) {
            Labs labs = labsOptional.get();
            labs.setHemogramObservations(newLabs.getHemogramObservations());
            labs.setGlycemiaObservations(newLabs.getGlycemiaObservations());
            labs.setHemoglobinObservations(newLabs.getHemoglobinObservations());
            labs.setUraemiaObservations(newLabs.getUraemiaObservations());
            labs.setCoagulogramObservations(newLabs.getCoagulogramObservations());
            labs.setUrineObservations(newLabs.getUrineObservations());
            labs.setCtxObservations(newLabs.getCtxObservations());
            labsRepository.save(labs);
        } else {
            throw new NotFoundException("Error al obtener las prácticas complementarias");

        }
    }
}

