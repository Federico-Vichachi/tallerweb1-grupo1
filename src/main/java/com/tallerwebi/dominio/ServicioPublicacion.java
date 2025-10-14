package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioPublicacion {

    List<Publicacion> obtenerTodasLasPublicaciones();
    List<Publicacion> buscarPublicacionesPorCategoria(String categoria);

}
