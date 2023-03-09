/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.repository;

import com.blessing.todo.dashboard.ITaskCountToday;
import com.blessing.todo.dashboard.ITaskGroupCount;
import com.blessing.todo.entity.Task;
import com.blessing.todo.entity.enums.TaskType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskDashboardRepository extends AbstractRepository<Task>, JpaSpecificationExecutor<Task> {

    Long countByAccountIdAndIsDeletedTrue(long accountId);

    @Query("SELECT t.taskType AS taskType, t.isCompleted AS isCompleted, COUNT(t.isCompleted) AS totalTasks " +
            "FROM Task AS t " +
            "WHERE t.account.id = :userId AND t.isDeleted = FALSE " +
            "GROUP BY t.taskType, t.isCompleted " +
            "ORDER BY totalTasks")
    List<ITaskGroupCount> taskGroupCountByUserId(Long userId);

    @Query("SELECT t.taskType AS taskType, t.isCompleted AS isCompleted, COUNT(t.isCompleted) AS totalTasks " +
            "FROM Task AS t " +
            "WHERE t.account.id = :userId AND t.isDeleted = FALSE AND t.taskType =  :taskType " +
            "GROUP BY t.taskType, t.isCompleted " +
            "ORDER BY totalTasks")
    List<ITaskGroupCount> taskGroupCountByUserId(Long userId, TaskType taskType);

    @Query("SELECT t.isCompleted AS isCompleted, COUNT(t.isCompleted) AS totalTasks " +
            "FROM Task AS t " +
            "WHERE t.account.id = :userId AND t.isDeleted = FALSE AND t.dueDate = CURRENT DATE " +
            "GROUP BY t.isCompleted " +
            "ORDER BY totalTasks")
    List<ITaskCountToday> taskCountTodayByUserId(Long userId);

    @Query("SELECT t.isCompleted AS isCompleted, COUNT(t.isCompleted) AS totalTasks " +
            "FROM Task AS t " +
            "WHERE t.account.id = :userId AND t.isDeleted = FALSE AND t.dueDate = CURRENT DATE AND t.taskType =  :taskType " +
            "GROUP BY t.isCompleted " +
            "ORDER BY totalTasks")
    List<ITaskCountToday> taskCountTodayByUserId(Long userId, TaskType taskType);
}
