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
    public void dadoQueExisteUnUsuarioYUnaMontoQueSeSumenLosPuntosCorrectamente(){
        Usuario usuario = givenTengoUnUsuarioSinPuntos();
        Double monto = 100d;

        whenSumoLosPuntos(usuario, monto);

        thenSeSumanLosPuntosCorrectamente(usuario, 10);
    }

    private Usuario givenTengoUnUsuarioSinPuntos() {
        Usuario usuario = new  Usuario();
        usuario.setPuntos(0);
        return usuario;
    }

    private void whenSumoLosPuntos(Usuario usuario, Double monto) {
        servicioPuntos.sumarPuntos(usuario, monto);
    }

    private void thenSeSumanLosPuntosCorrectamente(Usuario usuario, int puntosEsperados) {
        assertThat(usuario.getPuntos(), is(puntosEsperados));
    }

}
