package com.blessing.todo.service.impl;

import com.blessing.todo.dashboard.ITodoCountToday;
import com.blessing.todo.dashboard.ITodoGroupCount;
import com.blessing.todo.mapper.TodoTypeMapper;
import com.blessing.todo.model.TodoType;
import com.blessing.todo.repository.TodoDashboardRepository;
import com.blessing.todo.service.TodoDashboardService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class TodoDashboardServiceImpl implements TodoDashboardService {

    private TodoDashboardRepository todoDashboardRepository;

    @Override
    public Long countDeletedTodosByAccountId(long accountId) {
        return todoDashboardRepository.countByAccountIdAndIsDeletedTrue(accountId);
    }

    @Override
    public List<ITodoGroupCount> todoGroupCountByUserId(Long userId, TodoType todoType) {
        if (Objects.isNull(todoType) || Objects.equals(todoType, TodoType.UNKNOWN))
            return todoDashboardRepository.todoGroupCountByUserId(userId);

        return todoDashboardRepository.todoGroupCountByUserId(userId,
                TodoTypeMapper.INSTANCE.dtoToInternal(todoType)
        );

    }

    @Override
    public List<ITodoCountToday> todoCountTodayByUserId(Long userId, TodoType todoType) {
        if (Objects.isNull(todoType) || Objects.equals(todoType, TodoType.UNKNOWN))
            return todoDashboardRepository.todoCountTodayByUserId(userId);

        return todoDashboardRepository.todoCountTodayByUserId(userId,
                TodoTypeMapper.INSTANCE.dtoToInternal(todoType)
        );

    }
}