/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.service.impl;

import com.blessing.todo.entity.Task;
import com.blessing.todo.entity.enums.TaskType;
import com.blessing.todo.exception.DataNotFoundException;
import com.blessing.todo.model.TaskSearchDTO;
import com.blessing.todo.repository.TaskRepository;
import com.blessing.todo.repository.specification.TaskSpecification;
import com.blessing.todo.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Override
    public Page<Task> findAllTasksByUserId(Long userId, Pageable pageable) {
        return taskRepository.findAllTasksByAccountIdAndIsDeletedFalse(userId, pageable);
    }

    @Override
    public Page<Task> findTasksByUserIdAndTaskType(Long userId, TaskType taskType, Pageable pageable) {
        return taskRepository.findTasksByAccountIdAndTaskType(userId, taskType, pageable);
    }

    @Override
    public Page<Task> findAllTasksForTodayByUserId(Long userId, Pageable pageable) {
        return taskRepository.findAllTasksByAccountIdAndDueDate(userId, LocalDate.now(), pageable);
    }

    @Override
    public Task findTaskByIdAndUserId(Long taskId, Long userId) {
        return taskRepository.findByIdAndAccountIdAndIsDeletedFalse(taskId, userId).orElseThrow(
                () -> new DataNotFoundException("Could not find task")
        );
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Page<Task> findAllTasksByDescriptionContaining(String title, Pageable pageable) {
        return taskRepository.findTaskByDescriptionContainingIgnoreCase(title, pageable);
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        final Task updateTask = findById(task.getId()).orElseThrow();
        task.setCreatedDate(updateTask.getCreatedDate());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> saveAll(Iterable<Task> entities) {
        return taskRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteByIdAndAccountId(Long id, Long userId) {
        taskRepository.deleteByIdAndAccountId(id, userId);
    }

    @Override
    public void deleteByUserId(Long userId) {
        taskRepository.deleteByAccountId(userId);
    }

    @Override
    public void restoreDeletedTask(Long id, Long userId) {
        final Task task = taskRepository.findByIdAndAccountIdAndIsDeletedTrue(id, userId).orElseThrow();
        task.setIsDeleted(false);
    }

    @Override
    public Page<Task> searchTasks(Long userId, TaskSearchDTO taskSearch, Pageable pageable) {
        final Specification<Task> criteria = TaskSpecification.searchTasks(userId, taskSearch);
        return taskRepository.findAll(criteria, pageable);
    }

    @Override
    public Page<Task> findAllByUserId(Long userId, Pageable pageable) {
        return taskRepository.findAllByAccountIdAndIsDeletedFalse(userId, pageable);
    }

    @Override
    public Page<Task> findAllByAccountIdAndIsCompleted(long accountId, boolean isCompleted, Pageable pageable) {
        return taskRepository.findAllByAccountIdAndIsCompleted(accountId, isCompleted, pageable);
    }
}