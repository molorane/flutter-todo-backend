/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.delegate;

import com.blessing.todo.api.TaskApiDelegate;
import com.blessing.todo.entity.Task;
import com.blessing.todo.exception.DataNotFoundException;
import com.blessing.todo.mapper.PaginationMapper;
import com.blessing.todo.mapper.TaskMapper;
import com.blessing.todo.mapper.TaskTypeMapper;
import com.blessing.todo.model.*;
import com.blessing.todo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TaskApiDelegateImpl implements TaskApiDelegate {

    private TaskService todoService;

    @Override
    public ResponseEntity<TaskDTO> addTask(Long userId, TaskDTO todoDTO) {
        return ResponseEntity.ok(TaskMapper.INSTANCE.internalToDTO(
                todoService.save(TaskMapper.INSTANCE.dtoToInternal(todoDTO, userId)))
        );
    }

    @Override
    public ResponseEntity<DefaultResponse> deleteTaskByIdAndUserId(Long id, Long userId) {
        todoService.deleteByIdAndAccountId(id, userId);
        return new ResponseEntity<>(
                new DefaultResponse().message("Task Deleted").date(LocalDateTime.now()),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<PageTaskDTO> findTasksByUserId(Long userId, Pageable pageable) {
        final Page<Task> pages = todoService.findAllTasksByUserId(userId, pageable);
        final PageTaskDTO pageTaskDTO = PaginationMapper.INSTANCE.pageTaskDTO(pages);
        return ResponseEntity.ok(pageTaskDTO);
    }

    @Override
    public ResponseEntity<TaskDTO> findTaskByIdAndUserId(Long todoId, Long userId) {
        return ResponseEntity.ok(
                TaskMapper.INSTANCE.internalToDTO(
                        todoService.findTaskByIdAndUserId(todoId, userId)
                )
        );
    }

    @Override
    public ResponseEntity<PageTaskDTO> findTasksByUserIdAndTaskType(Long userId, TaskType todoType, Pageable pageable) {
        final Page<Task> pages = todoService.findTasksByUserIdAndTaskType(userId, TaskTypeMapper.INSTANCE.dtoToInternal(todoType), pageable);
        final PageTaskDTO pageTaskDTO = PaginationMapper.INSTANCE.pageTaskDTO(pages);
        return ResponseEntity.ok(pageTaskDTO);
    }

    @Override
    public ResponseEntity<PageTaskDTO> findAllTasksForTodayByUserId(Long userId, Pageable pageable) {
        final Page<Task> pages = todoService.findAllTasksForTodayByUserId(userId, pageable);
        final PageTaskDTO pageTaskDTO = PaginationMapper.INSTANCE.pageTaskDTO(pages);
        return ResponseEntity.ok(pageTaskDTO);
    }

    @Override
    public ResponseEntity<PageTaskDTO> findAllTasksByDescriptionContaining(String title, Pageable pageable) {
        final Page<Task> pages = todoService.findAllTasksByDescriptionContaining(title, pageable);
        final PageTaskDTO pageTaskDTO = PaginationMapper.INSTANCE.pageTaskDTO(pages);
        return ResponseEntity.ok(pageTaskDTO);
    }

    @Override
    public ResponseEntity<TaskDTO> findTaskById(Long todoId) {
        final TaskDTO todoDTO = TaskMapper.INSTANCE.internalToDTO(todoService.findById(todoId)
                .orElseThrow(() -> new DataNotFoundException("Church not found")));
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DefaultResponse> restoreSoftDeletedTask(Long id, Long userId) {
        todoService.restoreDeletedTask(id, userId);
        return new ResponseEntity<>(
                new DefaultResponse().message("Task restored").date(LocalDateTime.now()),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Void> updateTask(Long userId, TaskDTO todoDTO) {
        TaskMapper.INSTANCE.internalToDTO(
                todoService.update(
                        TaskMapper.INSTANCE.dtoToInternal(todoDTO, userId)
                )
        );
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<PageTaskDTO> searchTasks(Long userId, TaskSearchDTO todoSearch, Pageable pageable) {
        final Page<Task> pages = todoService.searchTasks(userId, todoSearch, pageable);
        final PageTaskDTO pageTaskDTO = PaginationMapper.INSTANCE.pageTaskDTO(pages);
        return ResponseEntity.ok(pageTaskDTO);
    }

    @Override
    public ResponseEntity<PageTaskDTO> findTasksByUserIdAndIsCompleted(Long userId, Boolean isCompleted, Pageable pageable) {
        final Page<Task> pages = todoService.findAllByAccountIdAndIsCompleted(userId, isCompleted, pageable);
        final PageTaskDTO pageTaskDTO = PaginationMapper.INSTANCE.pageTaskDTO(pages);
        return ResponseEntity.ok(pageTaskDTO);
    }
}
