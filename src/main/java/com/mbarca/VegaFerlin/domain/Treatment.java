package com.mbarca.VegaFerlin.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Treatment {
    private String code;
    private String description;
    private String piece;
    private String faces;
    private String status;
}
