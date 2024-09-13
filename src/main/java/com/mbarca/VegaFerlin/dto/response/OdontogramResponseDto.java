package com.mbarca.VegaFerlin.dto.response;

import lombok.Data;

@Data
public class OdontogramResponseDto {
    private Long id;
    private String odontogramJson;
    private Long patientId;
}