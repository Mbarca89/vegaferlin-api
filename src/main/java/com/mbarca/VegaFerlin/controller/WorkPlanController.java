package com.mbarca.VegaFerlin.controller;

import com.mbarca.VegaFerlin.dto.request.WorkPlanRequestDto;
import com.mbarca.VegaFerlin.dto.response.WorkPlanResponseDto;
import com.mbarca.VegaFerlin.mapper.WorkPlanMapper;
import com.mbarca.VegaFerlin.model.Patient;
import com.mbarca.VegaFerlin.model.WorkPlan;
import com.mbarca.VegaFerlin.service.PatientService;
import com.mbarca.VegaFerlin.service.WorkPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workPlan")
@CrossOrigin
public class WorkPlanController {
    @Autowired
    WorkPlanService workPlanService;
    @Autowired
    PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<?> createWorkPlan(@RequestBody WorkPlanRequestDto workPlanRequestDto) {
        WorkPlan workPlan = WorkPlanMapper.INSTANCE.toWorkPlan(workPlanRequestDto);
        Patient patient = patientService.getPatientById(workPlanRequestDto.getPatientId());
        workPlan.setPatient(patient);
        workPlanService.createWorkPlan(workPlanRequestDto.getPatientId(), workPlan);
        return ResponseEntity.status(HttpStatus.OK).body("Plan de trabajo creado correctamente");
    }

    @GetMapping("/getByPatientId")
    public ResponseEntity<?> getWorkPlanById(@RequestParam Long patientId) {
        List<WorkPlan> workPlans = workPlanService.getWorkPlanById(patientId);
        List<WorkPlanResponseDto> workPlanResponseDtos = workPlans.stream().map(WorkPlanMapper.INSTANCE::toDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(workPlanResponseDtos);
    }

    @PutMapping("/createStage")
    public ResponseEntity<?> createStage(@RequestParam Long patientId, @RequestParam Long id, @RequestBody String stage) {
        workPlanService.addStage(patientId,id, stage);
        return ResponseEntity.status(HttpStatus.OK).body("Etapa creada correctamente");
    }

    @PutMapping("/updateStage")
    public ResponseEntity<?> updateStage(@RequestParam Long id, @RequestBody WorkPlanRequestDto workPlanRequestDto) {
        WorkPlan workPlan = WorkPlanMapper.INSTANCE.toWorkPlan(workPlanRequestDto);
        workPlanService.updateStage(workPlanRequestDto.getPatientId(), workPlan, id);
        return ResponseEntity.status(HttpStatus.OK).body("Etapa editada correctamente");
    }

    @PutMapping("/closeWorkPlan")
    public ResponseEntity<?> closeWorkPlan(@RequestParam Long id, @RequestBody WorkPlanRequestDto workPlanRequestDto) {
        WorkPlan workPlan = WorkPlanMapper.INSTANCE.toWorkPlan(workPlanRequestDto);
        workPlanService.closeWorkPlan(workPlanRequestDto.getPatientId(), workPlan, id);
        return ResponseEntity.status(HttpStatus.OK).body("Plan de trabajo cerrado correctamente");
    }
}
