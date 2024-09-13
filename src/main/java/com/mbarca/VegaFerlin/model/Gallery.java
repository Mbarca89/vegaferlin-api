package com.mbarca.VegaFerlin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "gallery")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    @ElementCollection
    private List<String> extraoralFront;
    @ElementCollection
    private List<String> extraoralFront_thumb;
    @ElementCollection
    private List<String> extraoralFront_HD;
    @ElementCollection
    private List<String> extraoralMax;
    @ElementCollection
    private List<String> extraoralMax_thumb;
    @ElementCollection
    private List<String> extraoralMax_HD;
    @ElementCollection
    private List<String> extraoralLeft;
    @ElementCollection
    private List<String> extraoralLeft_thumb;
    @ElementCollection
    private List<String> extraoralLeft_HD;
    @ElementCollection
    private List<String> extraoralRight;
    @ElementCollection
    private List<String> extraoralRight_thumb;
    @ElementCollection
    private List<String> extraoralRight_HD;
    @ElementCollection
    private List<String> intraoralFront;
    @ElementCollection
    private List<String> intraoralFront_thumb;
    @ElementCollection
    private List<String> intraoralFront_HD;
    @ElementCollection
    private List<String> intraoralBlackBackground;
    @ElementCollection
    private List<String> intraoralBlackBackground_thumb;
    @ElementCollection
    private List<String> intraoralBlackBackground_HD;
    @ElementCollection
    private List<String> intraoralLeft;
    @ElementCollection
    private List<String> intraoralLeft_thumb;
    @ElementCollection
    private List<String> intraoralLeft_HD;
    @ElementCollection
    private List<String> intraoralRight;
    @ElementCollection
    private List<String> intraoralRight_thumb;
    @ElementCollection
    private List<String> intraoralRight_HD;
    @ElementCollection
    private List<String> arcTop;
    @ElementCollection
    private List<String> arcTop_thumb;
    @ElementCollection
    private List<String> arcTop_HD;
    @ElementCollection
    private List<String> arcBottom;
    @ElementCollection
    private List<String> arcBottom_thumb;
    @ElementCollection
    private List<String> arcBottom_HD;
    @ElementCollection
    private List<String> oclusal;
    @ElementCollection
    private List<String> oclusal_thumb;
    @ElementCollection
    private List<String> oclusal_HD;
    @ElementCollection
    private List<String> vestibular;
    @ElementCollection
    private List<String> vestibular_thumb;
    @ElementCollection
    private List<String> vestibular_HD;
    @ElementCollection
    private List<String> panoramic;
    @ElementCollection
    private List<String> panoramic_thumb;
    @ElementCollection
    private List<String> panoramic_HD;
    @ElementCollection
    private List<String> xray;
    @ElementCollection
    private List<String> xray_thumb;
    @ElementCollection
    private List<String> xray_HD;
}
