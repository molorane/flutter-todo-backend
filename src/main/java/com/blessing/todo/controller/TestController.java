package com.blessing.todo.controller;

import com.blessing.todo.model.TaskDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test", description = "Api operations for the test method")
public class TestController {


    @GetMapping("pages")
    public Page<TaskDTO> test(Pageable pageable) {
        System.out.println(pageable);
        return null;
    }
}
