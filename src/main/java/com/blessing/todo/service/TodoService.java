package com.blessing.todo.service;

import com.blessing.todo.entity.Todo;
import com.blessing.todo.model.TodoSearchDTO;

import java.util.List;

public interface TodoService extends AbstractService<Todo> {

    List<Todo> findAllTodosByUserId(Long userId);

    List<Todo> findAllTodosForTodayByUserId(Long userId);

    Todo findTodoByIdAndUserId(Long todoId, Long userId);

    List<Todo> findAllTodosByDescriptionContaining(String name);

    void deleteByIdAndAccountId(Long id, Long userId);

    void deleteByUserId(Long userId);

    void restoreDeletedTodo(Long id, Long userId);

    Long countDeletedTodosByAccountId(long accountId);

    List<Todo>  searchTodos(Long userId, TodoSearchDTO todoSearch);
}
