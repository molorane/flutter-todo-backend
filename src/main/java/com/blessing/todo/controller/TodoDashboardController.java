package com.blessing.todo.controller;

import com.blessing.todo.dashboard.ITodoGroupCount;
import com.blessing.todo.repository.TodoDashboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("dashboard")
@AllArgsConstructor
public class TodoDashboardController {

    private TodoDashboardRepository todoDashboardRepository;

    @GetMapping
    public List<ITodoGroupCount> accounts() {
        return todoDashboardRepository.todoGroupCountByUserId(2014098616L);
    }
}
