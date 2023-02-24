package com.blessing.todo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AbstractService<T> {


    default Optional<T> findById(Long id) {
        throw new RuntimeException("You need to implement this method to save an entity.");
    }

    default T save(final T entity) {
        throw new RuntimeException("You need to implement this method to save an entity.");
    }

    default T update(final T entity) {
        throw new RuntimeException("You need to implement this method to update an entity.");
    }

    default Page<T> findAll(Pageable pageable) {
        throw new RuntimeException("You need to implement this method to save an entity.");
    }

    default void deleteById(final Long id) {
        throw new RuntimeException("You need to implement this method to delete an entity.");
    }
}