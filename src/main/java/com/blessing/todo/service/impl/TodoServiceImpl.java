package com.blessing.todo.service.impl;

import com.blessing.todo.entity.Todo;
import com.blessing.todo.repository.TodoRepository;
import com.blessing.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Override
    public List<Todo> findAllByAccountId(Long userId) {
        return todoRepository.findAllByAccountIdOrderByIdDesc(userId);
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> findByNameContaining(String name) {
        return todoRepository.findTodoByTitleContaining(name);
    }

    @Override
    public Page<Todo> findEntities(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    @Override
    public Todo save(Todo todo, Long userId) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteById(Long id) {
        TodoService.super.deleteById(id);
    }
}
