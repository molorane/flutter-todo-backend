/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.service;

import com.blessing.todo.entity.Account;

import java.util.Optional;

public interface AccountService extends AbstractService<Account> {

    Optional<Account> findByEmail(String email);
}
