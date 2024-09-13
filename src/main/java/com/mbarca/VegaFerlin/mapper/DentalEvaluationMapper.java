package com.mbarca.VegaFerlin.mapper;

import com.mbarca.VegaFerlin.dto.request.DentalEvaluationRequestDto;
import com.mbarca.VegaFerlin.dto.response.DentalEvaluationResponseDto;
import com.mbarca.VegaFerlin.model.DentalEvaluation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface DentalEvaluationMapper {
    DentalEvaluationMapper INSTANCE = Mappers.getMapper(DentalEvaluationMapper.class);

    DentalEvaluation toDentalEvaluation (DentalEvaluationRequestDto dentalEvaluationRequestDto);
    DentalEvaluationResponseDto toDto (DentalEvaluation dentalEvaluation);
}
