package com.tallerwebi.dominio;

public enum Roles {
    USUARIO_COMUN("Usuario Común"),
    ORGANIZACION("Organización"),
    VETERINARIO("Veterinario");

    private final String nombreFormateado;

    Roles(String nombreFormateado) {
        this.nombreFormateado = nombreFormateado;
    }

    public String getNombreFormateado() {
        return nombreFormateado;
    }
}
