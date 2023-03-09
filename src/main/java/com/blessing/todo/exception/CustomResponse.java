package com.blessing.todo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;
import ch.qos.logback.core.status.Status;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse {
    /**
      * The status
      */
    private final Status status;
    /**
      * The message
      */
    private final String message;
    /**
      * The errors
      */
    private final Set<String> errors;
}