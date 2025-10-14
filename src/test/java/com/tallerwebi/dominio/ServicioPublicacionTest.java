package com.tallerwebi.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ServicioPublicacionTest {

    private ServicioPublicacion servicioPublicacion;

    @BeforeEach
    public void init() {
        // Usamos la implementación real porque queremos probar su lógica interna
        servicioPublicacion = new ServicioPublicacionImpl();
    }

    @Test
    public void cuandoBuscoPorCategoriaAdopcionDebeDevolverSoloPublicacionesDeAdopcion() {
        // Act: Ejecutamos el método que queremos probar
        List<Publicacion> resultado = servicioPublicacion.buscarPublicacionesPorCategoria("Adopcion");

        // Assert: Verificamos que el resultado es el esperado
        assertThat(resultado, is(notNullValue()));
        assertThat(resultado.size(), equalTo(1)); // Solo hay 1 de Adopción en los datos de prueba
        assertThat(resultado.get(0).getTipo(), equalToIgnoringCase("Adopcion"));
    }

    @Test
    public void cuandoBuscoPorCategoriaPerdidoDebeDevolverSoloPublicacionesDePerdido() {
        // Act
        List<Publicacion> resultado = servicioPublicacion.buscarPublicacionesPorCategoria("Perdido");

        // Assert
        assertThat(resultado.size(), equalTo(1));
        assertThat(resultado.get(0).getTipo(), equalToIgnoringCase("Perdido"));
    }

    @Test
    public void cuandoBuscoPorCategoriaNulaOVacíaDebeDevolverTodasLasPublicaciones() {
        // Act: Probamos con un filtro nulo y uno vacío
        List<Publicacion> resultadoConNull = servicioPublicacion.buscarPublicacionesPorCategoria(null);
        List<Publicacion> resultadoConVacio = servicioPublicacion.buscarPublicacionesPorCategoria(" ");

        // Assert: En ambos casos, debe devolver todas las publicaciones (4 en total)
        assertThat(resultadoConNull.size(), equalTo(4));
        assertThat(resultadoConVacio.size(), equalTo(4));
    }

}
