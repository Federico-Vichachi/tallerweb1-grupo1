package com.tallerwebi.presentacion;

import com.mercadopago.resources.preference.Preference;
import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class ControladorDonacionTest {

    private ControladorDonacion controladorDonacion;
    private ServicioPublicacion servicioPublicacionMock;
    private ServicioPago servicioPagoMock;
    private ServicioPuntos servicioPuntosMock;
    private HttpSession sessionMock;

    @BeforeEach
    void setUp() {
        servicioPublicacionMock = mock(ServicioPublicacion.class);
        servicioPagoMock = mock(ServicioPago.class);
        servicioPuntosMock = mock(ServicioPuntos.class);
        sessionMock = mock(HttpSession.class);

        controladorDonacion = new ControladorDonacion(
                servicioPublicacionMock, servicioPagoMock, servicioPuntosMock
        );
    }

    @Test
    void cuandoLaDonacionEsExitosaEntoncesSeSumanLosPuntosYSeRedirigeAlFeed() {
        Usuario usuario = givenTengoUnUsuario();
        givenTengoUnaPublicacion();

        ModelAndView mav = whenCreoUnaDonacionExitosa();

        thenRedirigueAlFeed(usuario, mav);
    }


    private Usuario givenTengoUnUsuario() {
        Usuario usuario = new Usuario();
        usuario.setPuntos(0);
        when(sessionMock.getAttribute("usuarioLogueado")).thenReturn(usuario);
        return usuario;
    }

    private void givenTengoUnaPublicacion() {
        PublicacionRecaudacion publicacionMock = mock(PublicacionRecaudacion.class);
        when(servicioPublicacionMock.buscarPorId(1L)).thenReturn(publicacionMock);
        when(servicioPagoMock.obtenerMontoDePago("pago123")).thenReturn(2000d);
    }

    private ModelAndView whenCreoUnaDonacionExitosa() {
        ModelAndView mav = controladorDonacion.donacionExitosa(
                "pago123", 1L, "approved", sessionMock
        );
        return mav;
    }

    private void thenRedirigueAlFeed(Usuario usuario, ModelAndView mav) {
        verify(servicioPuntosMock, times(1))
                .sumarPuntos(eq(usuario), anyDouble());

        assertThat(mav.getViewName(), is("redirect:/feed"));
    }


    @Test
    void cuandoUsuarioNoLogueadoYDonacionEntoncesRedirigeALogin() {

        givenNoTengoUsuarioLogueado();

        ModelAndView mav = whenIntentoDonar();

        thenRedirigeALogin(mav);
    }

    private void givenNoTengoUsuarioLogueado() {
        when(sessionMock.getAttribute("usuarioLogueado")).thenReturn(null);
    }

    private ModelAndView whenIntentoDonar() {
        return controladorDonacion.donar(1L, 500d, sessionMock);
    }

    private void thenRedirigeALogin(ModelAndView mav) {
        assertThat(mav.getViewName(), containsString("redirect:/inicio-de-sesion"));
    }
}
