package com.jimmy.salaryprediction.controller;

import com.jimmy.salaryprediction.exceptions.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Map<String, Object>> handleException(Exception exception) {
        return ResponseEntity.badRequest().body(new ApiError(exception.getMessage()).toMap());
    }
}
