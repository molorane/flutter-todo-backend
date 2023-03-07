/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.mapper;

import com.blessing.todo.entity.Account;
import com.blessing.todo.entity.Task;
import com.blessing.todo.model.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO internalToDTO(Task todo);

    @Mapping(target = "account", expression = "java(buildAccount(userId))")
    Task dtoToInternal(TaskDTO todo, Long userId);

    List<TaskDTO> internalsToDTOs(List<Task> tasks);

    default Account buildAccount(Long userId) {
        final Account account = new Account();
        account.setId(userId);
        return account;
    }
}
