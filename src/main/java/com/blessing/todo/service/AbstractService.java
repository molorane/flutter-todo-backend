/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.service;

import com.blessing.todo.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T> {


    default Optional<T> findById(Long id) {
        throw new RuntimeException("You need to implement this method to save an entity.");
    }

    default T save(final T entity) {
        throw new RuntimeException("You need to implement this method to save an entity.");
    }

    default List<Task> saveAll(final Iterable<T> entities) {
        throw new RuntimeException("You need to implement this method to saveAll an entities.");
    }

    default Page<T> findAll(Pageable pageable) {
        throw new RuntimeException("You need to implement this method to save an entity.");
    }

    default void deleteById(final Long id) {
        throw new RuntimeException("You need to implement this method to delete an entity.");
    }
}