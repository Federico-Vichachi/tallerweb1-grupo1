package com.tallerwebi.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;


public class ServicioPuntosTest {


    private ServicioPuntos servicioPuntos;
    private RepositorioUsuario repositorioUsuarioMock;


    @BeforeEach
    void setUp() {
        repositorioUsuarioMock = mock(RepositorioUsuario.class);
        servicioPuntos = new ServicioPuntosImpl(repositorioUsuarioMock);

    }

    @Test
    public void dadoQueExisteUnUsuarioYUnaPublicacionAdopcionQueSeSumenLosPuntosCorrectamente(){
        Usuario usuario = givenTengoUnUsuarioSinPuntos();
        Publicacion publicacionAdopcion = givenTengoUnaPublicacionAdopcion();

        whenSumoLosPuntos(usuario, publicacionAdopcion);

        thenSeSumanLosPuntosCorrectamente(usuario, 15);
    }

    private Usuario givenTengoUnUsuarioSinPuntos() {
        Usuario usuario = new  Usuario();
        usuario.setPuntos(0);
        return usuario;
    }

    private Publicacion givenTengoUnaPublicacionAdopcion() {
        Publicacion publicacionAdopcion = mock(PublicacionAdopcion.class);
        when(publicacionAdopcion.puntosPorCreacion()).thenReturn(15);
        return publicacionAdopcion;
    }

    private void whenSumoLosPuntos(Usuario usuario, Publicacion publicacionAdopcion) {
        servicioPuntos.sumarPuntos(usuario, publicacionAdopcion);
    }

    private void thenSeSumanLosPuntosCorrectamente(Usuario usuario, int puntosEsperados) {
        assertThat(usuario.getPuntos(), is(puntosEsperados));
    }

}
