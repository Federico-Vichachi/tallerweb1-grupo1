package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.CategoriaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collections;
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
    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return repositorioPublicacion.buscarTodas();
    }

    @Override
    public List<Publicacion> buscarPublicacionesPorCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            return obtenerTodasLasPublicaciones();
        }

        if (!validarCategoria(categoria)) {
            throw new CategoriaInvalidaException("La categoría '" + categoria + "' no es valida.");
        }

        TipoPublicacion tipo = TipoPublicacion.valueOf(categoria.toUpperCase());
        return repositorioPublicacion.buscarPorCategoria(tipo);
    }


    private boolean validarCategoria(String categoria) {
        if (categoria == null) {
            return false;
        }
        for (TipoPublicacion tipo : TipoPublicacion.values()) {
            if (tipo.name().equalsIgnoreCase(categoria)) {
                return true;
            }
        }
        return false;
    }
}