package com.blessing.todo.service.impl;

import com.blessing.todo.entity.Todo;
import com.blessing.todo.exception.DataNotFoundException;
import com.blessing.todo.repository.TodoRepository;
import com.blessing.todo.service.TodoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Override
    public List<Todo> findAllTodosByAccountId(Long userId) {
        return todoRepository.findAllByAccountIdAndDeletedFalseOrderByDueDateDesc(userId);
    }

    @Override
    public List<Todo> findAllTodaysTodosByAccountId(Long userId) {
        return todoRepository.findAllTodosByAccountIdAndDueDate(userId, LocalDate.now());
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> findAllTodosByDescriptionContaining(String title) {
        return todoRepository.findTodoByDescriptionContainingIgnoreCase(title);
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
    public Todo update(Todo todo) {
        Todo update = findById(todo.getId()).orElseThrow(() -> new DataNotFoundException("An error occurred while updating entity"));
        update.setTodoType(todo.getTodoType());
        update.setDescription(todo.getDescription());
        update.setDueDate(todo.getDueDate());
        update.setCompleted(todo.getCompleted());
        return update;
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

    @Override
    public Long countDeletedTodosByAccountId(long accountId) {
        return todoRepository.countByAccountIdAndDeletedTrue(accountId);
    }
}