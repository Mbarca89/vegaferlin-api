package com.mbarca.VegaFerlin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbarca.VegaFerlin.dto.response.DentalPredictionResponseDto;
import com.mbarca.VegaFerlin.mapper.DentalPredictionMapper;
import com.mbarca.VegaFerlin.model.DentalPrediction;
import com.mbarca.VegaFerlin.service.DentalPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/dentalPrediction")
@CrossOrigin
public class DentalPredictionController {

    @Autowired
    private DentalPredictionService dentalPredictionService;

    @GetMapping("/getByPatientId")
    public ResponseEntity<?> getDentalMapById(@RequestParam Long patientId) {
        DentalPrediction dentalPrediction = dentalPredictionService.getDentalMapByPatientId(patientId);
        DentalPredictionResponseDto dentalPredictionResponseDto = DentalPredictionMapper.INSTANCE.toDto(dentalPrediction);
        return ResponseEntity.status(HttpStatus.OK).body(dentalPredictionResponseDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDentalPrediction(@RequestParam Long patientId, @RequestBody Map<String, Object> newDentalPrediction) {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> topMap = mapper.convertValue(newDentalPrediction.get("top"), Map.class);
        Map<String, String> bottomMap = mapper.convertValue(newDentalPrediction.get("bottom"), Map.class);

        DentalPrediction dentalPrediction = new DentalPrediction();
        dentalPrediction.setTop(topMap);
        dentalPrediction.setBottom(bottomMap);

        dentalPredictionService.updateDentalPrediction(patientId, dentalPrediction);

        return ResponseEntity.status(HttpStatus.OK).body("Pronóstico por pieza editado correctamente.");
    }
}
