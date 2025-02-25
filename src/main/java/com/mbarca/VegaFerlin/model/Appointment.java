package com.mbarca.VegaFerlin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Table(name = "appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String phone;
    @NotNull(message = "Debe ingresar la fecha de la cita")
    private Timestamp startDate;
    @NotNull(message = "Debe ingresar al fecha de finalización de la cita")
    private Timestamp endDate;
    private String title;
    @Column(columnDefinition = "boolean default false")
    private Boolean messageSent;
}
