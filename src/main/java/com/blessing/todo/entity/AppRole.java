package com.blessing.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class AppRole extends AbstractEntity {

    @Column(name = "name", unique = true)
    private String role;

    @Column(name = "desc")
    private String desc;

    public AppRole(String role) {
        this(role, role);
    }

    public AppRole(String role, String desc) {
        this.role = role;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
