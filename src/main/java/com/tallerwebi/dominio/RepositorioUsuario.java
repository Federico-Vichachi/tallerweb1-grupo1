package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosEdicionPerfil;

public interface RepositorioUsuario {

    void guardar(Usuario usuario);
    Usuario buscarPorNombreDeUsuario(String nombreDeUsuario);
    Usuario buscarPorEmail(String email);
    Usuario buscarPorId(Long id);
    Usuario buscarPorTelefono(String telefono);
    void eliminar(Usuario usuario);
    void guardarCambiosPerfil(DatosEdicionPerfil datosEdicionPerfil);
    Usuario buscarPorEmailExcluyendoUsuario(String email, Long idUsuario);
    Usuario buscarPorNombreDeUsuarioExcluyendoUsuario(String nombreDeUsuario, Long idUsuario);
    Usuario buscarPorTelefonoExcluyendoUsuario(String telefono, Long idUsuario);


}

