package com.mbarca.VegaFerlin.mapper;

import com.mbarca.VegaFerlin.dto.response.DentalPredictionResponseDto;
import com.mbarca.VegaFerlin.model.DentalPrediction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DentalPredictionMapper {
    DentalPredictionMapper INSTANCE = Mappers.getMapper(DentalPredictionMapper.class);

    DentalPredictionResponseDto toDto (DentalPrediction dentalPrediction);
}
