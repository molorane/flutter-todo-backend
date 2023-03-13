/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.entity;

import com.blessing.todo.entity.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Account extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Email required.")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "First name required.")
    private String firstName;

    @NotEmpty(message = "Last name required.")
    private String lastName;

    @NotEmpty(message = "Other name required.")
    private String otherName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String profile;

    private String phone;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<AppRole> roles = new HashSet<>();

    public boolean hasRole(String userRole) {
        return roles.stream()
                .filter(role -> userRole.equals(role.getName()))
                .findAny()
                .orElse(null) != null;
    }

}
