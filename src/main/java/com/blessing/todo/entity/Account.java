package com.blessing.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "Username required.")
    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true)
    @NotEmpty(message = "Email Required.")
    @Email(message = "Invalid email format")
    private String email;

    @Column(nullable = false)
    @Size(min = 4, message = "Password must at least be 4 characters")
    private String password;

    @Transient
    @Size(min = 4, message = "Password must at least be 4 characters")
    private String password_confirm;

    @Column(columnDefinition = "int default 1")
    private int status;

    private boolean isLocked;

    private boolean isActive;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expiry_date", unique = true)
    private LocalDate expiryDate = LocalDate.now().plusMonths(1);

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<AppRole> roles = new HashSet<>();

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AccountInfo accountInfo;

    public Account(String username, String email, String password, int status, Set<AppRole> roles) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public boolean accountExpired() {
        return !expiryDate.isAfter(LocalDate.now());
    }

    public boolean hasRole(String userRole) {
        return (roles.stream()
                .filter(role -> userRole.equals(role.getRole()))
                .findAny()
                .orElse(null) != null);
    }

}
