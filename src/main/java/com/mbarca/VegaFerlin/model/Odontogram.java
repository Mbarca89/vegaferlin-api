package com.mbarca.VegaFerlin.model;

import com.mbarca.VegaFerlin.domain.Treatment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "odontogram")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Odontogram {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String odontogramJson;

    @ElementCollection
    @CollectionTable(name = "odontogram_treatments", joinColumns = @JoinColumn(name = "odontogram_id"))
    private List<Treatment> treatments;

    private Date odontogramDate;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
}

