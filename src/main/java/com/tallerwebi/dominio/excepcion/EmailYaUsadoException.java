package com.tallerwebi.dominio.excepcion;

public class EmailYaUsadoException extends RuntimeException {
    public EmailYaUsadoException(String message) {
        super(message);
    }
}
