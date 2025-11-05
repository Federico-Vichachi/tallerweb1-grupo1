package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioPublicacion {

    Publicacion guardar(Publicacion publicacion);

    List<Publicacion> obtenerTodasLasPublicaciones();
    List<Publicacion> buscarPublicacionesPorCategoria(String categoria);
    List<Publicacion> buscarPorNombre(String nombre);
    List<Publicacion> buscarPorProvincia(Provincias provincia);
    List<Publicacion> buscarPorLocalidad(String localidad);
    List<Publicacion> buscarPublicacionesCercanas(Double latitud, Double longitud, Double radioKm);
    List<Publicacion> buscarPublicacionesConFiltros (DatosFiltro datosFiltro);
    void actualizar(Publicacion publicacion);
}