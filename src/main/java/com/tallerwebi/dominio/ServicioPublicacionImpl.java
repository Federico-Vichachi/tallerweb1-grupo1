package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.CategoriaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioPublicacionImpl implements ServicioPublicacion {

    private final RepositorioPublicacion repositorioPublicacion;

    @Autowired
    public ServicioPublicacionImpl(RepositorioPublicacion repositorioPublicacion) {
        this.repositorioPublicacion = repositorioPublicacion;
    }

    @Override
    public Publicacion guardar(Publicacion publicacion) {
        return repositorioPublicacion.guardar(publicacion);
    }

    @Override
    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return repositorioPublicacion.buscarTodas();
    }

    @Override
    public List<Publicacion> buscarPublicacionesPorCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            return obtenerTodasLasPublicaciones();
        }

        switch (categoria.toUpperCase()) {
            case "ADOPCION":
                return (List<Publicacion>) (List<?>) repositorioPublicacion.buscarPorTipo(PublicacionAdopcion.class);
            case "PERDIDO":
                return (List<Publicacion>) (List<?>) repositorioPublicacion.buscarPorTipo(PublicacionPerdido.class);
            case "ENCONTRADO":
                return (List<Publicacion>) (List<?>) repositorioPublicacion.buscarPorTipo(PublicacionEncontrado.class);
            case "RECAUDACION":
                return (List<Publicacion>) (List<?>) repositorioPublicacion.buscarPorTipo(PublicacionRecaudacion.class);
            case "SALUD":
                return (List<Publicacion>) (List<?>) repositorioPublicacion.buscarPorTipo(PublicacionSalud.class);
            default:
                throw new CategoriaInvalidaException("La categoría '" + categoria + "' no es válida.");
        }
    }
}