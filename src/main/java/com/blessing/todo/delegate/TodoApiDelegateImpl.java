package com.blessing.todo.delegate;

import com.blessing.todo.api.TodoApiDelegate;
import com.blessing.todo.entity.Todo;
import com.blessing.todo.exception.DataNotFoundException;
import com.blessing.todo.mapper.PaginationMapper;
import com.blessing.todo.mapper.TodoMapper;
import com.blessing.todo.mapper.TodoTypeMapper;
import com.blessing.todo.model.*;
import com.blessing.todo.service.TodoService;
import com.blessing.todo.util.PageableUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TodoApiDelegateImpl implements TodoApiDelegate {

    private TodoService todoService;

    @Override
    public ResponseEntity<TodoDTO> addTodo(Long userId, TodoDTO todoDTO) {
        return ResponseEntity.ok(TodoMapper.INSTANCE.internalToDTO(
                todoService.save(TodoMapper.INSTANCE.dtoToInternal(todoDTO, userId)))
        );
    }

    @Override
    public ResponseEntity<DefaultResponse> deleteTodoByIdAndUserId(Long id, Long userId) {
        todoService.deleteByIdAndAccountId(id, userId);
        return new ResponseEntity<>(
                new DefaultResponse().message("Todo Deleted").date(LocalDateTime.now()),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<PageTodoDTO> loadTopEntitiesByUserId(Long userId, PageFilters pageFilters) {
        final Pageable pageable = PageableUtil.pageable(pageFilters.getPageNo(), pageFilters.getPageSize(), pageFilters.getSortBy(), pageFilters.getDir());
        final Page<Todo> pages = todoService.findAllTodosByUserId(userId, pageable);
        final PageTodoDTO pageTodoDTO = PaginationMapper.INSTANCE.pageTodoDTO(pages);
        return ResponseEntity.ok(pageTodoDTO);
    }

    @Override
    public ResponseEntity<TodoDTO> findTodoByIdAndUserId(Long todoId, Long userId) {
        return ResponseEntity.ok(
                TodoMapper.INSTANCE.internalToDTO(
                        todoService.findTodoByIdAndUserId(todoId, userId)
                )
        );
    }

    @Override
    public ResponseEntity<PageTodoDTO> findTodosByUserIdAndTodoType(Long userId, TodoType todoType, PageFilters pageFilters) {
        final Pageable pageable = PageableUtil.pageable(pageFilters.getPageNo(), pageFilters.getPageSize(), pageFilters.getSortBy(), pageFilters.getDir());
        final Page<Todo> pages = todoService.findTodosByUserIdAndTodoType(userId, TodoTypeMapper.INSTANCE.dtoToInternal(todoType), pageable);
        final PageTodoDTO pageTodoDTO = PaginationMapper.INSTANCE.pageTodoDTO(pages);
        return ResponseEntity.ok(pageTodoDTO);
    }

    @Override
    public ResponseEntity<PageTodoDTO> findAllTodosForTodayByUserId(Long userId, PageFilters pageFilters) {
        final Pageable pageable = PageableUtil.pageable(pageFilters.getPageNo(), pageFilters.getPageSize(), pageFilters.getSortBy(), pageFilters.getDir());
        final Page<Todo> pages = todoService.findAllTodosForTodayByUserId(userId, pageable);
        final PageTodoDTO pageTodoDTO = PaginationMapper.INSTANCE.pageTodoDTO(pages);
        return ResponseEntity.ok(pageTodoDTO);
    }

    @Override
    public ResponseEntity<PageTodoDTO> findAllTodosByDescriptionContaining(String title, PageFilters pageFilters) {
        final Pageable pageable = PageableUtil.pageable(pageFilters.getPageNo(), pageFilters.getPageSize(), pageFilters.getSortBy(), pageFilters.getDir());
        final Page<Todo> pages = todoService.findAllTodosByDescriptionContaining(title, pageable);
        final PageTodoDTO pageTodoDTO = PaginationMapper.INSTANCE.pageTodoDTO(pages);
        return ResponseEntity.ok(pageTodoDTO);
    }

    @Override
    public ResponseEntity<TodoDTO> findTodoById(Long todoId) {
        final TodoDTO todoDTO = TodoMapper.INSTANCE.internalToDTO(todoService.findById(todoId)
                .orElseThrow(() -> new DataNotFoundException("Church not found")));
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageTodoDTO> findTodos(Long userId, PageFilters pageFilters) {
        final Pageable pageable = PageableUtil.pageable(pageFilters.getPageNo(),
                pageFilters.getPageSize(), pageFilters.getSortBy(), pageFilters.getDir());
        final Page<Todo> pages = todoService.findAll(pageable);
        PageTodoDTO pageTodoDTO = PaginationMapper.INSTANCE.pageTodoDTO(pages);
        return ResponseEntity.ok(pageTodoDTO);
    }

    @Override
    public ResponseEntity<DefaultResponse> restoreSoftDeletedTodo(Long id, Long userId) {
        todoService.restoreDeletedTodo(id, userId);
        return new ResponseEntity<>(
                new DefaultResponse().message("Todo restored").date(LocalDateTime.now()),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Void> updateTodo(Long userId, TodoDTO todoDTO) {
        TodoMapper.INSTANCE.internalToDTO(
                todoService.update(
                        TodoMapper.INSTANCE.dtoToInternal(todoDTO, userId)
                )
        );
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<DefaultResponse> uploadFile(Long userId, MultipartFile profileImage) {
        return TodoApiDelegate.super.uploadFile(userId, profileImage);
    }

    @Override
    public ResponseEntity<PageTodoDTO> searchTodos(Long userId, TodoSearchDTO todoSearch, PageFilters pageFilters) {
        final Pageable pageable = PageableUtil.pageable(pageFilters.getPageNo(), pageFilters.getPageSize(), pageFilters.getSortBy(), pageFilters.getDir());
        final Page<Todo> pages = todoService.searchTodos(userId, todoSearch, pageable);
        PageTodoDTO pageTodoDTO = PaginationMapper.INSTANCE.pageTodoDTO(pages);
        return ResponseEntity.ok(pageTodoDTO);
    }

    @Override
    public ResponseEntity<PageTodoDTO> findTodosByUserIdAndIsCompleted(Long userId, Boolean isCompleted, PageFilters pageFilters) {
        final Pageable pageable = PageableUtil.pageable(pageFilters.getPageNo(), pageFilters.getPageSize(), pageFilters.getSortBy(), pageFilters.getDir());
        final Page<Todo> pages = todoService.findAllByAccountIdAndIsCompleted(userId, isCompleted, pageable);
        PageTodoDTO pageTodoDTO = PaginationMapper.INSTANCE.pageTodoDTO(pages);
        return ResponseEntity.ok(pageTodoDTO);
    }
}
