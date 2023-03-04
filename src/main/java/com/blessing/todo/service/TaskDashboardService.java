package com.blessing.todo.service;

import com.blessing.todo.dashboard.ITaskCountToday;
import com.blessing.todo.dashboard.ITaskGroupCount;
import com.blessing.todo.entity.Task;
import com.blessing.todo.model.TaskType;

import java.util.List;

public interface TaskDashboardService extends AbstractService<Task> {
    Long countDeletedTasksByAccountId(long accountId);

    List<ITaskGroupCount> taskGroupCountByUserId(Long userId, TaskType taskType);

    List<ITaskCountToday> taskCountTodayByUserId(Long userId, TaskType taskType);
}
