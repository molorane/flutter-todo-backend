package com.blessing.todo.service.impl;

import com.blessing.todo.model.DefaultResponse;
import com.blessing.todo.service.TodoFileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class TodoFileUploadServiceImpl implements TodoFileUploadService {

    @Override
    public ResponseEntity<DefaultResponse> uploadFile(Long userId, MultipartFile profileImage) {
        return null;
    }
}
