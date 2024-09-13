package com.mbarca.VegaFerlin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistoryRequestDto {
    private Long patientId;
    private String parents;
    private String siblings;
    private String children;
    private String actualDiseaseHistory;
    private String pathologicalHistory;
    private String traumaHistory;
    private String surgeries;
    private String medication;
    private String allergies;
    private boolean alcohol;
    private boolean tobacco;
    private boolean drugs;
    private String drugsDetail;
}
