package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioPublicacion {
    Publicacion guardar(Publicacion publicacion);
    Publicacion buscarPorId(Long id);
    List<Publicacion> buscarTodas();
    <T extends Publicacion> List<T> buscarPorTipo(Class<T> tipo);
    List<Publicacion> buscarPorNombre(String nombre);
    List<Publicacion> buscarPorProvincia(Provincias provincia);
    List<Publicacion> buscarPorLocalidad(String localidad);
    List<Publicacion> buscarPorTipos(List<Class<? extends Publicacion>> tipos);
    List<Publicacion> buscarPublicacionesPorFiltros(String categoria, String nombre, Provincias provincia, String localidad);
    void actualizar(Publicacion publicacion);
    List<Publicacion> buscarPorUsuario(Usuario usuario);
}
