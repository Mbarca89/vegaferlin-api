package com.mbarca.VegaFerlin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dentalEvaluation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DentalEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    private boolean brush;
    private String brushFrequency;
    private boolean floss;
    private String flossFrequency;
    private boolean interdentalBrush;
    private String interdentalBrushFrequency;
    private String biotype;
    private String smile;
    private boolean verticalLoss;
    private boolean jawPosition;
    private boolean dispersion;
    private boolean wear;
    private String wearType;
    private String internalExam;
    private String externalExam;
}
