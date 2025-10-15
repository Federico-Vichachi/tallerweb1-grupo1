package com.tallerwebi.dominio.excepcion;

public class CategoriaInvalidaException extends RuntimeException {
    public CategoriaInvalidaException(String message) {
        super(message);
    }
}