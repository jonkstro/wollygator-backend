package br.com.wollygator.main.services.exceptions;

public class DataBaseException extends RuntimeException {
    public DataBaseException(String msg) {
        super(msg);
    }
}
