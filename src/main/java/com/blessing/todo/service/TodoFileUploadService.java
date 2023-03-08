package com.blessing.todo.service;

import com.blessing.todo.model.DefaultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface TodoFileUploadService {
    ResponseEntity<DefaultResponse> uploadFile(Long userId, MultipartFile profileImage);
}
