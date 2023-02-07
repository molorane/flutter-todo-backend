package com.blessing.todo.service;


import com.blessing.todo.entity.Account;

import java.util.Optional;

public interface AccountService extends AbstractService<Account> {

    Account addAccount(Account user);

    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

}
