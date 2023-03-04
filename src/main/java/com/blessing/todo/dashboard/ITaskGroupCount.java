package com.blessing.todo.dashboard;

import com.blessing.todo.entity.enums.TaskType;

public interface ITaskGroupCount {
    TaskType getTaskType();

    Boolean getIsCompleted();

    Long getTotalTasks();
}