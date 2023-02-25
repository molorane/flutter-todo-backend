package com.blessing.todo.service;

import com.blessing.todo.dashboard.ITodoCountToday;
import com.blessing.todo.dashboard.ITodoGroupCount;
import com.blessing.todo.entity.Todo;

import java.util.List;

public interface TodoDashboardService extends AbstractService<Todo> {
    Long countDeletedTodosByAccountId(long accountId);

    List<ITodoGroupCount> todoGroupCountByUserId(Long userId);

    List<ITodoCountToday> todoCountTodayByUserId(Long userId);
}
