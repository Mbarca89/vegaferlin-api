package com.mbarca.VegaFerlin.mapper;

import com.mbarca.VegaFerlin.dto.response.AppointmentResponseDto;
import com.mbarca.VegaFerlin.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Mapping(source = "startDate", target = "start")
    @Mapping(source = "endDate", target = "end")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "messageSent", target = "messageSent")
    AppointmentResponseDto toDto (Appointment appointment);
}
