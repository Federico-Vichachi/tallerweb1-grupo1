package com.tallerwebi.dominio.excepcion;

public class ContraseniaNoContieneNumeroException extends RuntimeException {

    public ContraseniaNoContieneNumeroException() {
        super("La contraseña debe contener al menos un número.");
    }

}
