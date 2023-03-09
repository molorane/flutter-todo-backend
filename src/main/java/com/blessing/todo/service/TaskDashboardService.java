/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.service;

import com.blessing.todo.dashboard.ITaskCountToday;
import com.blessing.todo.dashboard.ITaskGroupCount;
import com.blessing.todo.entity.Task;
import com.blessing.todo.model.TaskType;

import java.util.List;

public interface TaskDashboardService extends AbstractService<Task> {
    Long countDeletedTasksByAccountId(long accountId);

    List<ITaskGroupCount> taskGroupCountByUserId(Long userId, TaskType taskType);

    List<ITaskCountToday> taskCountTodayByUserId(Long userId, TaskType taskType);
}
