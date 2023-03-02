package com.blessing.todo.dashboard;

import com.blessing.todo.entity.enums.TodoType;

public interface ITodoGroupCount {
    TodoType getTodoType();

    Boolean getIsCompleted();

    Long getTotalTodos();
}