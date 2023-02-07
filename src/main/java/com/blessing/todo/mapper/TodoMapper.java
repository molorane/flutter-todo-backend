package com.blessing.todo.mapper;

import com.blessing.todo.entity.Account;
import com.blessing.todo.entity.Todo;
import com.blessing.todo.mapper.destination.TodoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TodoMapper {

    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    @Mapping(target = "todoType", source = "todo.todoType")
    @Mapping(target = "title", source = "todo.title")
    @Mapping(target = "completed", source = "todo.completed")
    @Mapping(target = "dueDate", source = "todo.dueDate")
    @Mapping(target = "description", source = "todo.description")
    @Mapping(target = "createdDate", source = "todo.createdDate")
    @Mapping(target = "deleted", source = "todo.deleted")
    TodoDTO todoToTodoDTO(final Todo todo);

    @Mapping(target = "account", expression = "java(buildAccount(userId))")
    @Mapping(target = "todoType", source = "todo.todoType")
    @Mapping(target = "title", source = "todo.title")
    @Mapping(target = "completed", source = "todo.completed")
    @Mapping(target = "dueDate", source = "todo.dueDate")
    @Mapping(target = "description", source = "todo.description")
    @Mapping(target = "createdDate", source = "todo.createdDate")
    @Mapping(target = "deleted", source = "todo.deleted")
    Todo todoDTOToTodo(final TodoDTO todo, final Long userId);

    List<TodoDTO> todosToTodoDTOs(final List<Todo> todos);

    default Account buildAccount(final Long userId) {
        Account account = new Account();
        account.setId(userId);
        return account;
    }
}
