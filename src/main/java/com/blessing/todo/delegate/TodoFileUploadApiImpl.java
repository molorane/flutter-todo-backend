package com.blessing.todo.delegate;

import com.blessing.todo.api.TodoFileUploadApi;
import com.blessing.todo.model.DefaultResponse;
import com.blessing.todo.service.TodoFileUploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
public class TodoFileUploadApiImpl implements TodoFileUploadApi {

    private TodoFileUploadService todoFileUploadService;

    @Override
    public ResponseEntity<DefaultResponse> uploadFile(Long userId, MultipartFile profileImage) {
        return todoFileUploadService.uploadFile(userId, profileImage);
    }

}
