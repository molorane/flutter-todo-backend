/*
 * Copyright 2023 , All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 * @author  Mothusi Molorane
 * @since 1.0
 */

package com.blessing.todo.entity.enums;

public enum TaskType {

    PRAY("PRAY"),

    WORKOUT("WORKOUT"),

    FLUTTER("FLUTTER"),

    SPRING("SPRING"),

    STUDY("STUDY"),

    FOOD("FOOD"),

    ENTERTAINMENT("ENTERTAINMENT");

    private final String value;

    TaskType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static TaskType fromValue(String value) {
        for (TaskType b : TaskType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }

        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
