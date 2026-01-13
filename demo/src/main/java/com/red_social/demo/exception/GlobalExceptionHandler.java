package com.red_social.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
// maneja excepciones globales, se aplica a todos los RestControllers
// equivale a ControllerAdvice y ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    // registra este metodo como handler de excepciones
    // significa que cuando ocurra una IllegalArgumentException
    // se llama a este metodo sin importar si esta autenticado,
    // que controller o endpoint
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "error", ex.getMessage()
                ));
        // devuelve error con el mensaje lanzado en la exception
    }
}
