package com.mbarca.VegaFerlin.mapper;

import com.mbarca.VegaFerlin.dto.response.ActivityLogResponseDto;
import com.mbarca.VegaFerlin.model.ActivityLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActivityLogMapper {
    ActivityLogMapper INSTANCE = Mappers.getMapper(ActivityLogMapper.class);

    ActivityLogResponseDto toDto (ActivityLog activityLog);
}
