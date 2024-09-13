package com.mbarca.VegaFerlin.dto.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabsResponseDto {
    private Long id;
    private String patientId;
    private List<String> hemogram;
    private String hemogramObservations;
    private List<String> glycemia;
    private String glycemiaObservations;
    private List<String> hemoglobin;
    private String hemoglobinObservations;
    private List<String> uraemia;
    private String uraemiaObservations;
    private List<String> coagulagram;
    private String coagulogramObservations;
    private List<String> urine;
    private String urineObservations;
    private List<String> antitetanus;
    private String antitetanusObservations;
    private List<String> ctx;
    private String ctxObservations;
}
