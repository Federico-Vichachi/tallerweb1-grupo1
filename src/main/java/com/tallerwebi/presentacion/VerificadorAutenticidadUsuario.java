package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Roles;
import com.tallerwebi.dominio.Usuario;

import javax.servlet.http.HttpServletRequest;

public class VerificadorAutenticidadUsuario {

    private HttpServletRequest request;

    public VerificadorAutenticidadUsuario(HttpServletRequest request) {
        this.request = request;
    }

    public boolean verificarUsuarioConSesionIniciada() {
        return request.getSession().getAttribute("usuarioLogueado") != null;
    }

    public boolean verificarUsuarioConRolOrganizacion() {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        Roles rol = usuario.getRol();
        return rol != null && rol.equals("ORGANIZACION");
    }

    public boolean verificarUsuarioConRolVeterinario() {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        Roles rol = usuario.getRol();
        return rol != null && rol.equals("VETERINARIO");
    }

}
