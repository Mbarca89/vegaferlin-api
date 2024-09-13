package com.mbarca.VegaFerlin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbarca.VegaFerlin.domain.Images;
import com.mbarca.VegaFerlin.dto.request.PatientRequestDto;
import com.mbarca.VegaFerlin.dto.response.PatientListResponseDto;
import com.mbarca.VegaFerlin.dto.response.PatientResponseDto;
import com.mbarca.VegaFerlin.exceptions.RepositoryException;
import com.mbarca.VegaFerlin.mapper.PatientMapper;
import com.mbarca.VegaFerlin.model.*;
import com.mbarca.VegaFerlin.repository.ActivityLogRepository;
import com.mbarca.VegaFerlin.service.*;
import com.mbarca.VegaFerlin.utils.ImageCompressor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/patients")
@CrossOrigin
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    ImageCompressor imageCompressor;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createPatient(@RequestParam(value = "file", required = false) MultipartFile file,
                                           @Valid @RequestParam("patient") String patientJson) throws Exception {
        PatientRequestDto patientRequestDto = new ObjectMapper().readValue(patientJson, PatientRequestDto.class);
        Patient patient = PatientMapper.INSTANCE.toPatient(patientRequestDto);
        Images images;
        if (file != null && !file.isEmpty()) {
            images = imageCompressor.compressImage(file.getBytes(), false, patient.getName() + patient.getSurname(), "");
            patient.setImage(images.getThumbnail());
        }
        patientService.createPatient(patient);

        return ResponseEntity.status(HttpStatus.OK).body("Paciente creado correctamente");
    }

    @GetMapping("/getPatients")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getPatients() {
        List<PatientListResponseDto> patients = patientService.getPatients().stream().map(PatientMapper.INSTANCE::toPatientListResponseDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }

    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getPatientById(@RequestParam Long patientId) {
        PatientResponseDto patient = PatientMapper.INSTANCE.toPatientResponseDto(patientService.getPatientById(patientId));
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getByName(@RequestParam String searchTerm) throws RepositoryException {
        List<PatientListResponseDto> patients = patientService.getPatientsByName(searchTerm).stream().map(PatientMapper.INSTANCE::toPatientListResponseDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }
    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deletePatientById(@RequestParam Long id) {
        String response = patientService.deletePatient(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePatient(@RequestParam(value = "file", required = false) MultipartFile file,
                                           @Valid @RequestParam("patient") String patientJson) throws Exception {
        PatientRequestDto patientRequestDto = new ObjectMapper().readValue(patientJson, PatientRequestDto.class);
        Patient patient = PatientMapper.INSTANCE.toPatient(patientRequestDto);
        Images images;
        if (file != null && !file.isEmpty()) {
            images = imageCompressor.compressImage(file.getBytes(), false, patient.getName() + patient.getSurname(), "");
            patient.setImage(images.getThumbnail());
        }
        patientService.updatePatient(patient, patient.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Datos del paciente editados correctamente");
    }
}
