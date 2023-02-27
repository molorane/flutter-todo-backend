package com.blessing.todo.mapper;

import com.blessing.todo.entity.enums.TodoType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TodoTypeMapper {

    TodoTypeMapper INSTANCE = Mappers.getMapper(TodoTypeMapper.class);

    TodoType dtoToInternal(com.blessing.todo.model.TodoType todo);

    com.blessing.todo.model.TodoType internalToDTO(TodoType todo);
}
