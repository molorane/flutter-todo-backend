package com.blessing.todo.delegate;

import com.blessing.todo.api.TodoApiDelegate;
import com.blessing.todo.exception.DataNotFoundException;
import com.blessing.todo.mapper.TodoMapper;
import com.blessing.todo.model.DefaultResponse;
import com.blessing.todo.model.TodoDTO;
import com.blessing.todo.model.TodoSearchDTO;
import com.blessing.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseEntity<List<TodoDTO>> loadTopEntitiesByUserId(Long userId) {
        //throw new RuntimeException("Apt");
        return ResponseEntity.ok(
                TodoMapper.INSTANCE.internalsToDTOs(
                        todoService.findAllTodosByUserId(userId)
                )
        );
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
    public ResponseEntity<List<TodoDTO>> findAllTodosForTodayByUserId(Long userId) {
        return ResponseEntity.ok(
                TodoMapper.INSTANCE.internalsToDTOs(
                        todoService.findAllTodosForTodayByUserId(userId)
                )
        );
    }

    @Override
    public ResponseEntity<List<TodoDTO>> findAllTodosByDescriptionContaining(String title) {
        final List<TodoDTO> todos = TodoMapper.INSTANCE.internalsToDTOs(
                todoService.findAllTodosByDescriptionContaining(title)
        );
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TodoDTO> findTodoById(Long todoId) {
        final TodoDTO todoDTO = TodoMapper.INSTANCE.internalToDTO(todoService.findById(todoId)
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
    public ResponseEntity<List<TodoDTO>> searchTodos(Long userId, TodoSearchDTO todoSearch) {
        return ResponseEntity.ok(
                TodoMapper.INSTANCE.internalsToDTOs(
                        todoService.searchTodos(userId, todoSearch)
                )
        );
    }
}
