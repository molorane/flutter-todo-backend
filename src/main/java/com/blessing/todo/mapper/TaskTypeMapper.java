package com.blessing.todo.mapper;

import com.blessing.todo.entity.enums.TaskType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TaskTypeMapper {

    TaskTypeMapper INSTANCE = Mappers.getMapper(TaskTypeMapper.class);

    TaskType dtoToInternal(com.blessing.todo.model.TaskType todo);
}
