/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.mapper;

import com.blessing.todo.dashboard.ITaskCountToday;
import com.blessing.todo.dashboard.ITaskGroupCount;
import com.blessing.todo.model.TaskCountToday;
import com.blessing.todo.model.TaskGroupCount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TaskDashboardMapper {

    TaskDashboardMapper INSTANCE = Mappers.getMapper(TaskDashboardMapper.class);

    TaskGroupCount internalToDTO(ITaskGroupCount todo);

    List<TaskGroupCount> internalsToDTOs(List<ITaskGroupCount> todoGroupCountList);

    TaskCountToday internalToDTO(ITaskCountToday todoCountToday);

    List<TaskCountToday> internalsToTaskCountToday(List<ITaskCountToday> todoCountTodayList);
}
