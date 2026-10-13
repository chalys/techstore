package com.techstore.exception;

public class PrecioInvalidoException extends Exception {
    public PrecioInvalidoException(String mensaje) {
        super(mensaje);
    }

    public PrecioInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

}