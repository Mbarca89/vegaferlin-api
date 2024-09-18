package com.mbarca.VegaFerlin.mapper;

import com.mbarca.VegaFerlin.dto.request.OdontogramRequestDto;
import com.mbarca.VegaFerlin.dto.response.OdontogramListResponseDto;
import com.mbarca.VegaFerlin.dto.response.OdontogramResponseDto;
import com.mbarca.VegaFerlin.model.Odontogram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OdontogramMapper {
    OdontogramMapper INSTANCE = Mappers.getMapper(OdontogramMapper.class);

    @Mapping(source = "odontogramJson", target = "odontogramJson")
    @Mapping(source = "treatments", target = "treatments")
    Odontogram toOdontogram (OdontogramRequestDto odontogramRequestDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "odontogramJson", target = "odontogramJson")
    @Mapping(source = "treatments", target = "treatments")
    @Mapping(source = "odontogramDate", target = "odontogramDate")
    @Mapping(source = "patient.id", target = "patientId")
    OdontogramResponseDto toDto (Odontogram odontogram);

    OdontogramListResponseDto toListDto (Odontogram odontogram);
}
