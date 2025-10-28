package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ControladorRegistroTest {

    private ControladorRegistro controladorRegistro;
    private ServicioRegistro servicioRegistroMock;

    @BeforeEach
    public void init() {
        servicioRegistroMock = mock(ServicioRegistro.class);
        controladorRegistro = new ControladorRegistro(servicioRegistroMock);
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConDatosValidos_EntoncesElRegistroEsExitoso() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroEsExitoso(mav);
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinEmail_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "El email es obligatorio.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinNombre_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "El nombre es obligatorio.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinApellido_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "El apellido es obligatorio.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinNombreDeUsuario_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "El nombre de usuario es obligatorio.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinTelefono_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "El teléfono es obligatorio.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinContrasenia_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "", "", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "La contraseña es obligatoria.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinRepeticionDeContrasenia_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "Por favor, repita la contraseña.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConContraseniaYRepeticionDeContraseniaDistintas_EntoncesElRegistroFalla(){
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2026", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "La repetición de la contraseña no coincide con la contraseña.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinRol_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", null,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "El rol es obligatorio.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinCalle_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "La calle es obligatoria.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinNumero_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "El número de domicilio es obligatorio.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinCiudad_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "La ciudad es obligatoria.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinProvincia_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", null,"12345",
                "1", "A");

        entoncesElRegistroFalla(mav, "La provincia es obligatoria.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinCodigoPostal_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"",
                "1", "A");

        entoncesElRegistroFalla(mav, "El código postal es obligatorio.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnEmailInvalido_EntoncesElRegistroFalla() {
        doThrow(new FormatoDeEmailInvalidoException("El formato del email es inválido."))
                .when(servicioRegistroMock)
                .registrarUsuario(any(DatosRegistro.class));

        ModelAndView modelAndView = cuandoSeRegistra("email-invalido", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(modelAndView, "El formato del email es inválido.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnNombreInvalido_EntoncesElRegistroFalla() {
        doThrow(new FormatoDeNombreOApellidoInvalidoException("El formato del nombre es inválido."))
                .when(servicioRegistroMock)
                .registrarUsuario(any(DatosRegistro.class));

        ModelAndView modelAndView = cuandoSeRegistra("test@gmail.com", "juan123", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(modelAndView, "El formato del nombre es inválido.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnApellidoInvalido_EntoncesElRegistroFalla() {
        doThrow(new FormatoDeNombreOApellidoInvalidoException("El formato del apellido es inválido."))
                .when(servicioRegistroMock)
                .registrarUsuario(any(DatosRegistro.class));

        ModelAndView modelAndView = cuandoSeRegistra("test@gmail.com", "Juan", "@perez!", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(modelAndView, "El formato del apellido es inválido.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnNombreDeUsuarioInvalido_EntoncesElRegistroFalla() {
        doThrow(new FormatoDeNombreDeUsuarioInvalidoException("El formato del nombre de usuario es inválido."))
                .when(servicioRegistroMock)
                .registrarUsuario(any(DatosRegistro.class));

        ModelAndView modelAndView = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "Juan perez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(modelAndView, "El formato del nombre de usuario es inválido.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnTelefonoInvalido_EntoncesElRegistroFalla() {
        doThrow(new FormatoDeTelefonoInvalidoException("El formato del teléfono es inválido."))
                .when(servicioRegistroMock)
                .registrarUsuario(any(DatosRegistro.class));

        ModelAndView modelAndView = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "telefono-invalido",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(modelAndView, "El formato del teléfono es inválido.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnaContraseniaInvalida_EntoncesElRegistroFalla() {
        doThrow(new FormatoDeContraseniaInvalidoException("El formato de la contraseña es inválido."))
                .when(servicioRegistroMock)
                .registrarUsuario(any(DatosRegistro.class));

        ModelAndView modelAndView = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia-invalida", "Contrasenia-invalida", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(modelAndView, "El formato de la contraseña es inválido.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnCalleInvalida_EntoncesElRegistroFalla() {
        doThrow(new FormatoDeCalleInvalidoException("El formato de la calle es inválido."))
                .when(servicioRegistroMock)
                .registrarUsuario(any(DatosRegistro.class));

        ModelAndView modelAndView = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "calle-invalida!", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(modelAndView, "El formato de la calle es inválido.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnNumeroDeDomicilioInvalido_EntoncesElRegistroFalla() {
        doThrow(new FormatoDeNumeroInvalidoException("El formato del número de domicilio es inválido."))
                .when(servicioRegistroMock)
                .registrarUsuario(any(DatosRegistro.class));

        ModelAndView modelAndView = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(modelAndView, "El formato del número de domicilio es inválido.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnaCiudadInvalida_EntoncesElRegistroFalla() {
        doThrow(new FormatoDeCiudadInvalidoException("El formato de la ciudad es inválido."))
                .when(servicioRegistroMock)
                .registrarUsuario(any(DatosRegistro.class));

        ModelAndView modelAndView = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "@ciudadInvalida!", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(modelAndView, "El formato de la ciudad es inválido.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnCodigoPostalInvalido_EntoncesElRegistroFalla() {
        doThrow(new FormatoDeCodigoPostalInvalidoException("El formato del código postal es inválido."))
                .when(servicioRegistroMock)
                .registrarUsuario(any(DatosRegistro.class));

        ModelAndView modelAndView = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"#CodigoPostal-invalido!",
                "1", "A");

        entoncesElRegistroFalla(modelAndView, "El formato del código postal es inválido.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnPisoInvalido_EntoncesElRegistroFalla() {
        doThrow(new FormatoDePisoInvalidoException("El formato del piso es inválido."))
                .when(servicioRegistroMock)
                .registrarUsuario(any(DatosRegistro.class));

        ModelAndView modelAndView = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "##piso-invalido!", "A");

        entoncesElRegistroFalla(modelAndView, "El formato del piso es inválido.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnDepartamentoInvalido_EntoncesElRegistroFalla() {
        doThrow(new FormatoDeDepartamentoInvalidoException("El formato del departamento es inválido."))
                .when(servicioRegistroMock)
                .registrarUsuario(any(DatosRegistro.class));

        ModelAndView modelAndView = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "@DepartamentoInvalido!");

        entoncesElRegistroFalla(modelAndView, "El formato del departamento es inválido.");
    }


    private ModelAndView cuandoSeRegistra(String email, String nombre, String apellido, String nombreDeUsuario, String telefono, String contrasenia,
                                          String repeticionDeContrasenia, Roles rol, String calle, String numero, String ciudad,  Provincias provincia, String codigoPostal,
                                          String piso, String departamento) {
        DatosRegistro datosRegistro = new DatosRegistro(nombre, apellido, nombreDeUsuario, email, telefono, contrasenia,
                repeticionDeContrasenia, rol, calle, numero, ciudad, provincia, codigoPostal, departamento, piso);
        return controladorRegistro.registrar(datosRegistro);
    }


    private void dadoQueNoExisteUnUsuarioConEseEmail() {
    }

    private void entoncesElRegistroEsExitoso(ModelAndView mav) {
        assertThat(mav.getViewName(), equalToIgnoringCase("redirect:/inicio-de-sesion"));
    }
    private void entoncesElRegistroFalla(ModelAndView mav, String mensajeDeError) {
        assertThat(mav.getViewName(), equalToIgnoringCase("registro"));
        assertThat(mav.getModel().get("error").toString(), equalToIgnoringCase(mensajeDeError));
    }


}