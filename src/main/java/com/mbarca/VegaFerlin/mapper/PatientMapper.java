package com.mbarca.VegaFerlin.mapper;

import com.mbarca.VegaFerlin.dto.request.PatientRequestDto;
import com.mbarca.VegaFerlin.dto.response.PatientListResponseDto;
import com.mbarca.VegaFerlin.dto.response.PatientResponseDto;
import com.mbarca.VegaFerlin.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "inChargeOfId", source = "inChargeOfId")
    Patient toPatient(PatientRequestDto patientRequestDto);
    PatientResponseDto toPatientResponseDto(Patient patient);
    @Mapping(target = "id", source = "id")
    PatientListResponseDto toPatientListResponseDto(Patient patient);
}