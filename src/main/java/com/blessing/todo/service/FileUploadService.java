package com.blessing.todo.service;

import com.blessing.todo.model.DefaultResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileUploadService {

    ResponseEntity<DefaultResponse> uploadFile(Long userId, MultipartFile profileImage);

    void init();

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    Resource loadProfile(Long userId);

    void deleteAll();
}
