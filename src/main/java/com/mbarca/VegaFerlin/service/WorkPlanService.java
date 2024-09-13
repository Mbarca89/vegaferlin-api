package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.model.WorkPlan;
import com.mbarca.VegaFerlin.repository.WorkPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WorkPlanService {
    @Autowired
    WorkPlanRepository workPlanRepository;

    public void createWorkPlan(Long patientId, WorkPlan workPlan) {
        workPlanRepository.save(workPlan);
    }

    public List<WorkPlan> getWorkPlanById(Long patientId) {
        Optional<List<WorkPlan>> workPlanOptional = workPlanRepository.findByPatientId(patientId);
        if (workPlanOptional.isPresent()) {
            return workPlanOptional.get();
        } else {
            throw new NotFoundException("Plan de trabajo no encontrado");
        }
    }

    public void addStage(Long patientId, Long id, String stage) {
        workPlanRepository.findById(id)
                .map(workPlan -> {
                    List<String> stages = new ArrayList<>();
                    if(workPlan.getStages() != null) stages.addAll(workPlan.getStages());
                    stages.add(stage);
                    workPlan.setStages(stages);
                    return workPlanRepository.save(workPlan);
                })
                .orElseThrow(() -> new NotFoundException("Plan de trabajo no encontrado"));
    }

    public void updateStage(Long patientId, WorkPlan newWorkPlan, Long id) {
        Optional<WorkPlan> workPlanOptional = workPlanRepository.findById(id);
        if (workPlanOptional.isPresent()) {
            WorkPlan workPlan = workPlanOptional.get();
            workPlan.setStages(newWorkPlan.getStages());
            workPlanRepository.save(workPlan);
        } else {
            throw new NotFoundException("Plan de trabajo no encontrado");
        }
    }

    public void closeWorkPlan(Long patientId, WorkPlan newWorkPlan, Long id) {
        Optional<WorkPlan> workPlanOptional = workPlanRepository.findById(id);
        if (workPlanOptional.isPresent()) {
            WorkPlan workPlan = workPlanOptional.get();
            workPlan.setEndDate(new Date());
            workPlan.setStatus(newWorkPlan.getStatus());
            workPlanRepository.save(workPlan);
        } else {
            throw new NotFoundException("Plan de trabajo no encontrado");
        }
    }
}
