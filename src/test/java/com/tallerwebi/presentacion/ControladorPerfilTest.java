package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Domicilio;
import com.tallerwebi.dominio.Provincias;
import com.tallerwebi.dominio.ServicioPerfil;
import com.tallerwebi.dominio.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorPerfilTest {

    private ServicioPerfil servicioPerfilMock;
    private ControladorPerfil controladorPerfil;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private Usuario usuario;

    @BeforeEach
    public void init() {
        servicioPerfilMock = mock(ServicioPerfil.class);
        controladorPerfil = new ControladorPerfil(servicioPerfilMock);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        usuario = new Usuario();
        when(requestMock.getSession()).thenReturn(sessionMock);
    }
    
    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoSeAccedeAlPerfil_entoncesSeMuestraElPerfil() {
        dadoQueHayUnaCuentaIniciada();
        
        ModelAndView modelAndView = cuandoSeAccedeAlPerfil();
        
        entoncesSeMuestraElPerfil(modelAndView);
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoSeAccedeAEditarPerfil_entoncesSeMuestraEditarPerfil() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = cuandoSeAccedeAEditarPerfil();

        entoncesSeMuestraEditarPerfil(modelAndView);
    }

    @Test
    public void dadoQueNoHayUnaCuentaIniciada_cuandoSeAccedeAlPerfil_entoncesSeMuestraElInicioDeSesion() {
        ModelAndView modelAndView = cuandoSeAccedeAlPerfil();

        entoncesSeMuestraElInicioDeSesion(modelAndView);
    }

    @Test
    public void dadoQueNoHayUnaCuentaIniciada_cuandoSeAccedeAEditarPerfil_entoncesSeMuestraElInicioDeSesion() {
        ModelAndView modelAndView = cuandoSeAccedeAEditarPerfil();

        entoncesSeMuestraElInicioDeSesion(modelAndView);
    }

    @Test
    public void dadoQueHayUnaCuentaInciada_cuandoEditoUnDatoDelPerfil_entoncesSeActualizaElPerfil() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entoncesSeActualizaElPerfil(modelAndView);
    }

    private void dadoQueHayUnaCuentaIniciada() {
        usuario.setEmail("juan.perez@test.com");
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setDomicilio(new Domicilio());

        when(sessionMock.getAttribute("usuarioLogueado")).thenReturn(usuario);
    }


    private ModelAndView cuandoSeAccedeAlPerfil() {
        return controladorPerfil.irAlPerfil(requestMock);
    }

    private void entoncesSeMuestraElPerfil(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("perfil"));
    }

    private ModelAndView cuandoSeAccedeAEditarPerfil() {
        return controladorPerfil.irAEditarPerfil(requestMock);
    }

    private void entoncesSeMuestraEditarPerfil(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("editar-perfil"));
    }

    private void entoncesSeMuestraElInicioDeSesion(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/inicio-de-sesion"));
    }

    private ModelAndView cuandoEditoElPerfil(String nombre, String apellido, String nombreDeUsuario, String email,
                                     String telefono, String calle, String numero, String piso,
                                     String departamento, String ciudad, Provincias provincia, String codigoPostal) {
        DatosEdicionPerfil datosEdicionPerfil = new DatosEdicionPerfil(
                nombre, apellido, nombreDeUsuario, email, telefono,
                calle, numero, piso, departamento, ciudad, provincia, codigoPostal
        );
        return controladorPerfil.guardarPerfil(datosEdicionPerfil, requestMock);
    }

    private void entoncesSeActualizaElPerfil(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/perfil"));
    }

}
