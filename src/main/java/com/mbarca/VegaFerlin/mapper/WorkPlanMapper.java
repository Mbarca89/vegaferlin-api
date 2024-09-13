package com.mbarca.VegaFerlin.mapper;

import com.mbarca.VegaFerlin.dto.request.WorkPlanRequestDto;
import com.mbarca.VegaFerlin.dto.response.WorkPlanResponseDto;
import com.mbarca.VegaFerlin.model.WorkPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkPlanMapper {
    WorkPlanMapper INSTANCE = Mappers.getMapper(WorkPlanMapper.class);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "stages", source = "stages")
    WorkPlanResponseDto toDto (WorkPlan workPlan);
    WorkPlan toWorkPlan (WorkPlanRequestDto workPlanRequestDto);
}
