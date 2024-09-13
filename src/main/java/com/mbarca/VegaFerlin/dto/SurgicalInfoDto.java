package com.mbarca.VegaFerlin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurgicalInfoDto {
    private String zone;
    private String anaesthesia;
    private int incisionFrom;
    private int incisionTo;
    private String disposition;
    private String extension;
    private List<CompensatorDto> compensators;
    private List<ImplantDto> implants;
    private List<MaterialDto> materials;
    private String regenerationObjective;
    private String elevationMethod;
    private int regenerationFrom;
    private int regenerationTo;
    private String membrane;
    private String sutureMaterial;
    private String technique;
}

