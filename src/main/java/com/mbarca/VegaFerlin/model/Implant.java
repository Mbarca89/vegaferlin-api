package com.mbarca.VegaFerlin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Implant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
