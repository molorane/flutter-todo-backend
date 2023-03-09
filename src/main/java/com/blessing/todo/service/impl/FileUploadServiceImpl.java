/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.service.impl;

import com.blessing.todo.config.StorageProperties;
import com.blessing.todo.exception.StorageException;
import com.blessing.todo.exception.StorageFileNotFoundException;
import com.blessing.todo.model.DefaultResponse;
import com.blessing.todo.service.FileUploadService;
import jakarta.annotation.PostConstruct;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final Path rootLocation;

    public FileUploadServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public ResponseEntity<DefaultResponse> uploadFile(Long userId, MultipartFile profileImage) {
        try {
            if (profileImage.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            final String extension = FilenameUtils.getExtension(profileImage.getOriginalFilename());
            final String fileName = String.format("%s.%s", userId, extension);
            validateFileExtension(extension);
            deleteImageByUserId(userId);
            final Path destinationFile = this.rootLocation.resolve(Paths.get(Objects.requireNonNull(fileName)))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = profileImage.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            return ResponseEntity.ok(new DefaultResponse().message("File saved").date(LocalDateTime.now()));
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    private void validateFileExtension(String extension) {
        if (!List.of("jpeg", "jpg", "png", "svg").contains(extension))
            throw new StorageException("File extension not supported");
    }

    private void deleteImageByUserId(Long userId) {
        final List<String> extensions = List.of("jpeg", "jpg", "png", "svg");
        extensions.forEach(extension -> {
            final String fileName = String.format("%s.%s", userId, extension);
            final Path file = load(fileName);
            if (Files.exists(file)) {
                try {
                    Files.deleteIfExists(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private String getFileNameByUserId(Long userId) {
        final List<String> extensions = List.of("jpeg", "jpg", "png", "svg");
        for (String extension : extensions) {
            final String fileName = String.format("%s.%s", userId, extension);
            final Path file = load(fileName);
            if (Files.exists(file)) {
                return fileName;
            }
        }
        throw new StorageException("Profile for user not found");
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public Resource loadProfile(Long userId) {
        return loadAsResource(getFileNameByUserId(userId));
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
