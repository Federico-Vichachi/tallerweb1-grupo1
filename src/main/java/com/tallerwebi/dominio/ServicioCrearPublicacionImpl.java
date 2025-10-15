package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioCrearPublicacionImpl implements ServicioCrearPublicacion {

    private RepositorioCrearPublicacion repositorioCrearPublicacion;

    @Autowired
    public ServicioCrearPublicacionImpl(RepositorioCrearPublicacion repositorioCrearPublicacion) {this.repositorioCrearPublicacion = repositorioCrearPublicacion;}


    @Override
    public Publicacion2 guardar(Publicacion2 publicacion) {
        return repositorioCrearPublicacion.guardar(publicacion);
    }
}
