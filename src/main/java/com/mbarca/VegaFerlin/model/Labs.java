package com.mbarca.VegaFerlin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "labs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Labs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ElementCollection
    @CollectionTable(name = "hemogram_files", joinColumns = @JoinColumn(name = "labs_id"))
    @Column(name = "file_path")
    private List<String> hemogram;

    private String hemogramObservations;

    @ElementCollection
    @CollectionTable(name = "glycemia_files", joinColumns = @JoinColumn(name = "labs_id"))
    @Column(name = "file_path")
    private List<String> glycemia;

    private String glycemiaObservations;

    @ElementCollection
    @CollectionTable(name = "hemoglobin_files", joinColumns = @JoinColumn(name = "labs_id"))
    @Column(name = "file_path")
    private List<String> hemoglobin;

    private String hemoglobinObservations;

    @ElementCollection
    @CollectionTable(name = "uraemia_files", joinColumns = @JoinColumn(name = "labs_id"))
    @Column(name = "file_path")
    private List<String> uraemia;

    private String uraemiaObservations;

    @ElementCollection
    @CollectionTable(name = "coagulagram_files", joinColumns = @JoinColumn(name = "labs_id"))
    @Column(name = "file_path")
    private List<String> coagulagram;

    private String coagulogramObservations;

    @ElementCollection
    @CollectionTable(name = "urine_files", joinColumns = @JoinColumn(name = "labs_id"))
    @Column(name = "file_path")
    private List<String> urine;

    private String urineObservations;

    @ElementCollection
    @CollectionTable(name = "antitetanus_files", joinColumns = @JoinColumn(name = "labs_id"))
    @Column(name = "file_path")
    private List<String> antitetanus;

    private String antitetanusObservations;

    @ElementCollection
    @CollectionTable(name = "ctx_files", joinColumns = @JoinColumn(name = "labs_id"))
    @Column(name = "file_path")
    private List<String> ctx;

    private String ctxObservations;
}

