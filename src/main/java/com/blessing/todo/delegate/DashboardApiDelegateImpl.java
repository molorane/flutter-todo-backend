/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.delegate;

import com.blessing.todo.api.DashboardApiDelegate;
import com.blessing.todo.mapper.TaskDashboardMapper;
import com.blessing.todo.model.TaskCountToday;
import com.blessing.todo.model.TaskGroupCount;
import com.blessing.todo.model.TaskType;
import com.blessing.todo.service.TaskDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DashboardApiDelegateImpl implements DashboardApiDelegate {

    private TaskDashboardService taskDashboardService;

    @Override
    public ResponseEntity<Long> countDeletedTasksByUserId(Long userId) {
        return ResponseEntity.ok(taskDashboardService.countDeletedTasksByAccountId(userId));
    }

    @Override
    public ResponseEntity<List<TaskGroupCount>> taskGroupCountByUserId(Long userId, TaskType taskType) {
        return ResponseEntity.ok(
                TaskDashboardMapper.INSTANCE.internalsToDTOs(taskDashboardService.taskGroupCountByUserId(userId, taskType)));
    }

    @Override
    public ResponseEntity<List<TaskCountToday>> taskCountTodayByUserId(Long userId, TaskType taskType) {
        return ResponseEntity.ok(
                TaskDashboardMapper.INSTANCE.internalsToTaskCountToday(taskDashboardService.taskCountTodayByUserId(userId, taskType)));
    }
}
