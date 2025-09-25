package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.Roles;

public interface SerivicioRegistro {
    Usuario registrar(String email, String contrasenia, String repeticionDeContrasenia, Roles roles);
}
