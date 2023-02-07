package com.blessing.todo.exception;

import lombok.Getter;

public class DataNotFoundException extends RuntimeException {

    @Getter
    private final String errorMessage;

    public DataNotFoundException(String message) {
        super(message);
        this.errorMessage = message;
    }
}
