/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

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
