package com.tallerwebi.presentacion;

import com.mercadopago.resources.preference.Preference;
import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class ControladorProductoTest {

    private ControladorProducto controladorProducto;
    private ServicioProducto servicioProductoMock;
    private ServicioPuntos servicioPuntosMock;
    private ServicioPago servicioPagoMock;
    private HttpSession sessionMock;
    private RedirectAttributes redirectAttributesMock;
    private DatosProducto datosProducto;

    private Usuario usuario;
    private Producto producto;
    private ModelAndView mav;

    @BeforeEach
    public void setUp() {
        servicioProductoMock = mock(ServicioProducto.class);
        servicioPuntosMock = mock(ServicioPuntos.class);
        servicioPagoMock = mock(ServicioPago.class);
        sessionMock = mock(HttpSession.class);
        redirectAttributesMock = mock(RedirectAttributes.class);

        controladorProducto = new ControladorProducto(servicioProductoMock, servicioPuntosMock, servicioPagoMock);}

    // --- TEST CREAR PRODUCTO ---
    @Test
    public void dadoQueExistenDatosValidosDeProductoCuandoCreoElProductoEntoncesSeMuestraLaVistaDeTienda() {
        givenExistenDatosDeUnProducto();

        ModelAndView mav = whenCreoElProducto();

        thenSeMuestraLaVistaDeTienda(mav);
    }

    private void givenExistenDatosDeUnProducto() {
        datosProducto = new DatosProducto();
        datosProducto.setNombre("Voraz 10kg");
        datosProducto.setDescripcion("Bolsa de 10kg calidad económica, para perros adultos");
        datosProducto.setPrecio(BigDecimal.valueOf(12000));
        datosProducto.setPrecioEnPuntos(122);
        datosProducto.setStock(5);
        datosProducto.setImagen("Alimento.jpg");
    }

    private ModelAndView whenCreoElProducto() {
        return controladorProducto.crearProducto(datosProducto);
    }

    private void thenSeMuestraLaVistaDeTienda(ModelAndView mav) {
        assertThat(mav, is(notNullValue()));
        assertThat(mav.getViewName(), is("redirect:/tienda"));
    }

    @Test
    public void dadoQueUsuarioLogueadoYStockDisponibleCuandoCompraEntoncesRedirigeALinkDePago() {
        givenUsuarioLogueadoYProductoValido();
        mav = whenCompraProducto();
        thenRedirigeALinkPago(mav);
    }

    private void givenUsuarioLogueadoYProductoValido() {
        usuario = new Usuario();
        usuario.setPuntos(500);
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Collar antipulgas");
        producto.setStock(10);
        producto.setPrecioEnPuntos(100);

        when(sessionMock.getAttribute("usuarioLogueado")).thenReturn(usuario);
        when(servicioProductoMock.buscarPorId(1L)).thenReturn(producto);

        Preference pref = mock(Preference.class);
        when(pref.getInitPoint()).thenReturn("http://pago.mp/test");
        when(servicioPagoMock.generarLinkPago(producto, 1)).thenReturn(pref);
    }

    private ModelAndView whenCompraProducto() {
        return controladorProducto.comprarProducto(1L, 1, sessionMock);
    }

    private void thenRedirigeALinkPago(ModelAndView mav) {
        assertThat(mav.getViewName(), is("redirect:http://pago.mp/test"));
    }

    // --- CANJE EXITOSO ---
    @Test
    public void dadoQueUsuarioConPuntosSuficientesCuandoCanjeaEntoncesDescuentaStockYExito() {
        givenUsuarioConPuntosSuficientes();
        whenCanjeaProducto();
        thenCanjeExitoso();
    }

    private void givenUsuarioConPuntosSuficientes() {
        usuario = new Usuario();
        usuario.setPuntos(200);
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Pelota");
        producto.setPrecioEnPuntos(100);
        producto.setStock(5);

        when(sessionMock.getAttribute("usuarioLogueado")).thenReturn(usuario);
        when(servicioProductoMock.buscarPorId(1L)).thenReturn(producto);
        when(servicioPuntosMock.gastarPuntos(usuario, producto,1)).thenReturn(true);
        when(servicioProductoMock.listarProductos()).thenReturn(Collections.singletonList(producto));
    }

    private void whenCanjeaProducto() {
        mav = controladorProducto.canjearProducto(1L, 1, sessionMock, redirectAttributesMock);
    }

    private void thenCanjeExitoso() {
        verify(servicioProductoMock).descontarStock(producto, 1);
        assertThat(mav.getViewName(), is("redirect:/tienda"));
    }

    // --- CANJE SIN PUNTOS ---
    @Test
    public void dadoQueUsuarioSinPuntosSuficientesCuandoCanjeaEntoncesMuestraError() {
        givenUsuarioSinPuntos();
        whenCanjeaProducto();
        thenErrorPorFaltaDePuntos();
    }

    private void givenUsuarioSinPuntos() {
        usuario = new Usuario();
        usuario.setPuntos(0);
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Cucha");
        producto.setPrecioEnPuntos(100);
        producto.setStock(10);

        when(sessionMock.getAttribute("usuarioLogueado")).thenReturn(usuario);
        when(servicioProductoMock.buscarPorId(1L)).thenReturn(producto);
        when(servicioPuntosMock.gastarPuntos(usuario, producto,1)).thenReturn(false);
        when(servicioProductoMock.listarProductos()).thenReturn(Collections.singletonList(producto));
    }

    private void thenErrorPorFaltaDePuntos() {
        verify(servicioProductoMock, never()).descontarStock(any(), anyInt());
        assertThat(mav.getViewName(), is("redirect:/tienda"));
        verify(redirectAttributesMock).addFlashAttribute(eq("error"), contains("No tenes puntos suficientes"));
    }

    // --- CANJE SIN LOGIN ---
    @Test
    public void dadoQueUsuarioNoLogueadoCuandoCanjeaEntoncesRedirigeALogin() {
        givenUsuarioNoLogueado();
        whenCanjeaProducto();
        thenRedirigeALogin();
    }

    private void givenUsuarioNoLogueado() {
        when(sessionMock.getAttribute("usuarioLogueado")).thenReturn(null);
    }

    private void thenRedirigeALogin() {
        assertThat(mav.getViewName(), is("redirect:/inicio-de-sesion"));
    }
}