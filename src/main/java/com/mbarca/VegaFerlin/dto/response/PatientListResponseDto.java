package com.mbarca.VegaFerlin.dto.response;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientListResponseDto {
    private Long id;
    private String inChargeOf;
    private String name;
    private String surname;
    private String docType;
    private Long doc;
    private String gender;
    private Long phone;
}
