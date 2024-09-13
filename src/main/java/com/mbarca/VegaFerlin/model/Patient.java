package com.mbarca.VegaFerlin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private byte[] image;
    @Column(nullable = false)
    @NotBlank(message = "Debe especificar el profesional a cargo.")
    private String inChargeOf;
    @Column(nullable = false)
    @NotNull(message = "Debe especificar el ID del profesional a cargo.")
    private Long inChargeOfId;
    @Column(nullable = false)
    @NotBlank(message = "Ingrese el nombre")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "Ingrese el apellido")
    private String surname;
    @Column(nullable = false)
    @NotBlank(message = "Debe especificar el tipo de documento")
    private String docType;
    @Column(nullable = false)
    @NotNull(message = "Ingrese el número de documento")
    private Long doc;
    @Column(nullable = false)
    @NotBlank(message = "Ingrese el género")
    private String gender;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Ingrese la fecha de nacimiento")
    private Date birth;
    @Column(nullable = false)
    @NotBlank(message = "Ingrese la nacionalidad")
    private String nationality;
    @Column(nullable = false)
    @NotBlank(message = "Ingrese el estado civil")
    private String civilState;
    @Column(nullable = false)
    @NotBlank(message = "Ingrese el país")
    private String country;
    @Column(nullable = false)
    @NotBlank(message = "Ingrese la provincia")
    private String state;
    @Column(nullable = false)
    @NotBlank(message = "Ingrese la ciudad")
    private String city;
    @Column(nullable = false)
    @NotBlank(message = "Ingrese la dirección")
    private String address;
    private String derivedBy;
    private Long phone;
    private String email;
    private String occupation;
    private String studies;
    private String workAddress;
    private String workingHours;
    private String social;
    private Long socialNumber;
    private String observations;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityLog> activityLogs = new ArrayList<>();
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private DentalEvaluation dentalEvaluation;
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private DentalPrediction dentalPrediction;
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Gallery gallery;
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private HealthQuestionnaire healthQuestionnaire;
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Labs labs;
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private MedicalHistory medicalHistory;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkPlan> workPlans = new ArrayList<>();
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurgicalProtocol> surgicalProtocols = new ArrayList<>();
}
