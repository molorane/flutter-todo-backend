package com.blessing.todo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AbstractService<T> {


    Optional<T> findById(Long id);

    default T save(final T entity) {
        throw new RuntimeException("You need to implement this method to save an entity.");
    }

    default T update(final T entity) {
        throw new RuntimeException("You need to implement this method to update an entity.");
    }

    Page<T> findAll(Pageable pageable);

    default void deleteById(final Long id) {
        throw new RuntimeException("You need to implement this method to delete an entity.");
    }
}