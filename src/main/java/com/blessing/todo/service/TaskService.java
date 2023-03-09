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
import com.blessing.todo.entity.enums.TaskType;
import com.blessing.todo.model.TaskSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService extends AbstractService<Task> {

    Page<Task> findAllTasksByUserId(Long userId, Pageable pageable);

    Page<Task> findTasksByUserIdAndTaskType(Long userId, TaskType todoType, Pageable pageable);

    Page<Task> findAllTasksForTodayByUserId(Long userId, Pageable pageable);

    Task findTaskByIdAndUserId(Long todoId, Long userId);

    Page<Task> findAllTasksByDescriptionContaining(String name, Pageable pageable);

    void deleteByIdAndAccountId(Long id, Long userId);

    void deleteByUserId(Long userId);

    void restoreDeletedTask(Long id, Long userId);

    Page<Task> searchTasks(Long userId, TaskSearchDTO todoSearch, Pageable pageable);

    Page<Task> findAllByUserId(Long userId, Pageable pageable);

    Page<Task> findAllByAccountIdAndIsCompleted(long accountId, boolean isCompleted, Pageable pageable);
}
