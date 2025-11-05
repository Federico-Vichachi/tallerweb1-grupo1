package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionAdopcion;
import com.tallerwebi.dominio.ServicioPublicacion;
import com.tallerwebi.dominio.excepcion.PublicacionNoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ControladorPublicacionTest {

    private ControladorPublicacion controladorPublicacion;
    private ServicioPublicacion servicioPublicacionMock;

    @BeforeEach
    public void init() {
        servicioPublicacionMock = mock(ServicioPublicacion.class);
        controladorPublicacion = new ControladorPublicacion(servicioPublicacionMock);
    }

    @Test
    public void queAlPedirUnaPublicacionExistenteMeLleveALaVistaDetalle() {

        Long publicacionId = 1L;
        Publicacion publicacionMock = new PublicacionAdopcion();
        publicacionMock.setId(publicacionId);
        publicacionMock.setTitulo("Panchito");

        when(servicioPublicacionMock.buscarPorId(publicacionId)).thenReturn(publicacionMock);

        ModelAndView mav = controladorPublicacion.irADetallePublicacion(publicacionId);

        assertThat(mav.getViewName(), is(equalTo("publicacion-detalle")));

        assertThat(mav.getModel().get("publicacion"), is(equalTo(publicacionMock)));

        verify(servicioPublicacionMock, times(1)).buscarPorId(publicacionId);
    }


    @Test
    public void queAlPedirUnaPublicacionInexistenteMeMuestreLaPaginaDeError() {
        Long publicacionIdInexistente = 99L;
        when(servicioPublicacionMock.buscarPorId(publicacionIdInexistente))
                .thenThrow(new PublicacionNoEncontradaException("Pica de aca"));

        ModelAndView mav = controladorPublicacion.irADetallePublicacion(publicacionIdInexistente);

        assertThat(mav.getViewName(), is(equalTo("publicacion-no-encontrada")));

        assertThat(mav.getModel().get("mensajeError"), is(equalTo("Pica de aca")));

        verify(servicioPublicacionMock, times(1)).buscarPorId(publicacionIdInexistente);
    }
}