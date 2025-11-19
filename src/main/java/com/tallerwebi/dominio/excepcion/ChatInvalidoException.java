package com.tallerwebi.dominio.excepcion;

public class ChatInvalidoException extends RuntimeException {
    public ChatInvalidoException(String message) {
        super(message);
    }
}