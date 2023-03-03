package com.blessing.todo.controller;

import com.blessing.todo.model.TodoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("pages")
    public Page<TodoDTO> test(Pageable pageable) {
        System.out.println(pageable);
        return null;
    }
}
