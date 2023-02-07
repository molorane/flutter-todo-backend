package com.blessing.todo.entity;

import com.blessing.todo.entity.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class AccountInfo extends AbstractEntity {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account account;

    @NotEmpty(message = "last name Required.")
    private String last_name;

    @NotEmpty(message = "first name Required.")
    private String first_name;

    private String other_name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String profile;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
}
