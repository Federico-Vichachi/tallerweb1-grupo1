package com.tallerwebi.dominio.excepcion;

public class NombreDeUsuarioYaUsadoException extends RuntimeException {
    public NombreDeUsuarioYaUsadoException(String message) {
        super(message);
    }
}
