package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ServicioRegistroTest {


    private RepositorioUsuario repositorioUsuarioMock;
    private ServicioRegistro servicioRegistro;

    @BeforeEach
    public void init() {

        repositorioUsuarioMock = mock(RepositorioUsuario.class);
        servicioRegistro = new ServicioRegistroImpl(repositorioUsuarioMock);
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConDatosValidos_EntoncesElRegistroEsExitoso() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertDoesNotThrow(() -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConEmailInvalido_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeEmailInvalidoException.class, () -> {
            cuandoSeRegistra("email-invalido", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConContraseniaInvalida_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeContraseniaInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "contrasenia-invalida", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConNombreInvalido_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeNombreOApellidoInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan123", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConApellidoInvalido_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeNombreOApellidoInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez123", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConNombreDeUsuarioInvalido_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeNombreDeUsuarioInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "ju", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConTelefonoInvalido_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeTelefonoInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "123",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConCalleInvalida_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeCalleInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Ca", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConNumeroInvalido_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeNumeroInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "1234567", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConCiudadInvalida_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeCiudadInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "S", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConCodigoPostalInvalido_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeCodigoPostalInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"123",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConPisoInvalido_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDePisoInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1234", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConDepartamentoInvalido_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeDepartamentoInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "ABCDEFGHIJK");
        });
    }

    @Test
    public void dadoQueExisteUnUsuarioConEseEmail_CuandoSeRegistra_EntoncesElRegistroFalla() {
        dadoQueExisteUnUsuarioConEseEmail();

        assertThrows(EmailYaUsadoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES, "12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueExisteUnUsuarioConEseNombreDeUsuario_CuandoSeRegistra_EntoncesElRegistroFalla() {
        dadoQueExisteUnUsuarioConEseNombreDeUsuario();

        assertThrows(NombreDeUsuarioYaUsadoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES, "12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueExisteUnUsuarioConEseTelefono_CuandoSeRegistra_EntoncesElRegistroFalla() {
        dadoQueExisteUnUsuarioConEseTelefono();

        assertThrows(TelefonoYaUsadoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES, "12345",
                    "1", "A");
        });
    }

    private void dadoQueNoExisteUnUsuarioConEseEmail() {
    }

    private void dadoQueExisteUnUsuarioConEseEmail() {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setEmail("test@gmail.com");
        when(repositorioUsuarioMock.buscarPorEmail("test@gmail.com")).thenReturn(usuarioExistente);
    }

    private void dadoQueExisteUnUsuarioConEseNombreDeUsuario() {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setNombreDeUsuario("juanperez");
        when(repositorioUsuarioMock.buscarPorNombreDeUsuario("juanperez")).thenReturn(usuarioExistente);
    }

    private void dadoQueExisteUnUsuarioConEseTelefono() {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setTelefono("1234567890");
        when(repositorioUsuarioMock.buscarPorTelefono("1234567890")).thenReturn(usuarioExistente);
    }

    private void cuandoSeRegistra(String email, String nombre, String apellido, String nombreDeUsuario, String telefono, String contrasenia,
                                          Roles rol, String calle, String numero, String ciudad, Provincias provincia, String codigoPostal,
                                          String piso, String departamento) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setNombreDeUsuario(nombreDeUsuario);
        usuario.setTelefono(telefono);
        usuario.setContrasenia(contrasenia);
        usuario.setRol(rol);
        usuario.setDomicilio(new Domicilio());
        usuario.getDomicilio().setCalle(calle);
        usuario.getDomicilio().setNumero(numero);
        usuario.getDomicilio().setCiudad(ciudad);
        usuario.getDomicilio().setProvincia(provincia);
        usuario.getDomicilio().setCodigoPostal(codigoPostal);
        usuario.getDomicilio().setDepartamento(departamento);
        usuario.getDomicilio().setPiso(piso);

        servicioRegistro.registrarUsuario(usuario);
    }
}
