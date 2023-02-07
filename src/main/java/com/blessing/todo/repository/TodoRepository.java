package com.blessing.todo.repository;

import com.blessing.todo.entity.Todo;

import java.util.List;

public interface TodoRepository extends AbstractRepository<Todo> {

    List<Todo> findTodoByTitleContaining(String title);

    List<Todo> findAllByCompleted(boolean completed);

    List<Todo> findAllByAccountIdOrderByIdDesc(Long userId);

}
