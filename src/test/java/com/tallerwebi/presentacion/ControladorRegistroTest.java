package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Provincias;
import com.tallerwebi.dominio.Roles;
import com.tallerwebi.dominio.excepcion.FormatoDeEmailInvalidoException;
import com.tallerwebi.dominio.excepcion.FormatoDeContraseniaInvalidoException;
import com.tallerwebi.dominio.ServicioRegistroImpl;
import com.tallerwebi.dominio.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

@ExtendWith(MockitoExtension.class)
public class ControladorRegistroTest {

    @Mock
    ServicioRegistroImpl servicioRegistroImpl;
    Usuario usuario;

    @InjectMocks
    private ControladorRegistro controladorRegistro;

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
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnEmailInvalido_EntoncesElRegistroFalla() throws Exception {
        // Configurar el mock para lanzar excepción con email inválido
        doThrow(new FormatoDeEmailInvalidoException("Email inválido"))
                .when(servicioRegistroImpl)
                .registrarUsuario(any(Usuario.class));

        ModelAndView modelAndView = cuandoSeRegistra("email-invalido", "Juan", "Perez", "juanperez", "1234567890",
                "Contrasenia@2025", "Contrasenia@2025", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                "1", "A");

        entoncesElRegistroFalla(modelAndView, "Email inválido");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnaContraseniaInvalida_EntoncesElRegistroFalla() throws Exception {
        // Configurar el mock para lanzar excepción con contraseña inválida
        doThrow(new FormatoDeContraseniaInvalidoException("Contraseña inválida"))
                .when(servicioRegistroImpl)
                .registrarUsuario(any(Usuario.class));

        ModelAndView modelAndView = cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                "contrasenia-invalida", "contrasenia-invalida", Roles.USUARIO_COMUN,
                "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES, "12345",
                "1", "A");
        entoncesElRegistroFalla(modelAndView, "Contraseña inválida");
    }


    private ModelAndView cuandoSeRegistra(String email, String nombre, String apellido, String nombreDeUsuario, String telefono, String contrasenia,
                                          String repeticionDeContrasenia, Roles rol, String calle, String numero, String ciudad,  Provincias provincia, String codigoPostal,
                                          String piso, String departamento) {
        DatosRegistro datosRegistro = new DatosRegistro();
        datosRegistro.setEmail(email);
        datosRegistro.setNombre(nombre);
        datosRegistro.setApellido(apellido);
        datosRegistro.setNombreDeUsuario(nombreDeUsuario);
        datosRegistro.setTelefono(telefono);
        datosRegistro.setContrasenia(contrasenia);
        datosRegistro.setRepeticionDeContrasenia(repeticionDeContrasenia);
        datosRegistro.setRol(rol);
        datosRegistro.setCalle(calle);
        datosRegistro.setNumero(numero);
        datosRegistro.setCiudad(ciudad);
        datosRegistro.setProvincia(provincia);
        datosRegistro.setCodigoPostal(codigoPostal);
        datosRegistro.setPiso(piso);
        datosRegistro.setDepartamento(departamento);
        return controladorRegistro.registrar(datosRegistro);
    }


    private void dadoQueNoExisteUnUsuarioConEseEmail() {
    }

    private void entoncesElRegistroEsExitoso(ModelAndView mav) {
        assertThat(mav.getViewName(), equalToIgnoringCase("login"));
    }
    private void entoncesElRegistroFalla(ModelAndView mav, String mensajeDeError) {
        assertThat(mav.getViewName(), equalToIgnoringCase("registro"));
        assertThat(mav.getModel().get("error").toString(), equalToIgnoringCase(mensajeDeError));
    }


}