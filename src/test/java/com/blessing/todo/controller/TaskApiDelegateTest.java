/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.controller;

import com.blessing.todo.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@ActiveProfiles("test")
public class TaskApiDelegateTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findTasksByUserId_givenValidUserIdThenReturnStatusOk() throws Exception {
        this.mockMvc.perform(get("http://localhost:2023/todo/v1/tasks/2014098616/user"))
                .andExpect(status().isOk());
    }

    @Test
    public void findTasksByUserId_givenInValidUserIdThenReturnEmptyPage() throws Exception {
        //Setup
        final PageRequest pageRequest = PageRequest.of(1, 10);

        //Expectation
        final Page<Task> taskPage = new PageImpl<>(Collections.emptyList(), pageRequest, 0);

        //Mocks
        //given(taskRepository.findAllTasksByAccountIdAndIsDeletedFalse(2033L, pageRequest)).willReturn(taskPage);

        //Exercise SUT
        this.mockMvc.perform(get("http://localhost:2023/todo/v1/tasks/2014098616/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                //.andExpect(jsonPath("$.content").value(0));
    }
}
