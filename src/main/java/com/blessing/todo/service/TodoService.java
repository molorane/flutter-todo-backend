package com.blessing.todo.service;

import com.blessing.todo.entity.Todo;

import java.util.List;


public interface TodoService extends AbstractService<Todo> {


    List<Todo> findAllByAccountId(final Long userId);

    default Todo save(final Todo todo, Long userId) {
        throw new RuntimeException("You need to implement this method to save an entity.");
    }
}
