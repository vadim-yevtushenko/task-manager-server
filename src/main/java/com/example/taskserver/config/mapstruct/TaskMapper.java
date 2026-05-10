package com.example.taskserver.config.mapstruct;

import com.example.taskserver.api.dto.TaskDto;
import com.example.taskserver.api.dto.TaskSummaryDto;
import com.example.taskserver.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskSummaryDto toSummaryDto(TaskEntity taskEntity);

    TaskDto toDto(TaskEntity taskEntity);

    TaskEntity toEntity(TaskDto dto);

    void updateEntityFromDto(TaskDto dto, @MappingTarget TaskEntity entity);

}
