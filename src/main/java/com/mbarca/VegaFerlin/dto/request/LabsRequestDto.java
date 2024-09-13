package com.mbarca.VegaFerlin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabsRequestDto {
    private Long patientId;
    private String hemogramObservations;
    private String glycemiaObservations;
    private String hemoglobinObservations;
    private String uraemiaObservations;
    private String coagulogramObservations;
    private String urineObservations;
    private String antitetanusObservations;
    private String ctxObservations;
}
