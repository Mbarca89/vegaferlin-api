package com.mbarca.VegaFerlin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "surgicalProtocol")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurgicalProtocol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private String date;
    private String firstAssistant;
    private String secondAssistant;
    private String startTime;
    private String endTime;
    private String preMed;
    private String postMed;
    private String surgeryType;
    private String others;
    private boolean topMaxillary;
    private boolean jaw;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "top_maxillary_info_id")
    private SurgicalInfo topMaxillaryInfo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "jaw_info_id")
    private SurgicalInfo jawInfo;
}

