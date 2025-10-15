package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.CategoriaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class ServicioPublicacionTest {

    private ServicioPublicacion servicioPublicacion;
    private RepositorioPublicacion repositorioPublicacionMock;

    @BeforeEach
    public void init() {
        repositorioPublicacionMock = mock(RepositorioPublicacion.class);
        servicioPublicacion = new ServicioPublicacionImpl(repositorioPublicacionMock);
    }

    @Test
    public void cuandoBuscoPorCategoriaAdopcionDebeLlamarAlRepositorioConEsaCategoria() {
        // Preparacion
        String categoria = "ADOPCION";
        TipoPublicacion tipoEnum = TipoPublicacion.ADOPCION;

        Publicacion publicacionMock = mock(Publicacion.class);
        List<Publicacion> publicacionesFalsas = Collections.singletonList(publicacionMock);

        when(repositorioPublicacionMock.buscarPorCategoria(tipoEnum)).thenReturn(publicacionesFalsas);

        // Ejecucion
        List<Publicacion> resultado = servicioPublicacion.buscarPublicacionesPorCategoria(categoria);

        // Verificacion
        assertThat(resultado, is(notNullValue()));
        assertThat(resultado.size(), equalTo(1));
        verify(repositorioPublicacionMock, times(1)).buscarPorCategoria(tipoEnum);
    }

    @Test
    public void cuandoBuscoPorCategoriaSaludDebeLlamarAlRepositorioConEsaCategoria() {
        // Preparacion
        String categoria = "SALUD";
        TipoPublicacion tipoEnum = TipoPublicacion.SALUD;

        Publicacion publicacionMock = mock(Publicacion.class);
        List<Publicacion> publicacionesFalsas = Collections.singletonList(publicacionMock);

        when(repositorioPublicacionMock.buscarPorCategoria(tipoEnum)).thenReturn(publicacionesFalsas);

        // Ejecucion
        List<Publicacion> resultado = servicioPublicacion.buscarPublicacionesPorCategoria(categoria);

        // Verificacion
        assertThat(resultado, is(notNullValue()));
        assertThat(resultado.size(), equalTo(1));
        verify(repositorioPublicacionMock, times(1)).buscarPorCategoria(tipoEnum);
    }


    @Test
    public void cuandoBuscoPorCategoriaNulaOVaciaDebeDevolverTodasLasPublicaciones() {
        // Preparacion
        List<Publicacion> publicacionesFalsas = List.of(mock(Publicacion.class), mock(Publicacion.class));
        when(repositorioPublicacionMock.buscarTodas()).thenReturn(publicacionesFalsas);

        // Ejecucion
        List<Publicacion> resultadoConNull = servicioPublicacion.buscarPublicacionesPorCategoria(null);
        List<Publicacion> resultadoConVacio = servicioPublicacion.buscarPublicacionesPorCategoria(" ");

        // Verificacion
        assertThat(resultadoConNull.size(), equalTo(2));
        assertThat(resultadoConVacio.size(), equalTo(2));
        verify(repositorioPublicacionMock, times(2)).buscarTodas();
    }

    @Test
    public void cuandoBuscoPorCategoriaInexistenteDebeLanzarExcepcion() {
        // Preparacion
        String categoriaInvalida = "categoria_que_no_existe";

        // Ejecucion y Verificacion
        assertThrows(CategoriaInvalidaException.class, () -> {
            servicioPublicacion.buscarPublicacionesPorCategoria(categoriaInvalida);
        });
    }
}