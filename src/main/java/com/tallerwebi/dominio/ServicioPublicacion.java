package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioPublicacion {

    Publicacion guardar(Publicacion publicacion);
    List<Publicacion> obtenerTodasLasPublicaciones();
    List<Publicacion> buscarPublicacionesPorCategoria(String categoria);
}
