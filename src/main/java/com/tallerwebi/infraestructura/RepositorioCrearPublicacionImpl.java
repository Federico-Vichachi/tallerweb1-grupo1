package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Publicacion2;
import com.tallerwebi.dominio.RepositorioCrearPublicacion;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioCrearPublicacion")
public class RepositorioCrearPublicacionImpl implements RepositorioCrearPublicacion {

    SessionFactory sessionFactory;

    @Autowired
    public RepositorioCrearPublicacionImpl(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}


    @Override
    public Publicacion2 guardar(Publicacion2 publicacion) {
        sessionFactory.getCurrentSession().save(publicacion);
        return publicacion;
    }
}
