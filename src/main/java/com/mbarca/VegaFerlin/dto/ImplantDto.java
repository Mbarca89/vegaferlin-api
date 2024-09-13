package com.mbarca.VegaFerlin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImplantDto {
    private String location;
    private String brand;
    private String connection;
    private String platform;
    private int length;
    private int diameter;
    private int torque;
    private String stability;
    private String placement;
    private String instrumentalMethod;
    private int instrumentalFrom;
    private int instrumentalTo;
}
