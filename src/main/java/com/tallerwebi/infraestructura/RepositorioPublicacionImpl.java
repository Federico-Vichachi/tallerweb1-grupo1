package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.RepositorioPublicacion;
import com.tallerwebi.dominio.TipoPublicacion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
    public void guardar(Publicacion publicacion) {
        sessionFactory.getCurrentSession().save(publicacion);
    }

    @Override
    public Publicacion buscarPorId(Long id) {
        return sessionFactory.getCurrentSession().get(Publicacion.class, id);
    }

    @Override
    public List<Publicacion> buscarTodas() {
        return (List<Publicacion>) sessionFactory.getCurrentSession()
                .createCriteria(Publicacion.class)
                .addOrder(Order.desc("fechaPublicacion"))
                .list();
    }

    @Override
    public List<Publicacion> buscarPorCategoria(TipoPublicacion tipo) {
        return (List<Publicacion>) sessionFactory.getCurrentSession()
                .createCriteria(Publicacion.class)
                .add(Restrictions.eq("tipo", tipo))
                .addOrder(Order.desc("fechaPublicacion"))
                .list();
    }
}
