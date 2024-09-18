package com.mbarca.VegaFerlin.controller;

import com.mbarca.VegaFerlin.dto.request.OdontogramRequestDto;
import com.mbarca.VegaFerlin.dto.response.OdontogramListResponseDto;
import com.mbarca.VegaFerlin.dto.response.OdontogramResponseDto;
import com.mbarca.VegaFerlin.mapper.OdontogramMapper;
import com.mbarca.VegaFerlin.model.Odontogram;
import com.mbarca.VegaFerlin.model.Patient;
import com.mbarca.VegaFerlin.service.OdontogramService;
import com.mbarca.VegaFerlin.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/odontogram")
@CrossOrigin
public class OdontogramController {

    @Autowired
    OdontogramService odontogramService;
    @Autowired
    PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<?> createOdontogram (@RequestBody OdontogramRequestDto odontogramRequestDto) {
        Odontogram odontogram = OdontogramMapper.INSTANCE.toOdontogram(odontogramRequestDto);
        Patient patient = patientService.getPatientById(odontogramRequestDto.getPatientId());
        odontogram.setPatient(patient);
        odontogramService.createOdontogram(patient.getId(), odontogram);
        return ResponseEntity.status(HttpStatus.OK).body("Odontograma guardado correctamente");
    }

    @GetMapping("getById")
    public ResponseEntity<?> getOdontogramById (@RequestParam Long id) {
        Odontogram odontogram = odontogramService.getOdontogramById(id);
        OdontogramResponseDto odontogramResponseDto = OdontogramMapper.INSTANCE.toDto(odontogram);
        return ResponseEntity.status(HttpStatus.OK).body(odontogramResponseDto);
    }

    @GetMapping("getLast")
    public ResponseEntity<?> getLastOdontogram (@RequestParam Long patientId) {
        Odontogram odontogram = odontogramService.getLastOdontogram(patientId);
        OdontogramResponseDto odontogramResponseDto = OdontogramMapper.INSTANCE.toDto(odontogram);
        return ResponseEntity.status(HttpStatus.OK).body(odontogramResponseDto);
    }

    @GetMapping("getOdontograms")
    public ResponseEntity<?> getOdontograms (@RequestParam Long patientId) {
        List<Odontogram> odontograms = odontogramService.getOdontrogramsByPatientId(patientId);
        List<OdontogramListResponseDto> odontogramListResponseDto = odontograms.stream().map(OdontogramMapper.INSTANCE::toListDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(odontogramListResponseDto);
    }
}
