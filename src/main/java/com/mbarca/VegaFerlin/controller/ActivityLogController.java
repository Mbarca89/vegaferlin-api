package com.mbarca.VegaFerlin.controller;

import com.mbarca.VegaFerlin.dto.response.ActivityLogResponseDto;
import com.mbarca.VegaFerlin.mapper.ActivityLogMapper;
import com.mbarca.VegaFerlin.model.ActivityLog;
import com.mbarca.VegaFerlin.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/activityLog")
@CrossOrigin
public class ActivityLogController {
    @Autowired
    ActivityLogService activityLogService;

    @GetMapping("/getByPatientId")
    public ResponseEntity<?> getByPatientId(@RequestParam Long patientId) {
        List<ActivityLog> activityLog = activityLogService.getByPatientId(patientId);
        List<ActivityLogResponseDto> activityLogResponseDto = activityLog.stream().map(ActivityLogMapper.INSTANCE::toDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(activityLogResponseDto);
    }
}
