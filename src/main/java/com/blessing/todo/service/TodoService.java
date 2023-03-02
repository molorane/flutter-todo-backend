package com.blessing.todo.service;

import com.blessing.todo.entity.Todo;
import com.blessing.todo.entity.enums.TodoType;
import com.blessing.todo.model.TodoSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoService extends AbstractService<Todo> {

    Page<Todo> findAllTodosByUserId(Long userId, Pageable pageable);

    Page<Todo> findTodosByUserIdAndTodoType(Long userId, TodoType todoType, Pageable pageable);

    Page<Todo> findAllTodosForTodayByUserId(Long userId, Pageable pageable);

    Todo findTodoByIdAndUserId(Long todoId, Long userId);

    Page<Todo> findAllTodosByDescriptionContaining(String name, Pageable pageable);

    void deleteByIdAndAccountId(Long id, Long userId);

    void deleteByUserId(Long userId);

    void restoreDeletedTodo(Long id, Long userId);

    Page<Todo> searchTodos(Long userId, TodoSearchDTO todoSearch, Pageable pageable);

    Page<Todo> findAllByUserId(Long userId, Pageable pageable);

    Page<Todo> findAllByAccountIdAndIsCompleted(long accountId, boolean isCompleted, Pageable pageable);
}
