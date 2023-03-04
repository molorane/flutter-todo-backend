package com.blessing.todo.service.impl;

import com.blessing.todo.dashboard.ITaskCountToday;
import com.blessing.todo.dashboard.ITaskGroupCount;
import com.blessing.todo.mapper.TaskTypeMapper;
import com.blessing.todo.model.TaskType;
import com.blessing.todo.repository.TaskDashboardRepository;
import com.blessing.todo.service.TaskDashboardService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class TaskDashboardServiceImpl implements TaskDashboardService {

    private TaskDashboardRepository taskDashboardRepository;

    @Override
    public Long countDeletedTasksByAccountId(long accountId) {
        return taskDashboardRepository.countByAccountIdAndIsDeletedTrue(accountId);
    }

    @Override
    public List<ITaskGroupCount> taskGroupCountByUserId(Long userId, TaskType taskType) {
        if (Objects.isNull(taskType))
            return taskDashboardRepository.taskGroupCountByUserId(userId);

        return taskDashboardRepository.taskGroupCountByUserId(userId,
                TaskTypeMapper.INSTANCE.dtoToInternal(taskType)
        );
    }

    @Override
    public List<ITaskCountToday> taskCountTodayByUserId(Long userId, TaskType taskType) {
        if (Objects.isNull(taskType))
            return taskDashboardRepository.taskCountTodayByUserId(userId);

        return taskDashboardRepository.taskCountTodayByUserId(userId,
                TaskTypeMapper.INSTANCE.dtoToInternal(taskType)
        );
    }
}