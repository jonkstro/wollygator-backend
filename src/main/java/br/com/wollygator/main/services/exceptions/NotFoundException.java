package br.com.wollygator.main.services.exceptions;

public class NotFoundException extends RuntimeException {
    // Exceção personalizada para requisições que não encontrem o recurso
    public NotFoundException(String msg) {
        super(msg);
    }
}
