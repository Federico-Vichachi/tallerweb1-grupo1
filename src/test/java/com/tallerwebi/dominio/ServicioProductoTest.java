package com.tallerwebi.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;

public class ServicioProductoTest {

    private RepositorioProducto repositorioProductoMock;
    private ServicioProducto servicioProducto;

    @BeforeEach
    public void setUp() {
        repositorioProductoMock = mock(RepositorioProducto.class);
        servicioProducto = new ServicioProductoImpl(repositorioProductoMock);
    }

    @Test
    public void siLosDatosDelProductoSonCorrectosElProductoSeGuardaExitosamente() {
        givenNoExisteProducto();
        Producto producto = whenCreoElProductoCon("Voraz 10kg", "Bolsa de 10kg calidad economica, para perros adultos", 12000, 122, 5, "Alimento.jpg");
        thenElProductoSeGuardaCorrectamente(producto);
    }

    private void givenNoExisteProducto() {
    }

    private Producto whenCreoElProductoCon(String nombre, String descripcion, int precioEnCentavos, int precioEnPuntos, int stock ,String imagen) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecioEnCentavos(precioEnCentavos);
        producto.setPrecioEnPuntos(precioEnPuntos);
        producto.setStock(stock);
        producto.setImagen(imagen);
        servicioProducto.guardar(producto);
        return producto;
    }

    private void thenElProductoSeGuardaCorrectamente(Producto producto) {
        assertThat(producto, notNullValue());
    }


}
