/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.mapper;

import com.blessing.todo.entity.Account;
import com.blessing.todo.entity.AppRole;
import com.blessing.todo.model.AccountDTO;
import com.blessing.todo.model.AccountRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountRoleDTO internalToDTO(AppRole appRole);

    AccountDTO internalToDTO(Account account);

    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "roles", ignore = true)
    Account dtoToInternal(AccountDTO accountDTO);
}
