package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.DatosDeInicioDeSesionIncorrectosException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.*;

public class ControladorInicioDeSesionTest {

    private ControladorInicioDeSesion controladorInicioDeSesion;
    private ServicioInicioDeSesion servicioInicioDeSesionMock;
    private RepositorioUsuario repositorioUsuarioMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;

    @BeforeEach
    public void init() {
        servicioInicioDeSesionMock = mock(ServicioInicioDeSesion.class);
        controladorInicioDeSesion = new ControladorInicioDeSesion(servicioInicioDeSesionMock);
        repositorioUsuarioMock = mock(RepositorioUsuario.class);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);

        when(requestMock.getSession()).thenReturn(sessionMock);
    }

    @Test
    public void dadoQueExisteUnUsuarioRegistrado_CuandoIniciaSesionConDatosCorrectos_EntoncesElInicioDeSesionEsExitoso() {
        Usuario usuario = dadoQueExisteUnUsuarioRegistrado("test@gmail.com", "Contrasenia123@");
        when(servicioInicioDeSesionMock.iniciarSesion("test@gmail.com", "Contrasenia123@")).thenReturn(usuario);
        ModelAndView modelAndView = cuandoIniciaSesion("test@gmail.com", "Contrasenia123@");
        entoncesElInicioDeSesionEsExitoso(modelAndView);
    }

    @Test
    public void dadoQueExisteUnUsuarioRegistrado_CuandoIniciaSesionConUnEmailIncorrecto_EntoncesElInicioDeSesionFalla() {
        dadoQueExisteUnUsuarioRegistrado("test@gmail.com", "Contrasenia123@");
        doThrow(new DatosDeInicioDeSesionIncorrectosException("El email o la contraseña son incorrectos."))
                .when(servicioInicioDeSesionMock)
                .iniciarSesion("test1@gmail.com", "Contrasenia123@");
        ModelAndView modelAndView = cuandoIniciaSesion("test1@gmail.com", "Contrasenia123@");
        entoncesElInicioDeSesionFalla(modelAndView);
    }

    @Test
    public void dadoQueExisteUnUsuarioRegistrado_CuandoIniciaSesionConUnaContraseniaIncorrecta_EntoncesElInicioDeSesionFalla() {
        dadoQueExisteUnUsuarioRegistrado("test@gmail.com", "Contrasenia123@");
        doThrow(new DatosDeInicioDeSesionIncorrectosException("El email o la contraseña son incorrectos."))
                .when(servicioInicioDeSesionMock)
                .iniciarSesion("test@gmail.com", "Contrasenia1234@");
        ModelAndView modelAndView = cuandoIniciaSesion("test@gmail.com", "Contrasenia1234@");
        entoncesElInicioDeSesionFalla(modelAndView);
    }

    private Usuario dadoQueExisteUnUsuarioRegistrado(String email, String contrasenia) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setContrasenia(contrasenia);
        repositorioUsuarioMock.guardar(usuario);
        return usuario;
    }

    private ModelAndView cuandoIniciaSesion(String email, String contrasenia) {
        DatosInicioSesion datosInicioSesion = new DatosInicioSesion();
        datosInicioSesion.setEmail(email);
        datosInicioSesion.setContrasenia(contrasenia);
        return controladorInicioDeSesion.iniciarSesion(datosInicioSesion, requestMock);
    }

    private void entoncesElInicioDeSesionEsExitoso(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/feed"));
    }

    private void entoncesElInicioDeSesionFalla(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("inicio-de-sesion"));
    }
}