/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.repository;

import com.blessing.todo.entity.Account;
import com.blessing.todo.entity.AppRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AccountRepository extends AbstractRepository<Account> {

    Optional<Account> findByEmail(String email);

    List<Account> findByRolesIn(Set<AppRole> roles);
}
