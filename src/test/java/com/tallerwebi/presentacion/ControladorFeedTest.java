package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.ServicioPublicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.*;

public class ControladorFeedTest {

    private ControladorFeed controladorFeed;
    private ServicioPublicacion servicioPublicacionMock;

    @BeforeEach
    public void init() {
        servicioPublicacionMock = mock(ServicioPublicacion.class);
        controladorFeed = new ControladorFeed(servicioPublicacionMock);
    }

    @Test
    public void siPidoElFeedSinFiltrosDeberiaMostrarTodasLasPublicaciones() {
        // Preparacion
        List<Publicacion> publicacionesFalsas = Arrays.asList(mock(Publicacion.class), mock(Publicacion.class));
        when(servicioPublicacionMock.buscarPublicacionesPorCategoria(null)).thenReturn(publicacionesFalsas);

        // Ejecucion
        ModelAndView modelAndView = controladorFeed.irAFeed(null, null, null, null, null);

        // Validacion
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("feed"));
        List<Publicacion> publicacionesEnModelo = (List<Publicacion>) modelAndView.getModel().get("publicaciones");
        assertThat(publicacionesEnModelo, hasSize(2));
        verify(servicioPublicacionMock, times(1)).buscarPublicacionesPorCategoria(null);
    }

    @Test
    public void siPidoElFeedConFiltroDeCategoriaDeberiaMostrarPublicacionesFiltradas() {
        // Preparacion
        String categoriaFiltro = "Adopcion";
        List<Publicacion> publicacionesFiltradasFalsas = Collections.singletonList(mock(Publicacion.class));
        when(servicioPublicacionMock.buscarPublicacionesPorCategoria(categoriaFiltro)).thenReturn(publicacionesFiltradasFalsas);

        // Ejecucion
        ModelAndView modelAndView = controladorFeed.irAFeed(categoriaFiltro, null, null, null, null);

        // Validacion
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("feed"));
        List<Publicacion> publicacionesEnModelo = (List<Publicacion>) modelAndView.getModel().get("publicaciones");
        assertThat(publicacionesEnModelo, hasSize(1));
        assertThat((String) modelAndView.getModel().get("categoriaFiltro"), equalToIgnoringCase(categoriaFiltro));
        verify(servicioPublicacionMock, times(1)).buscarPublicacionesPorCategoria(categoriaFiltro);
    }

    @Test
    public void siElServicioNoEncuentraPublicacionesElFeedDeberiaMostrarUnaListaVacia() {
        // Preparacion
        when(servicioPublicacionMock.buscarPublicacionesPorCategoria(null)).thenReturn(Collections.emptyList());

        // Ejecucion
        ModelAndView modelAndView = controladorFeed.irAFeed(null, null, null, null, null);

        // Validacion
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("feed"));
        List<Publicacion> publicacionesEnModelo = (List<Publicacion>) modelAndView.getModel().get("publicaciones");
        assertThat(publicacionesEnModelo, is(empty()));
    }
}