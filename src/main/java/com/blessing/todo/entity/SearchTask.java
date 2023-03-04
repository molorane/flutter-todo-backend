package com.blessing.todo.entity;

import com.blessing.todo.model.TaskType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTask {

    private TaskType todoType = TaskType.STUDY;

    private Boolean isCompleted = false;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private Boolean isDeleted = false;
}
