package com.blessing.todo.controller;

import com.blessing.todo.entity.Todo;
import com.blessing.todo.exception.DataNotFoundException;
import com.blessing.todo.mapper.TodoMapper;
import com.blessing.todo.mapper.destination.TodoDTO;
import com.blessing.todo.service.TodoService;
import com.blessing.todo.util.PageableUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/task")
@Tag(name = "Todo", description = "Api operations for the todo")
public class TodoController {

    private final TodoService todoService;

    @Operation(summary = "Find todos by accountId", description = "Find todos by accountId")
    @GetMapping("{userId}")
    public ResponseEntity<List<TodoDTO>> findAllTodosByAccountId(final @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(TodoMapper.INSTANCE.todosToTodoDTOs(todoService.findAllByAccountId(userId)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Todo not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")}
    )
    @Operation(summary = "Add a todo", description = "Add a todo")
    @PostMapping("{userId}")
    public ResponseEntity<TodoDTO> addTodo(@RequestBody @Valid final TodoDTO todoDTO, final @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(TodoMapper.INSTANCE.todoToTodoDTO(
                TodoMapper.INSTANCE.todoDTOToTodo(todoDTO, userId))
        );
    }

    @Operation(description = "Get Todo", summary = "Get Todo By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Todo By Id"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    @GetMapping("{todoId}")
    public ResponseEntity<TodoDTO> findTodoById(@PathVariable("todoId") final Long todoId) {
        final TodoDTO todoDTO = TodoMapper.INSTANCE.todoToTodoDTO(todoService.findById(todoId)
                .orElseThrow(() -> new DataNotFoundException("Church not found")));
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

    @Operation(description = "Get a Todo", summary = "Get a Todo By title")
    @GetMapping("{title}/title")
    public ResponseEntity<List<TodoDTO>> findAllTodosByTitleContaining(@PathVariable("title") final String title) {
        final List<TodoDTO> todos = TodoMapper.INSTANCE.todosToTodoDTOs(todoService.findByTitleContaining(title));
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @Operation(description = "Get Todos", summary = "Get Todos in pages")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<TodoDTO>> findTodos(
            @RequestParam(defaultValue = "0") final Integer pageNo,
            @RequestParam(defaultValue = "10") final Integer pageSize,
            @RequestParam(defaultValue = "name") final String sortBy,
            @RequestParam(name = "dir", defaultValue = "asc") final String direction
    ) {
        final Pageable pageable = PageableUtil.pageable(pageNo, pageSize, sortBy, direction);
        final Page<Todo> pages = todoService.findEntities(pageable);
        final List<TodoDTO> list = TodoMapper.INSTANCE.todosToTodoDTOs(pages.getContent());
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new PageImpl<>(list), HttpStatus.OK);
    }

    @Operation(description = "Update a Todo", summary = "Update Todo")
    @PutMapping("{userId}")
    public ResponseEntity<TodoDTO> updateTodo(@RequestBody @Valid final TodoDTO todoDTO, final @PathVariable("userId") Long userId) {
        final TodoDTO todo = TodoMapper.INSTANCE.todoToTodoDTO(
                todoService.save(
                        TodoMapper.INSTANCE.todoDTOToTodo(todoDTO, userId)
                )
        );
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @Operation(description = "Delete a Todo", summary = "Delete a Todo By Id")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable("id") final Long id) {
        todoService.deleteById(id);
        return new ResponseEntity<>("Todo Deleted", HttpStatus.OK);
    }

    @Operation(description = "Restore a deleted Todo", summary = "Restore a deleted Todo")
    @PutMapping("undo/{id}")
    public ResponseEntity<?> restoreDeletedTodo(@PathVariable("id") final Long id) {
        todoService.restoreDeleted(id);
        return new ResponseEntity<>("Todo restored", HttpStatus.OK);
    }
}
