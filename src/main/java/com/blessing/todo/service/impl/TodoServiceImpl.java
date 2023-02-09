package com.blessing.todo.service.impl;

import com.blessing.todo.entity.Todo;
import com.blessing.todo.repository.TodoRepository;
import com.blessing.todo.service.TodoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Override
    public List<Todo> findAllTodosByAccountId(Long userId) {
        return todoRepository.findAllByAccountIdAndCompletedIsFalseOrderByIdDesc(userId);
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> findByTitleContaining(String title) {
        return todoRepository.findTodoByTitleContainingIgnoreCase(title);
    }

    @Override
    public Page<Todo> findAll(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public void deleteByIdAndAccountId(Long id, Long userId) {
        todoRepository.deleteByIdAndAccountId(id, userId);
    }

    @Override
    public void deleteByAccountId(Long userId) {
        todoRepository.deleteByAccountId(userId);
    }

    @Override
    public void restoreDeletedTodo(Long id, Long userId) {
        todoRepository.restoreDeletedTodo(id, userId);
    }
}