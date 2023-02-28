package com.blessing.todo.entity.enums;

public enum TodoType {

    PRAYER("PRAYER"),

    GYM("GYM"),

    PREACH("PREACH"),

    FLUTTER("FLUTTER"),

    SPRING("SPRING"),

    STUDY("STUDY"),

    FOOD("FOOD"),

    ENTERTAINMENT("ENTERTAINMENT");

    private final String value;

    TodoType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static TodoType fromValue(String value) {
        for (TodoType b : TodoType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
