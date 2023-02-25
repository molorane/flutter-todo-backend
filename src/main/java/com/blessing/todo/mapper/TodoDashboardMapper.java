package com.blessing.todo.mapper;

import com.blessing.todo.dashboard.ITodoCountToday;
import com.blessing.todo.dashboard.ITodoGroupCount;
import com.blessing.todo.model.TodoCountToday;
import com.blessing.todo.model.TodoGroupCount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TodoDashboardMapper {

    TodoDashboardMapper INSTANCE = Mappers.getMapper(TodoDashboardMapper.class);

    TodoGroupCount internalToDTO(ITodoGroupCount todo);

    List<TodoGroupCount> internalsToDTOs(List<ITodoGroupCount> todoGroupCountList);

    TodoCountToday internalToDTO(ITodoCountToday todoCountToday);

    List<TodoCountToday> internalsToTodoCountToday(List<ITodoCountToday> todoCountTodayList);
}
