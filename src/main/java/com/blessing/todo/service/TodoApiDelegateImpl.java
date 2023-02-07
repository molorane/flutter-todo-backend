package com.blessing.todo.service;

import com.blessing.todo.api.TodoApiDelegate;
import com.blessing.todo.entity.Todo;
import com.blessing.todo.exception.DataNotFoundException;
import com.blessing.todo.mapper.TodoMapper;
import com.blessing.todo.model.PageTodoDTO;
import com.blessing.todo.model.TodoDTO;
import com.blessing.todo.repository.TodoRepository;
import com.blessing.todo.util.PageableUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoApiDelegateImpl implements TodoApiDelegate {

    private TodoRepository todoRepository;

    @Override
    public ResponseEntity<TodoDTO> addTodo(Long userId, TodoDTO todoDTO) {
        return ResponseEntity.ok(TodoMapper.INSTANCE.todoToTodoDTO(
                TodoMapper.INSTANCE.todoDTOToTodo(todoDTO, userId))
        );
    }

    @Override
    public ResponseEntity<Object> deleteTodoById(Long id) {
         todoRepository.deleteById(id);
        return new ResponseEntity<>("Todo Deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TodoDTO>> findAllTodosByAccountId(Long userId) {
        return ResponseEntity.ok(TodoMapper.INSTANCE.todosToTodoDTOs(todoRepository.findAllByAccountIdOrderByIdDesc(userId)));
    }

    @Override
    public ResponseEntity<List<TodoDTO>> findAllTodosByTitleContaining(String title) {
        final List<TodoDTO> todos = TodoMapper.INSTANCE.todosToTodoDTOs(todoRepository.findTodoByTitleContaining(title));
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TodoDTO> findTodoById(Long todoId) {
        final TodoDTO todoDTO = TodoMapper.INSTANCE.todoToTodoDTO(todoRepository.findById(todoId)
                .orElseThrow(() -> new DataNotFoundException("Church not found")));
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<PageTodoDTO> findTodos(Integer pageNo, Integer pageSize, String sortBy, String dir) {
//        final Pageable pageable = PageableUtil.pageable(pageNo, pageSize, sortBy, dir);
//        final Page<Todo> pages = todoRepository.findAll(pageable);
//        final List<TodoDTO> list = TodoMapper.INSTANCE.todosToTodoDTOs(pages.getContent());
//        if (list.isEmpty())
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(new PageImpl<>(list), HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<Object> restoreDeletedTodo(Long id) {
        todoRepository.restoreDeleted(id);
        return new ResponseEntity<>("Todo restored", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TodoDTO> updateTodo(Long userId, TodoDTO todoDTO) {
        final TodoDTO todo = TodoMapper.INSTANCE.todoToTodoDTO(
                todoRepository.save(
                        TodoMapper.INSTANCE.todoDTOToTodo(todoDTO, userId)
                )
        );
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }
}
