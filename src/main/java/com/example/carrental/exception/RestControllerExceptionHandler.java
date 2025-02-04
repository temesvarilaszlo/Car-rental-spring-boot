package com.example.carrental.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.example.carrental.restcontroller")
public class RestControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        // Collect validation errors
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        if (!exception.getBindingResult().getGlobalErrors().isEmpty()) {
            errors.put("otherError", exception.getBindingResult().getGlobalError().getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<Map<String, String>> handleCustomValidationException(CustomValidationException exception) {
        // Collect validation errors
        Map<String, String> errors = exception.getErrors();

        return ResponseEntity.badRequest().body(errors);
    }
}
