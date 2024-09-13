package com.mbarca.VegaFerlin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkPlanResponseDto {
    private Long id;
    private Date startDate;
    private Date endDate;
    private String observations;
    private String status;
    private List<String> stages;
}
