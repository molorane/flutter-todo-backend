package com.blessing.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = new Random().nextLong(200);

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_date", unique = true)
    private LocalDateTime createdDate = LocalDateTime.now();

    private Boolean deleted = false;

    @PrePersist
    public void init() {
        this.createdDate = LocalDateTime.now();
        this.deleted = false;
    }
}