package com.example.jogoDaVelha.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(PartidaNaoEncontradaException.class)
    public ResponseEntity<Object> handleNotFound(PartidaNaoEncontradaException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Não Encontrado", ex.getMessage());
    }

    @ExceptionHandler({JogadaInvalidaException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Requisição Inválida", ex.getMessage());
    }

    @ExceptionHandler(PartidaEncerradaException.class)
    public ResponseEntity<Object> handleConflict(PartidaEncerradaException ex) {
        return buildResponse(HttpStatus.CONFLICT, "Conflito", ex.getMessage());
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String erro, String mensagem) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());
        body.put("erro", erro);
        body.put("mensagem", mensagem);
        return new ResponseEntity<>(body, status);
    }
}
