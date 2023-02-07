package com.blessing.todo.entity;

import com.blessing.todo.entity.enums.TodoType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Todo extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Account account;
    @Enumerated(EnumType.STRING)
    private TodoType todoType = TodoType.STUDY;
    private String title;
    private Boolean completed;
    private LocalDate dueDate;
    private String description;
}
