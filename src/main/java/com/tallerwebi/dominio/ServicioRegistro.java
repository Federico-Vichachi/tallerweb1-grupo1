package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.Roles;

public interface ServicioRegistro {
    Usuario registrarUsuario(String email, String contrasenia, Roles roles);
}
