package com.mbarca.VegaFerlin.mapper;

import com.mbarca.VegaFerlin.dto.request.MedicalHistoryRequestDto;
import com.mbarca.VegaFerlin.dto.response.MedicalHistoryResponseDto;
import com.mbarca.VegaFerlin.model.MedicalHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface MedicalHistoryMapper {
    MedicalHistoryMapper INSTANCE = Mappers.getMapper(MedicalHistoryMapper.class);
    MedicalHistory toMedicalHistory (MedicalHistoryRequestDto medicalHistoryRequestDto);
    MedicalHistoryResponseDto toDto (MedicalHistory medicalHistory);
}
