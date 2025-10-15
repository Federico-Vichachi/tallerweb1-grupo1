package com.tallerwebi.dominio;
import java.util.List;

public interface RepositorioPublicacion {
    void guardar(Publicacion publicacion);
    Publicacion buscarPorId(Long id);
    List<Publicacion> buscarTodas();
    List<Publicacion> buscarPorCategoria(TipoPublicacion tipo);
}
