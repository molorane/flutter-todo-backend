package com.blessing.todo.delegate;

import com.blessing.todo.api.DashboardApiDelegate;
import com.blessing.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DashboardApiDelegateImpl implements DashboardApiDelegate {

    private TodoService todoService;

    @Override
    public ResponseEntity<Long> countDeletedTodosByUserId(Long userId) {
        return ResponseEntity.ok(todoService.countDeletedTodosByAccountId(userId));
    }
}
