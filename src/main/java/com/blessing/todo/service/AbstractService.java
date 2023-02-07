package com.blessing.todo.service;

import com.blessing.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T> {


    Optional<T> findById(final Long id);

    List<Todo> findByNameContaining(String name);

    Page<T> findEntities(final Pageable pageable);

    default void deleteById(final Long id) {
        throw new RuntimeException("You need to implement this method to delete an entity.");
    }
}