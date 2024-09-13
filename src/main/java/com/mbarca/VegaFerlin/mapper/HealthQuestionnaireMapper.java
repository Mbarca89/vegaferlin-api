package com.mbarca.VegaFerlin.mapper;

import com.mbarca.VegaFerlin.dto.request.HealthQuestionnaireRequestDto;
import com.mbarca.VegaFerlin.dto.response.HealthQuestionnaireResponseDto;
import com.mbarca.VegaFerlin.model.HealthQuestionnaire;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface HealthQuestionnaireMapper {
    HealthQuestionnaireMapper INSTANCE = Mappers.getMapper(HealthQuestionnaireMapper.class);
    HealthQuestionnaire toHealthQuestionnaire(HealthQuestionnaireRequestDto healthQuestionnaireRequestDto);
    HealthQuestionnaireResponseDto toHealthQuestionnaireResponseDto(HealthQuestionnaire healthQuestionnaire);
}
