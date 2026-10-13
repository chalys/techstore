package com.techstore.exception;

public class StockInsuficienteException extends Exception {
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }

    public StockInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }

}