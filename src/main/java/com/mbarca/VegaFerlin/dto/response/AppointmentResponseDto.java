package com.mbarca.VegaFerlin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDto {
    private Long id;
    private String name;
    private String phone;
    private Timestamp start;
    private Timestamp end;
    private String title;
    private Boolean messageSent;
}
