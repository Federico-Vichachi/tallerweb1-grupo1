package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.ServicioComentario;
import com.tallerwebi.dominio.ServicioPublicacion;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.ComentarioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.*;

public class ControladorComentarioTest {

    private ControladorComentario controladorComentario;
    private ServicioComentario servicioComentarioMock;
    private ServicioPublicacion servicioPublicacionMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;

    private Usuario usuarioLogueado;
    private Publicacion publicacionExistente;
    private final Long ID_PUBLICACION = 1L;
    private final String TEXTO_COMENTARIO = "Este es un comentario de prueba";

    @BeforeEach
    public void init() {
        servicioComentarioMock = mock(ServicioComentario.class);
        servicioPublicacionMock = mock(ServicioPublicacion.class);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);

        usuarioLogueado = new Usuario();
        publicacionExistente = mock(Publicacion.class);

        controladorComentario = new ControladorComentario(servicioComentarioMock, servicioPublicacionMock);

        when(requestMock.getSession()).thenReturn(sessionMock);
    }

    @Test
    public void queAlGuardarUnComentarioExitosamenteRedirijaALaPublicacion() {
        tengoUnUsuarioLogueadoYUnaPublicacionExistente();
        ModelAndView mav = guardoElComentario(TEXTO_COMENTARIO, ID_PUBLICACION);
        elComentarioSeGuardaYSeRedirige(mav);
    }


    @Test
    public void queSiUsuarioNoLogueadoComentaSeMuestreErrorEnLaMismaPagina() {
        tengoUnUsuarioNoLogueado();
        when(servicioPublicacionMock.buscarPorId(ID_PUBLICACION)).thenReturn(publicacionExistente);
        doThrow(new ComentarioException("Debe iniciar sesion para comentar."))
                .when(servicioComentarioMock)
                .guardarComentario(anyString(), eq(null), org.mockito.ArgumentMatchers.any(Publicacion.class));
        ModelAndView mav = guardoElComentario(TEXTO_COMENTARIO, ID_PUBLICACION);

        seMuestraLaVistaDeDetalleConMensajeDeError(mav, "Debe iniciar sesion para comentar.");
    }

    @Test
    public void queSiHayUnErrorAlGuardarSeMuestreLaVistaDeDetalleConElError() {
        elServicioLanzaraUnaExcepcion();
        ModelAndView mav = guardoElComentario(TEXTO_COMENTARIO, ID_PUBLICACION);
        seMuestraLaVistaDeDetalleConMensajeDeError(mav, "Error al guardar");
    }

    private void tengoUnUsuarioLogueadoYUnaPublicacionExistente() {
        when(sessionMock.getAttribute("usuarioLogueado")).thenReturn(usuarioLogueado);
        when(servicioPublicacionMock.buscarPorId(ID_PUBLICACION)).thenReturn(publicacionExistente);
    }

    private void tengoUnUsuarioNoLogueado() {
        when(sessionMock.getAttribute("usuarioLogueado")).thenReturn(null);
    }

    private void elServicioLanzaraUnaExcepcion() {
        when(sessionMock.getAttribute("usuarioLogueado")).thenReturn(usuarioLogueado);
        when(servicioPublicacionMock.buscarPorId(ID_PUBLICACION)).thenReturn(publicacionExistente);
        doThrow(new ComentarioException("Error al guardar"))
                .when(servicioComentarioMock)
                .guardarComentario(
                        org.mockito.ArgumentMatchers.anyString(),
                        org.mockito.ArgumentMatchers.any(Usuario.class),
                        org.mockito.ArgumentMatchers.any(Publicacion.class)
                );
    }

    private ModelAndView guardoElComentario(String texto, Long publicacionId) {
        return controladorComentario.guardarComentario(texto, publicacionId, requestMock);
    }

    private void elComentarioSeGuardaYSeRedirige(ModelAndView mav) {
        verify(servicioComentarioMock, times(1)).guardarComentario(
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.any(Usuario.class),
                org.mockito.ArgumentMatchers.any(Publicacion.class)
        );
        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/publicacion/" + ID_PUBLICACION));
    }



    private void seMuestraLaVistaDeDetalleConMensajeDeError(ModelAndView mav, String mensajeError) {
        assertThat(mav.getViewName(), equalToIgnoringCase("publicacion-detalle"));

        assertThat(mav.getModel().get("errorComentario"), is(equalTo(mensajeError)));

        assertThat(mav.getModel().get("publicacion"), is(notNullValue()));
        assertThat(mav.getModel().get("publicacion"), is(equalTo(publicacionExistente)));
    }
}