package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.RepositorioPublicacion;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("repositorioPublicacion")
public class RepositorioPublicacionImpl implements RepositorioPublicacion {

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioPublicacionImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Publicacion guardar(Publicacion publicacion) {
        sessionFactory.getCurrentSession().save(publicacion);
        return publicacion;
    }

    @Override
    public Publicacion buscarPorId(Long id) {
        return sessionFactory.getCurrentSession().get(Publicacion.class, id);
    }

    @Override
    public List<Publicacion> buscarTodas() {
        return (List<Publicacion>) sessionFactory.getCurrentSession()
                .createCriteria(Publicacion.class)
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public <T extends Publicacion> List<T> buscarPorTipo(Class<T> tipo) {
        return (List<T>) sessionFactory.getCurrentSession()
                .createCriteria(tipo)
                .addOrder(Order.desc("id"))
                .list();
    }
}