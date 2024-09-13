package com.mbarca.VegaFerlin.mapper;

import com.mbarca.VegaFerlin.dto.request.LabsRequestDto;
import com.mbarca.VegaFerlin.dto.response.LabsResponseDto;
import com.mbarca.VegaFerlin.model.Labs;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface LabsMapper {
    LabsMapper INSTANCE = Mappers.getMapper(LabsMapper.class);
    LabsResponseDto toDto (Labs labs);
    Labs toLabs (LabsRequestDto labsRequestDto);
}
