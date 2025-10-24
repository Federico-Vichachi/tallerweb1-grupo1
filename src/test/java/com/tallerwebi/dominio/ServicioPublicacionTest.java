package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.CategoriaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void cuandoBuscoPorCategoriaAdopcionDebeLlamarAlRepositorioConLaClaseCorrecta() {
        // Preparacion
        String categoria = "ADOPCION";
        List<PublicacionAdopcion> publicacionesFalsas = Collections.singletonList(mock(PublicacionAdopcion.class));
        when(repositorioPublicacionMock.buscarPorTipo(PublicacionAdopcion.class)).thenReturn(publicacionesFalsas);

        // Ejecucion
        List<Publicacion> resultado = servicioPublicacion.buscarPublicacionesPorCategoria(categoria);

        // Verificacion
        assertThat(resultado, is(notNullValue()));
        assertThat(resultado.size(), equalTo(1));
        verify(repositorioPublicacionMock, times(1)).buscarPorTipo(PublicacionAdopcion.class);
    }

    @Test
    public void cuandoBuscoPorCategoriaSaludDebeLlamarAlRepositorioConLaClaseCorrecta() {
        // Preparacion
        String categoria = "SALUD";
        List<PublicacionSalud> publicacionesFalsas = Collections.singletonList(mock(PublicacionSalud.class));
        when(repositorioPublicacionMock.buscarPorTipo(PublicacionSalud.class)).thenReturn(publicacionesFalsas);

        // Ejecucion
        List<Publicacion> resultado = servicioPublicacion.buscarPublicacionesPorCategoria(categoria);

        // Verificacion
        assertThat(resultado, is(notNullValue()));
        assertThat(resultado.size(), equalTo(1));
        verify(repositorioPublicacionMock, times(1)).buscarPorTipo(PublicacionSalud.class);
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