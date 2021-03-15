package com.quake.arena.logparser.infrastructure.http.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionResponse> bindingExceptionHandler(BindException exception) {
        List<ObjectError> allErrors = exception.getAllErrors();
        List<String> errors = new ArrayList<>();
        allErrors.forEach(e -> errors.add(e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ExceptionResponse(errors));
    }
}
