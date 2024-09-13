package com.mbarca.VegaFerlin.controller;

import com.mbarca.VegaFerlin.dto.request.DentalEvaluationRequestDto;
import com.mbarca.VegaFerlin.dto.response.DentalEvaluationResponseDto;
import com.mbarca.VegaFerlin.mapper.DentalEvaluationMapper;
import com.mbarca.VegaFerlin.model.DentalEvaluation;
import com.mbarca.VegaFerlin.service.DentalEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/dentalEvaluation")
@CrossOrigin
public class DentalEvaluationController {
    @Autowired
    private DentalEvaluationService dentalEvaluationService;

    @GetMapping("/getByPatientId")
    public ResponseEntity<?> getByPatientId(@RequestParam Long patientId) {
        DentalEvaluation dentalEvaluation = dentalEvaluationService.getDentalEvaluationByPatientId(patientId);
        DentalEvaluationResponseDto dentalEvaluationResponseDto = DentalEvaluationMapper.INSTANCE.toDto(dentalEvaluation);
        return ResponseEntity.status(HttpStatus.OK).body(dentalEvaluationResponseDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDentalEvaluation(@RequestParam Long patientId, @RequestBody DentalEvaluationRequestDto dentalEvaluationRequestDto) {
        try {
            DentalEvaluation dentalEvaluation = DentalEvaluationMapper.INSTANCE.toDentalEvaluation(dentalEvaluationRequestDto);
            dentalEvaluationService.updateDentalEvaluation(patientId, dentalEvaluation);
            return ResponseEntity.status(HttpStatus.OK).body("Evaluación dental editada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}

