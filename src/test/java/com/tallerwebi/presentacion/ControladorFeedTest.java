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

    // 1. Declaramos los Mocks y el Controlador, igual que en ControladorLoginTest
    private ControladorFeed controladorFeed;
    private ServicioPublicacion servicioPublicacionMock;

    @BeforeEach
    public void init() {
        // 2. Inicializamos los Mocks y el Controlador en el @BeforeEach
        servicioPublicacionMock = mock(ServicioPublicacion.class);
        controladorFeed = new ControladorFeed(servicioPublicacionMock);
    }

    @Test
    public void siPidoElFeedSinFiltrosDeberiaMostrarTodasLasPublicaciones() {
        // Preparación (Arrange)
        // Creamos una lista falsa de publicaciones que simulará la respuesta del servicio
        List<Publicacion> publicacionesFalsas = Arrays.asList(mock(Publicacion.class), mock(Publicacion.class));
        when(servicioPublicacionMock.buscarPublicacionesPorCategoria(null)).thenReturn(publicacionesFalsas);

        // Ejecución (Act)
        ModelAndView modelAndView = controladorFeed.irAFeed(null);

        // Validación (Assert)
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("feed"));
        List<Publicacion> publicacionesEnModelo = (List<Publicacion>) modelAndView.getModel().get("publicaciones");
        assertThat(publicacionesEnModelo, hasSize(2));
        verify(servicioPublicacionMock, times(1)).buscarPublicacionesPorCategoria(null);
    }

    @Test
    public void siPidoElFeedConFiltroDeCategoriaDeberiaMostrarPublicacionesFiltradas() {
        // Preparación (Arrange)
        String categoriaFiltro = "Adopcion";
        // Creamos una lista falsa que representa el resultado filtrado
        List<Publicacion> publicacionesFiltradasFalsas = Collections.singletonList(mock(Publicacion.class));
        when(servicioPublicacionMock.buscarPublicacionesPorCategoria(categoriaFiltro)).thenReturn(publicacionesFiltradasFalsas);

        // Ejecución (Act)
        ModelAndView modelAndView = controladorFeed.irAFeed(categoriaFiltro);

        // Validación (Assert)
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("feed"));
        List<Publicacion> publicacionesEnModelo = (List<Publicacion>) modelAndView.getModel().get("publicaciones");
        assertThat(publicacionesEnModelo, hasSize(1));
        assertThat((String) modelAndView.getModel().get("categoriaFiltro"), equalToIgnoringCase(categoriaFiltro));
        // Verificamos que el controlador llamó al servicio con el filtro correcto
        verify(servicioPublicacionMock, times(1)).buscarPublicacionesPorCategoria(categoriaFiltro);
    }

    @Test
    public void siElServicioNoEncuentraPublicacionesElFeedDeberiaMostrarUnaListaVacia() {
        // Preparación (Arrange)
        when(servicioPublicacionMock.buscarPublicacionesPorCategoria(null)).thenReturn(Collections.emptyList());

        // Ejecución (Act)
        ModelAndView modelAndView = controladorFeed.irAFeed(null);

        // Validación (Assert)
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("feed"));
        List<Publicacion> publicacionesEnModelo = (List<Publicacion>) modelAndView.getModel().get("publicaciones");
        assertThat(publicacionesEnModelo, is(empty()));
    }
}