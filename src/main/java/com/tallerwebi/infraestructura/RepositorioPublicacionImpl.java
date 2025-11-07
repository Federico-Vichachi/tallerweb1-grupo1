package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import org.hibernate.Criteria;
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

    @Override
    public List<Publicacion> buscarPorNombre(String nombre) {
        return this.buscarPublicacionesPorFiltros(null, nombre, null, null);
    }

    @Override
    public List<Publicacion> buscarPorProvincia(Provincias provincia) {
        return this.buscarPublicacionesPorFiltros(null, null, provincia, null);
    }

    @Override
    public List<Publicacion> buscarPorLocalidad(String localidad) {
        return this.buscarPublicacionesPorFiltros(null, null, null, localidad);
    }

    @Override
    public List<Publicacion> buscarPorTipos(List<Class<? extends Publicacion>> tipos){
        return (List<Publicacion>) sessionFactory.getCurrentSession()
                .createCriteria(Publicacion.class)
                .add(Restrictions.in("class", tipos))
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public List<Publicacion> buscarPublicacionesPorFiltros(String categoria, String nombre, Provincias provincia, String localidad) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Publicacion.class);

        if (categoria != null && !categoria.trim().isEmpty()) {
            switch (categoria.toUpperCase()) {
                case "ADOPCION": criteria.add(Restrictions.eq("class", PublicacionAdopcion.class)); break;
                case "PERDIDO": criteria.add(Restrictions.eq("class", PublicacionPerdido.class)); break;
                case "ENCONTRADO": criteria.add(Restrictions.eq("class", PublicacionEncontrado.class)); break;
                case "RECAUDACION": criteria.add(Restrictions.eq("class", PublicacionRecaudacion.class)); break;
                case "SALUD": criteria.add(Restrictions.eq("class", PublicacionSalud.class)); break;
            }
        }
        if (nombre != null && !nombre.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("titulo", "%" + nombre + "%"));
        }
        if (provincia != null) {
            criteria.add(Restrictions.eq("provincia", provincia));
        }
        if (localidad != null && !localidad.trim().isEmpty()) {
            criteria.add(Restrictions.ilike("localidad", "%" + localidad + "%"));
        }

        criteria.addOrder(Order.desc("id"));
        return (List<Publicacion>) criteria.list();
    }

    @Override
    public void actualizar(Publicacion publicacion) {
        sessionFactory.getCurrentSession().update(publicacion);
    }
}