/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.mapper;

import com.blessing.todo.entity.enums.TaskType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TaskTypeMapper {

    TaskTypeMapper INSTANCE = Mappers.getMapper(TaskTypeMapper.class);

    TaskType dtoToInternal(com.blessing.todo.model.TaskType todo);
}
