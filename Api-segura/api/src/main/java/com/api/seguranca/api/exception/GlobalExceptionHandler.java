package com.api.seguranca.api.exception;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConflitoViagemException.class)
    public ResponseEntity<?> handleConflitoViagem(
            ConflitoViagemException ex
    ){
        return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(Map.of(
            "timeStamp", LocalDate.now(),
            "status", 409,
            "error","Conflito de Viagem",
            "message", ex.getMessage()
        ));
    }
    
}
