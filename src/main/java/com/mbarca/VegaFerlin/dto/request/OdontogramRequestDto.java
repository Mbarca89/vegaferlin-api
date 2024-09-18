package com.mbarca.VegaFerlin.dto.request;

import com.mbarca.VegaFerlin.domain.Treatment;
import lombok.Data;

import java.util.List;

@Data
public class OdontogramRequestDto {
    private String odontogramJson;
    private Long patientId;
    private List<Treatment> treatments;
}
