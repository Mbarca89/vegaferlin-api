package com.mbarca.VegaFerlin.controller;

import com.mbarca.VegaFerlin.dto.request.LabsRequestDto;
import com.mbarca.VegaFerlin.dto.request.MedicalHistoryRequestDto;
import com.mbarca.VegaFerlin.dto.response.MedicalHistoryResponseDto;
import com.mbarca.VegaFerlin.mapper.LabsMapper;
import com.mbarca.VegaFerlin.mapper.MedicalHistoryMapper;
import com.mbarca.VegaFerlin.model.Labs;
import com.mbarca.VegaFerlin.model.MedicalHistory;
import com.mbarca.VegaFerlin.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicalHistory")
@CrossOrigin
public class MedicalHistoryController {
    @Autowired
    MedicalHistoryService medicalHistoryService;

    @GetMapping("/getByPatientId")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getByPatientId(@RequestParam Long patientId) {
        MedicalHistory medicalHistory = medicalHistoryService.getMedicalHistoryByPatientId(patientId);
        MedicalHistoryResponseDto medicalHistoryResponseDto = MedicalHistoryMapper.INSTANCE.toDto(medicalHistory);
        return ResponseEntity.status(HttpStatus.OK).body(medicalHistoryResponseDto);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateMedicalHistory(@RequestParam Long patientId, @RequestBody MedicalHistoryRequestDto medicalHistoryRequestDto) {
        try {
            MedicalHistory medicalHistory = MedicalHistoryMapper.INSTANCE.toMedicalHistory(medicalHistoryRequestDto);
            medicalHistoryService.updateMedicalHistory(patientId, medicalHistory);
            return ResponseEntity.status(HttpStatus.OK).body("Historia clínica editada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
