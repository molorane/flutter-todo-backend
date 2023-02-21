package com.blessing.todo.service;

import com.blessing.todo.entity.Todo;

import java.util.List;

public interface TodoService extends AbstractService<Todo> {

    List<Todo> findAllTodosByAccountId(Long userId);

    List<Todo> findAllTodaysTodosByAccountId(Long userId);

    List<Todo> findAllTodosByDescriptionContaining(String name);

    void deleteByIdAndAccountId(Long id, Long userId);

    void deleteByAccountId(Long userId);

    void restoreDeletedTodo(Long id, Long userId);

    Long countDeletedTodosByAccountId(long accountId);
}
