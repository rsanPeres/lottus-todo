package com.lottus.todo.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@Data
public class ExceptionDetails {
    private String title;
    private LocalDateTime timestamp;
    private int status;
    private String exception;
    private Map<String, String> details;
}
