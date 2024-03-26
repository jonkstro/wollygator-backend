package br.com.wollygator.main.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.wollygator.main.services.exceptions.DataBaseException;
import br.com.wollygator.main.services.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;

// Anotação para interceptar os erros
@ControllerAdvice
public class ControllerExceptionHandler {
    // Tratamento personalizado da custom exception NotFoundException:
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(NotFoundException e, HttpServletRequest request) {
        String errorMsg = "Recurso não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(
                Instant.now(), // momento do erro
                status.value(), // 404
                errorMsg, // recurso nao encontrado
                e.getMessage(), // msg que vem do service quando estoura a exception
                request.getRequestURI() // URI da requisicao ex.: "/user/1"
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> databaseError(DataBaseException e, HttpServletRequest request) {
        String errorMsg = "Erro de operação em banco de dados";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(
                Instant.now(), // momento do erro
                status.value(), // 404
                errorMsg, // recurso nao encontrado
                e.getMessage(), // msg que vem do service quando estoura a exception
                request.getRequestURI() // URI da requisicao ex.: "/user/1"
        );
        return ResponseEntity.status(status).body(error);
    }

    // Add outras exceções personalizadas

}
