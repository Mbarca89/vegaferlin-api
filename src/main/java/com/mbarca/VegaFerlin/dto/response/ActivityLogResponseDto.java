package com.mbarca.VegaFerlin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityLogResponseDto {
    String activity;
    String username;
    LocalDateTime timestamp;
}
