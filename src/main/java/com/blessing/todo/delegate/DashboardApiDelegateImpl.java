package com.blessing.todo.delegate;

import com.blessing.todo.api.DashboardApiDelegate;
import com.blessing.todo.mapper.TodoDashboardMapper;
import com.blessing.todo.model.TodoCountToday;
import com.blessing.todo.model.TodoGroupCount;
import com.blessing.todo.model.TodoType;
import com.blessing.todo.service.TodoDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DashboardApiDelegateImpl implements DashboardApiDelegate {

    private TodoDashboardService todoDashboardService;

    @Override
    public ResponseEntity<Long> countDeletedTodosByUserId(Long userId) {
        return ResponseEntity.ok(todoDashboardService.countDeletedTodosByAccountId(userId));
    }

    @Override
    public ResponseEntity<List<TodoGroupCount>> todoGroupCountByUserId(Long userId, TodoType todoType) {
        return ResponseEntity.ok(
                TodoDashboardMapper.INSTANCE.internalsToDTOs(todoDashboardService.todoGroupCountByUserId(userId, todoType)));
    }

    @Override
    public ResponseEntity<List<TodoCountToday>> todoCountTodayByUserId(Long userId, TodoType todoType) {
        return ResponseEntity.ok(
                TodoDashboardMapper.INSTANCE.internalsToTodoCountToday(todoDashboardService.todoCountTodayByUserId(userId, todoType)));
    }
}
