package com.mbarca.VegaFerlin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DentalEvaluationResponseDto {
    private Long id;
    private Long patientId;
    private boolean brush;
    private String brushFrequency;
    private boolean floss;
    private String flossFrequency;
    private boolean interdentalBrush;
    private String interdentalBrushFrequency;
    private String biotype;
    private String smile;
    private boolean verticalLoss;
    private boolean jawPosition;
    private boolean dispersion;
    private boolean wear;
    private String wearType;
    private String internalExam;
    private String externalExam;
}
