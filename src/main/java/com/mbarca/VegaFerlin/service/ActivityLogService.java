package com.mbarca.VegaFerlin.service;

import com.mbarca.VegaFerlin.exceptions.NotFoundException;
import com.mbarca.VegaFerlin.model.ActivityLog;
import com.mbarca.VegaFerlin.repository.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityLogService {
    @Autowired
    ActivityLogRepository activityLogRepository;

    public List<ActivityLog> getByPatientId (Long patientId) {
        Optional<List<ActivityLog>> activityLogOptional = activityLogRepository.findByPatientIdOrderByTimestampDesc(patientId);
        if(activityLogOptional.isPresent()) {
            return activityLogOptional.get();
        } else {
            throw new NotFoundException("Historial de actividades no encontrado");
        }
    }
}
