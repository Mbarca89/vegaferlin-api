package com.mbarca.VegaFerlin.controller;

import com.mbarca.VegaFerlin.dto.request.SurgicalProtocolRequestDto;
import com.mbarca.VegaFerlin.dto.request.WorkPlanRequestDto;
import com.mbarca.VegaFerlin.dto.response.SurgicalProtocolResponseDto;
import com.mbarca.VegaFerlin.dto.response.WorkPlanResponseDto;
import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.mapper.SurgicalProtocolMapper;
import com.mbarca.VegaFerlin.mapper.WorkPlanMapper;
import com.mbarca.VegaFerlin.model.Patient;
import com.mbarca.VegaFerlin.model.SurgicalProtocol;
import com.mbarca.VegaFerlin.model.WorkPlan;
import com.mbarca.VegaFerlin.service.PatientService;
import com.mbarca.VegaFerlin.service.SurgicalProtocolService;
import com.mbarca.VegaFerlin.service.WorkPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surgicalProtocol")
@CrossOrigin
public class SurgicalProtocolController {
    @Autowired
    SurgicalProtocolService surgicalProtocolService;
    @Autowired
    PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<?> createSurgicalProtocol(@RequestBody SurgicalProtocolRequestDto surgicalProtocolRequestDto) {
        SurgicalProtocol surgicalProtocol = SurgicalProtocolMapper.INSTANCE.toEntity(surgicalProtocolRequestDto);
        Patient patient = patientService.getPatientById(surgicalProtocolRequestDto.getPatientId());
        surgicalProtocol.setPatient(patient);
        surgicalProtocolService.createSurgicalProtocol(surgicalProtocolRequestDto.getPatientId(), surgicalProtocol);
        return ResponseEntity.status(HttpStatus.OK).body("Protocolo quirúrgico creado correctamente");
    }

    @GetMapping("/getByPatientId")
    public ResponseEntity<?> getSurgicalProtocolByPatientId(@RequestParam Long patientId) {
        List<SurgicalProtocol> surgicalProtocols = surgicalProtocolService.getSurgicalProtocolByPatientId(patientId);
        List<SurgicalProtocolResponseDto> surgicalProtocolResponseDtos = surgicalProtocols.stream().map(SurgicalProtocolMapper.INSTANCE::toResponseDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(surgicalProtocolResponseDtos);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getSurgicalProtocolById(@RequestParam Long id) {
        SurgicalProtocol surgicalProtocol = surgicalProtocolService.getSurgicalProtocolById(id);
        SurgicalProtocolResponseDto surgicalProtocolResponseDtos = SurgicalProtocolMapper.INSTANCE.toResponseDTO(surgicalProtocol);
        return ResponseEntity.status(HttpStatus.OK).body(surgicalProtocolResponseDtos);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSurgicalProtocol(@RequestParam Long id, @RequestBody SurgicalProtocolRequestDto dto) {
        SurgicalProtocol existing = surgicalProtocolService.getSurgicalProtocolById(id);
        if (existing == null) {
            throw new NotFoundException("Protocolo quirúrgico no encontrado");
        }
        SurgicalProtocol updated = SurgicalProtocolMapper.INSTANCE.toEntity(dto);
        updated.setId(existing.getId());
        updated.setPatient(existing.getPatient());
        surgicalProtocolService.updateSurgicalProtocol(updated);
        return ResponseEntity.ok("Protocolo quirúrgico actualizado correctamente");
    }
}
