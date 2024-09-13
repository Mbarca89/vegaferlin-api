package com.mbarca.VegaFerlin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicalHistory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    private String parents;
    private String siblings;
    private String children;
    private String actualDiseaseHistory;
    private String pathologicalHistory;
    private String traumaHistory;
    private String surgeries;
    private String medication;
    private String allergies;
    private boolean alcohol;
    private boolean tobacco;
    private boolean drugs;
    private String drugsDetail;

}
