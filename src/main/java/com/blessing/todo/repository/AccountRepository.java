package com.blessing.todo.repository;

import com.blessing.todo.entity.Account;
import com.blessing.todo.entity.AppRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends AbstractRepository<Account> {

    Optional<Account> findByEmail(String email);

    Optional<Account> findByUsername(String username);


    List<Account> findByRolesIn(List<AppRole> roles);
}
