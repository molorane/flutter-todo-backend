package com.blessing.todo.repository;

import com.blessing.todo.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends AbstractRepository<Account> {

    Optional<Account> findByEmail(String email);

    Optional<Account> findByUsername(String username);


}
