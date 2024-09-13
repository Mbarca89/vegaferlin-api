package com.mbarca.VegaFerlin.dto.request;

import com.mbarca.VegaFerlin.dto.SurgicalInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurgicalProtocolRequestDto {
    private Long patientId;
    private String date;
    private String firstAssistant;
    private String secondAssistant;
    private String startTime;
    private String endTime;
    private String preMed;
    private String postMed;
    private String surgeryType;
    private String others;
    private boolean topMaxillary;
    private boolean jaw;
    private SurgicalInfoDto topMaxillaryInfo;
    private SurgicalInfoDto jawInfo;
}
