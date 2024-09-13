package com.mbarca.VegaFerlin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DentalPredictionResponseDto {
    private Map<String, String> top;
    private Map<String, String> bottom;
}
