package com.civa.futbolista_posicion.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String errorMessage = error.getField() + ": " + error.getDefaultMessage();
            errors.add(errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse("Error en la validación", errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
