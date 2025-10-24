package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioPublicacion {
    Publicacion guardar(Publicacion publicacion);
    Publicacion buscarPorId(Long id);
    List<Publicacion> buscarTodas();
    <T extends Publicacion> List<T> buscarPorTipo(Class<T> tipo);

}
