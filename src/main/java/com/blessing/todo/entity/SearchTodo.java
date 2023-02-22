package com.blessing.todo.entity;

import com.blessing.todo.model.TodoType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTodo {

    private TodoType todoType = TodoType.STUDY;

    private Boolean isCompleted = false;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private Boolean isDeleted = false;
}
