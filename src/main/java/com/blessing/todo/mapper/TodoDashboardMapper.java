package com.blessing.todo.mapper;

import com.blessing.todo.dashboard.ITodoGroupCount;
import com.blessing.todo.model.TodoGroupCount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TodoDashboardMapper {

    TodoDashboardMapper INSTANCE = Mappers.getMapper(TodoDashboardMapper.class);

    TodoGroupCount internalToDTO(ITodoGroupCount todo);

    List<TodoGroupCount> internalsToDTOs(List<ITodoGroupCount> todos);
}
