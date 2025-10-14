package com.tallerwebi.dominio.excepcion;

public class FormatoDeEmailInvalidoException extends RuntimeException {
    public FormatoDeEmailInvalidoException(String message) {
        super(message);
    }
}
