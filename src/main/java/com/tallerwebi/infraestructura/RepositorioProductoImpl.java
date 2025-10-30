package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Producto;
import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.RepositorioProducto;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioProducto")
public class RepositorioProductoImpl implements RepositorioProducto {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioProductoImpl(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    @Override
    public void guardar(Producto producto) {
        sessionFactory.getCurrentSession().save(producto);
    }

    @Override
    public List<Producto> buscarProductos() {
        return (List<Producto>) sessionFactory.getCurrentSession()
                .createCriteria(Producto.class)
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public Producto buscarPorId(Long id) {
        return sessionFactory.getCurrentSession().get(Producto.class, id);
    }
}
