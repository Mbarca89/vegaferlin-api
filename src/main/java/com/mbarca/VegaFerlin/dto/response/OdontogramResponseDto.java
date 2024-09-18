package com.mbarca.VegaFerlin.dto.response;

import com.mbarca.VegaFerlin.domain.Treatment;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OdontogramResponseDto {
    private Long id;
    private String odontogramJson;
    private Long patientId;
    private List<Treatment> treatments;
    private Date odontogramDate;
}