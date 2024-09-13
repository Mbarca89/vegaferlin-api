package com.mbarca.VegaFerlin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkPlanRequestDto {
    private Long patientId;
    private Date startDate;
    private String observations;
    private String status;
    private List<String> stages;
}
