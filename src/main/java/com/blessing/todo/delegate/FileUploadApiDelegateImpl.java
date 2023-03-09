package com.blessing.todo.delegate;

import com.blessing.todo.api.FileUploadApiDelegate;
import com.blessing.todo.model.DefaultResponse;
import com.blessing.todo.service.FileUploadService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class FileUploadApiDelegateImpl implements FileUploadApiDelegate {

    private FileUploadService fileUploadService;

    @Override
    public ResponseEntity<DefaultResponse> uploadProfileImage(Long userId, MultipartFile profileImage) {
        return fileUploadService.uploadFile(userId, profileImage);
    }

    @Override
    public ResponseEntity<Resource> loadProfileImage(Long userId) {
        return ResponseEntity.ok(fileUploadService.loadProfile(userId));
    }
}
