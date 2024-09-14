package com.lottus.todo.core.exception;


import jakarta.persistence.EntityNotFoundException;
import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ExceptionDetails> handleDataAccessException(DataAccessException ex) {
        Map<String, String> detailsMap = new HashMap<>();
        detailsMap.put(ex.getCause().toString(), ex.getMessage());

        ExceptionDetails errorDetails = new ExceptionDetails(
                "Database Error",
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getClass().getName(),
                detailsMap
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<ExceptionDetails> handleJpaSystemException(JpaSystemException ex) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        Map<String, String> detailsMap = new HashMap<>();
        detailsMap.put(ex.getCause().toString(), ex.getMessage());

        ExceptionDetails errorDetails = new ExceptionDetails(
                "JPA System Exception",
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                rootCause.getClass().getName(),
                detailsMap
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> detailsMap = new HashMap<>();
        detailsMap.put(ex.getCause().toString(), ex.getMessage());

        ExceptionDetails errorDetails = new ExceptionDetails(
                "Entity Not Found",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getClass().getName(),
                detailsMap
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, String> detailsMap = new HashMap<>();
        detailsMap.put(ex.getCause().toString(), ex.getMessage());

        ExceptionDetails errorDetails = new ExceptionDetails(
                "Entity Not Found",
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getClass().getName(),
                detailsMap
        );

        return new ResponseEntity<>(errorDetails, status);
    }
}
