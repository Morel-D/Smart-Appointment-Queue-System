package com.example.queue_flow.project.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import com.example.queue_flow.project.model.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for(FieldError error: ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ApiResponse<>(
            false, 
            null,
            "feild_validation_errors",
            errors, 
            LocalDateTime.now())
        );
    }


    // Business errors
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handlerBusinessErrors(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            new ApiResponse<>(
            false,
            null,
            ex.getMessage(),
            null,
            LocalDateTime.now()
        )
        );
    }


    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<?> handleOptimisticLocking(ObjectOptimisticLockingFailureException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            new ApiResponse<>(
                false, 
                null, 
                "This time slot was just booked by someone else.",
                 null, 
                 LocalDateTime.now()
                )
        );
    }
}
