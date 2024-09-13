package com.mbarca.VegaFerlin.controller;

import com.mbarca.VegaFerlin.dto.request.HealthQuestionnaireRequestDto;
import com.mbarca.VegaFerlin.dto.response.HealthQuestionnaireResponseDto;
import com.mbarca.VegaFerlin.mapper.HealthQuestionnaireMapper;
import com.mbarca.VegaFerlin.model.HealthQuestionnaire;
import com.mbarca.VegaFerlin.service.HealthQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/healthQuestionnaire")
@CrossOrigin
public class HealthQuestionnaireController {
    @Autowired
    private HealthQuestionnaireService healthQuestionnaireService;
    @GetMapping("/getByPatientId")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getByPatientId(@RequestParam Long patientId) {
        HealthQuestionnaire healthQuestionnaire = healthQuestionnaireService.getByPatientId(patientId);
        HealthQuestionnaireResponseDto healthQuestionnaireResponseDto = HealthQuestionnaireMapper.INSTANCE.toHealthQuestionnaireResponseDto(healthQuestionnaire);
        return ResponseEntity.status(HttpStatus.OK).body(healthQuestionnaireResponseDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateHealthQuestionnaire(@RequestParam Long patientId, @RequestBody HealthQuestionnaireRequestDto newHealthQuestionnaire) {
        try {
            HealthQuestionnaire healthQuestionnaire = HealthQuestionnaireMapper.INSTANCE.toHealthQuestionnaire(newHealthQuestionnaire);
            healthQuestionnaireService.updateHealthQuestionnaire(patientId, healthQuestionnaire);
            return ResponseEntity.status(HttpStatus.OK).body("Cuestionario médico editado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
