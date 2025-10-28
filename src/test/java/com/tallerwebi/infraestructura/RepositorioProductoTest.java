package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioProductoTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RepositorioProducto repositorioProducto;


    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnProducto(){
        Producto producto = givenTengoUnproducto("Voraz 10kg", "Bolsa de 10kg calidad economica, para perros adultos", 12000, 122, 5, "Alimento.jpg");
        whenGuardoElProducto(producto);
        thenSePuedeGuardarCorrectamenteElProducto(producto);
    }

    private Producto givenTengoUnproducto(String nombre, String descripcion, int precioEnCentavos, int precioEnPuntos, int stock ,String imagen) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecioEnCentavos(precioEnCentavos);
        producto.setPrecioEnPuntos(precioEnPuntos);
        producto.setStock(stock);
        producto.setImagen(imagen);
        repositorioProducto.guardar(producto);
        return producto;
    }

    private void whenGuardoElProducto(Producto producto) {
        repositorioProducto.guardar(producto);
    }

    private void thenSePuedeGuardarCorrectamenteElProducto(Producto producto) {
        Producto guardada = sessionFactory.getCurrentSession()
                .get(Producto.class, producto.getId());
        assertThat(guardada, notNullValue());
        assertThat(guardada.getNombre(), equalTo(producto.getNombre()));
    }
}
