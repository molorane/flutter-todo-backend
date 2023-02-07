package com.blessing.todo.controller;

import com.blessing.todo.mapper.TodoMapper;
import com.blessing.todo.mapper.destination.TodoDTO;
import com.blessing.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<List<TodoDTO>> findAllByAccountId(final @PathVariable("userId") Long userId) {
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
}
