package com.blessing.todo.mapper;

import com.blessing.todo.entity.Task;
import com.blessing.todo.model.PageTaskDTO;
import com.blessing.todo.model.PageableObject;
import com.blessing.todo.model.SortObject;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {TaskMapper.class})
public interface PaginationMapper {

    PaginationMapper INSTANCE = Mappers.getMapper(PaginationMapper.class);

    SortObject sortObject(Sort sort);

    PageableObject pageableObject(Pageable pageable);

    PageTaskDTO pageTaskDTO(Page<Task> pages);
}
