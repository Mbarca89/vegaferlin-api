package com.mbarca.VegaFerlin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "dentalPrediction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DentalPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ElementCollection
    @CollectionTable(name = "top_map", joinColumns = @JoinColumn(name = "dental_map_id"))
    @MapKeyColumn(name = "teeth_number")
    @Column(name = "condition")
    private Map<String, String> top;

    @ElementCollection
    @CollectionTable(name = "bottom_map", joinColumns = @JoinColumn(name = "dental_map_id"))
    @MapKeyColumn(name = "teeth_number")
    @Column(name = "condition")
    private Map<String, String> bottom;
}
