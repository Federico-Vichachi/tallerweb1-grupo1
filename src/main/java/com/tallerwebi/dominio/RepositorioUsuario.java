package com.tallerwebi.dominio;

public interface RepositorioUsuario {

    void guardar(Usuario usuario);
    Usuario buscarPorNombreDeUsuario(String nombreDeUsuario);
    Usuario buscarPorEmail(String email);
    Usuario buscarPorId(Long id);
    void eliminar(Usuario usuario);
}

