package com.blessing.todo.mapper;

import com.blessing.todo.entity.Account;
import com.blessing.todo.entity.SearchTodo;
import com.blessing.todo.entity.Todo;
import com.blessing.todo.model.TodoDTO;
import com.blessing.todo.model.TodoSearchDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TodoMapper {

    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    TodoDTO internalToDTO(Todo todo);

    SearchTodo internalToDTO(TodoSearchDTO todo);

    @Mapping(target = "account", expression = "java(buildAccount(userId))")
    Todo dtoToInternal(TodoDTO todo, Long userId);

    List<TodoDTO> internalsToDTOs(List<Todo> todos);

    default Account buildAccount(Long userId) {
        final Account account = new Account();
        account.setId(userId);
        return account;
    }
}
