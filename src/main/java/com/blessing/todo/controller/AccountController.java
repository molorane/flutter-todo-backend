package com.blessing.todo.controller;

import com.blessing.todo.entity.Account;
import com.blessing.todo.entity.AppRole;
import com.blessing.todo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public List<Account> accounts() {
        final AppRole appRole2 = new AppRole();
        appRole2.setId(1L);

        final List<Account> list = accountRepository.findByRolesIn(Set.of(appRole2));

        return list;
    }

//    @GetMapping("roles")
//    public Set<AppRole> appRoles() {
//
//        final Account account = accountRepository.findByAccountId(2014098616L);
//
//        return account.getRoles();
//    }
}
