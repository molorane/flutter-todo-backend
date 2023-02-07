package com.blessing.todo.mapper.destination;

import com.blessing.todo.entity.enums.TodoType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class TodoDTO {

    private Long id;
    private TodoType todoType;
    private String title;
    private Boolean completed;
    private LocalDate dueDate;
    private String description;
    private LocalDateTime createdDate;
    private Boolean deleted;
}
