package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioPuntosImpl implements ServicioPuntos {

    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioPuntosImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void sumarPuntos(Usuario usuario, Publicacion publicacion) {
        int puntos = publicacion.puntosPorCreacion();
        usuario.sumarPuntos(puntos);
        repositorioUsuario.guardar(usuario);
    }
}
