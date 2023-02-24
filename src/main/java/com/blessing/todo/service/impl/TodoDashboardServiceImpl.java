package com.blessing.todo.service.impl;

import com.blessing.todo.dashboard.ITodoGroupCount;
import com.blessing.todo.repository.TodoDashboardRepository;
import com.blessing.todo.service.TodoDashboardService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<ITodoGroupCount> todoGroupCountByUserId(Long userId) {
        return todoDashboardRepository.todoGroupCountByUserId(userId);
    }
}