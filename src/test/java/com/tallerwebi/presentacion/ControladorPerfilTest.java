package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ControladorPerfilTest {

    private ServicioPerfil servicioPerfilMock;
    private ServicioPublicacion servicioPublicacionMock;
    private ControladorPerfil controladorPerfil;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private Usuario usuario;

    @BeforeEach
    public void init() {
        servicioPerfilMock = mock(ServicioPerfil.class);
        servicioPublicacionMock = mock(ServicioPublicacion.class);
        controladorPerfil = new ControladorPerfil(servicioPerfilMock, servicioPublicacionMock);
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

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditoElPerfilConUnNombreVacio_entocesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = cuandoEditoElPerfil("", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El nombre es obligatorio.");
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditoElPerfilConUnApellidoVacio_entocesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El apellido es obligatorio.");
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditoElPerfilConUnNombreDeUsuarioVacio_entocesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El nombre de usuario es obligatorio.");
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditoElPerfilConUnEmailVacio_entocesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El email es obligatorio.");
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditoElPerfilConUnTelefonoVacio_entocesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El teléfono es obligatorio.");
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditoElPerfilConUnaCalleVacia_entocesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "La calle es obligatoria.");
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditoElPerfilConUnNumeroDeDomicilioVacio_entocesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El número de domicilio es obligatorio.");
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditoElPerfilConUnaCiudadVacia_entocesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "La ciudad es obligatoria.");
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditoElPerfilConUnaProvinciaVacia_entocesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", null, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "La provincia es obligatoria.");
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditoElPerfilConUnCodigoPostalVacio_entocesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El código postal es obligatorio.");
    }

    @Test
    public void dadoQueHayUnaCuentaInciada_CuandoEditoElPerfilConUnNombreInvalido_entoncesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        doThrow(new FormatoDeNombreOApellidoInvalidoException("El formato del nombre es inválido."))
                .when(servicioPerfilMock)
                .guardarCambiosPerfil(any(DatosEdicionPerfil.class));

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan123", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El formato del nombre es inválido.");
    }

    @Test
    public void dadoQueHayUnaCuentaInciada_CuandoEditoElPerfilConUnApellidoInvalido_entoncesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        doThrow(new FormatoDeNombreOApellidoInvalidoException("El formato del apellido es inválido."))
                .when(servicioPerfilMock)
                .guardarCambiosPerfil(any(DatosEdicionPerfil.class));

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez1234", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El formato del apellido es inválido.");
    }

    @Test
    public void dadoQueHayUnaCuentaInciada_CuandoEditoElPerfilConUnNombreDeUsuarioInvalido_entoncesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        doThrow(new FormatoDeNombreDeUsuarioInvalidoException("El formato del nombre de usuario es inválido."))
                .when(servicioPerfilMock)
                .guardarCambiosPerfil(any(DatosEdicionPerfil.class));

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El formato del nombre de usuario es inválido.");
    }

    @Test
    public void dadoQueHayUnaCuentaInciada_CuandoEditoElPerfilConUnEmailInvalido_entoncesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        doThrow(new FormatoDeEmailInvalidoException("El formato del email es inválido."))
                .when(servicioPerfilMock)
                .guardarCambiosPerfil(any(DatosEdicionPerfil.class));

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "emailsinarroba.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El formato del email es inválido.");
    }

    @Test
    public void dadoQueHayUnaCuentaInciada_CuandoEditoElPerfilConUnTelefonoInvalido_entoncesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        doThrow(new FormatoDeTelefonoInvalidoException("El formato del teléfono es inválido."))
                .when(servicioPerfilMock)
                .guardarCambiosPerfil(any(DatosEdicionPerfil.class));

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "telefonoinvalido", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El formato del teléfono es inválido.");
    }

    @Test
    public void dadoQueHayUnaCuentaInciada_CuandoEditoElPerfilConUnaCalleInvalida_entoncesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        doThrow(new FormatoDeCalleInvalidoException("El formato de la calle es inválido."))
                .when(servicioPerfilMock)
                .guardarCambiosPerfil(any(DatosEdicionPerfil.class));

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "avenda Invalida@-+", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El formato de la calle es inválido.");
    }

    @Test
    public void dadoQueHayUnaCuentaInciada_CuandoEditoElPerfilConUnNumeroDeDomicilioInvalido_entoncesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        doThrow(new FormatoDeNumeroInvalidoException("El formato del número de domicilio es inválido."))
                .when(servicioPerfilMock)
                .guardarCambiosPerfil(any(DatosEdicionPerfil.class));

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "numeroINVALIDO", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El formato del número de domicilio es inválido.");
    }

    @Test
    public void dadoQueHayUnaCuentaInciada_CuandoEditoElPerfilConUnPisoInvalido_entoncesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        doThrow(new FormatoDePisoInvalidoException("El formato del piso es inválido."))
                .when(servicioPerfilMock)
                .guardarCambiosPerfil(any(DatosEdicionPerfil.class));

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "piso#?", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El formato del piso es inválido.");
    }

    @Test
    public void dadoQueHayUnaCuentaInciada_CuandoEditoElPerfilConUnDepartamentoInvalido_entoncesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        doThrow(new FormatoDeDepartamentoInvalidoException("El formato del departamento es inválido."))
                .when(servicioPerfilMock)
                .guardarCambiosPerfil(any(DatosEdicionPerfil.class));

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "dep??", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El formato del departamento es inválido.");
    }

    @Test
    public void dadoQueHayUnaCuentaInciada_CuandoEditoElPerfilConUnaCiudadInvalida_entoncesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        doThrow(new FormatoDeCiudadInvalidoException("El formato de la ciudad es inválido."))
                .when(servicioPerfilMock)
                .guardarCambiosPerfil(any(DatosEdicionPerfil.class));

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ciudad 123@", Provincias.BUENOS_AIRES, "1704");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El formato de la ciudad es inválido.");
    }

    @Test
    public void dadoQueHayUnaCuentaInciada_CuandoEditoElPerfilConUnCodigoPostalInvalido_entoncesLaActualizacionDelPerfilFalla() {
        dadoQueHayUnaCuentaIniciada();

        doThrow(new FormatoDeCodigoPostalInvalidoException("El formato del código postal es inválido."))
                .when(servicioPerfilMock)
                .guardarCambiosPerfil(any(DatosEdicionPerfil.class));

        ModelAndView modelAndView = cuandoEditoElPerfil("Juan", "Perez", "juanperezNevoNombre", "juan.perez@test.com",
                "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "12AB");

        entocesLaActualizacionDelPerfilFalla(modelAndView, "El formato del código postal es inválido.");
    }


    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoCierroSesion_entoncesSeCierraLaSesionYSeRedirigeAlInicio() {
        dadoQueHayUnaCuentaIniciada();

        ModelAndView modelAndView = controladorPerfil.cerrarSesion(requestMock);

        entoncesSeCierraLaSesionYSeRedirigeAlInicio(modelAndView);
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
        return controladorPerfil.guardarPerfil(datosEdicionPerfil, null, requestMock);
    }

    private void entoncesSeActualizaElPerfil(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/perfil"));
    }

    private void entocesLaActualizacionDelPerfilFalla(ModelAndView modelAndView, String mensajeDeError) {
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("editar-perfil"));
        assertThat(modelAndView.getModel().get("error").toString(), equalToIgnoringCase(mensajeDeError));
    }

    private void entoncesSeCierraLaSesionYSeRedirigeAlInicio(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName(), equalToIgnoringCase("redirect:/inicio-de-sesion"));
    }

}
