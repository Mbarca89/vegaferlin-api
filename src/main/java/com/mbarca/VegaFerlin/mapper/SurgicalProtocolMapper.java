package com.mbarca.VegaFerlin.mapper;

import com.mbarca.VegaFerlin.dto.CompensatorDto;
import com.mbarca.VegaFerlin.dto.ImplantDto;
import com.mbarca.VegaFerlin.dto.SurgicalInfoDto;
import com.mbarca.VegaFerlin.dto.request.SurgicalProtocolRequestDto;
import com.mbarca.VegaFerlin.dto.response.SurgicalProtocolResponseDto;
import com.mbarca.VegaFerlin.model.Compensator;
import com.mbarca.VegaFerlin.model.Implant;
import com.mbarca.VegaFerlin.model.SurgicalInfo;
import com.mbarca.VegaFerlin.model.SurgicalProtocol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SurgicalProtocolMapper {

    SurgicalProtocolMapper INSTANCE = Mappers.getMapper(SurgicalProtocolMapper.class);

    SurgicalProtocol toEntity(SurgicalProtocolRequestDto dto);

    SurgicalProtocolResponseDto toResponseDTO(SurgicalProtocol entity);

    SurgicalInfo toEntity(SurgicalInfoDto dto);

    SurgicalInfoDto toResponseDTO(SurgicalInfo entity);

    Compensator toEntity(CompensatorDto dto);

    CompensatorDto toResponseDTO(Compensator entity);

    Implant toEntity(ImplantDto dto);

    ImplantDto toResponseDTO(Implant entity);
}
