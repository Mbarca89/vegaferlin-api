package com.mbarca.VegaFerlin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurgicalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zone;
    private String anaesthesia;
    private int incisionFrom;
    private int incisionTo;
    private String disposition;
    private String extension;
    private String regenerationObjective;
    private String elevationMethod;
    private int regenerationFrom;
    private int regenerationTo;
    private String membrane;
    private String sutureMaterial;
    private String technique;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "surgical_info_id")
    private List<Compensator> compensators;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "surgical_info_id")
    private List<Implant> implants;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "surgical_info_id")
    private List<Material> materials;
}
