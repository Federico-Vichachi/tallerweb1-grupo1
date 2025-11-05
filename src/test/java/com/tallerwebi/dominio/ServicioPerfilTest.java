package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.*;
import com.tallerwebi.presentacion.DatosEdicionPerfil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioPerfilTest {

    private RepositorioUsuario repositorioUsuarioMock;
    private ServicioPerfil servicioPerfil;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private Usuario usuario;

    @BeforeEach
    public void init() {
        repositorioUsuarioMock = mock(RepositorioUsuario.class);
        servicioPerfil = new ServicioPerfilImpl(repositorioUsuarioMock);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        usuario = new Usuario();
        when(requestMock.getSession()).thenReturn(sessionMock);
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditaSuPerfilConDatosValidos_EntoncesLaEdicionEsExitosa() {
        dadoQueHayUnaCuentaIniciada();

        assertDoesNotThrow(() -> {
            cuandoEditaSuPerfil("Juan", "Perez", "juanperez", "juan.perez@test.com",
                    "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");
        });
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditaSuPerfilConNombreInvalido_EntoncesSeLanzanExcepciones() {
        dadoQueHayUnaCuentaIniciada();

        assertThrows(FormatoDeNombreOApellidoInvalidoException.class, () -> {
            cuandoEditaSuPerfil("Juan123", "Perez", "juanperez", "juan.perez@test.com",
                    "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");
        });
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditaSuPerfilConApellidoInvalido_EntoncesSeLanzanExcepciones() {
        dadoQueHayUnaCuentaIniciada();

        assertThrows(FormatoDeNombreOApellidoInvalidoException.class, () -> {
            cuandoEditaSuPerfil("Juan", "Perez123", "juanperez", "juan.perez@test.com",
                    "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");
        });
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditaSuPerfilConEmailInvalido_EntoncesSeLanzanExcepciones() {
        dadoQueHayUnaCuentaIniciada();

        assertThrows(FormatoDeEmailInvalidoException.class, () -> {
            cuandoEditaSuPerfil("Juan", "Perez", "juanperez", "emailsinarroba.com",
                    "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");
        });
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditaSuPerfilConNombreDeUsuarioInvalido_EntoncesSeLanzanExcepciones() {
        dadoQueHayUnaCuentaIniciada();

        assertThrows(FormatoDeNombreDeUsuarioInvalidoException.class, () -> {
            cuandoEditaSuPerfil("Juan", "Perez", "juan perez!!!", "juan.perez@test.com",
                    "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");
        });
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditaSuPerfilConTelefonoInvalido_EntoncesSeLanzanExcepciones() {
        dadoQueHayUnaCuentaIniciada();

        assertThrows(FormatoDeTelefonoInvalidoException.class, () -> {
            cuandoEditaSuPerfil("Juan", "Perez", "juanperez", "juan.perez@test.com",
                    "telefonoinvalido", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");
        });
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditaSuPerfilConCalleInvalida_EntoncesSeLanzanExcepciones() {
        dadoQueHayUnaCuentaIniciada();

        assertThrows(FormatoDeCalleInvalidoException.class, () -> {
            cuandoEditaSuPerfil("Juan", "Perez", "juanperez", "juan.perez@test.com",
                    "1122334455", "Calle@@@Invalida", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");
        });
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditaSuPerfilConNumeroInvalido_EntoncesSeLanzanExcepciones() {
        dadoQueHayUnaCuentaIniciada();

        assertThrows(FormatoDeNumeroInvalidoException.class, () -> {
            cuandoEditaSuPerfil("Juan", "Perez", "juanperez", "juan.perez@test.com",
                    "1122334455", "Av. de Mayo", "numeroINVALIDO", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");
        });
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditaSuPerfilConPisoInvalido_EntoncesSeLanzanExcepciones() {
        dadoQueHayUnaCuentaIniciada();

        assertThrows(FormatoDePisoInvalidoException.class, () -> {
            cuandoEditaSuPerfil("Juan", "Perez", "juanperez", "juan.perez@test.com",
                    "1122334455", "Av. de Mayo", "1234", "piso#?", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");
        });
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditaSuPerfilConDepartamentoInvalido_EntoncesSeLanzanExcepciones() {
        dadoQueHayUnaCuentaIniciada();

        assertThrows(FormatoDeDepartamentoInvalidoException.class, () -> {
            cuandoEditaSuPerfil("Juan", "Perez", "juanperez", "juan.perez@test.com",
                    "1122334455", "Av. de Mayo", "1234", "", "dep??", "Ramos Mejia", Provincias.BUENOS_AIRES, "1704");
        });
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditaSuPerfilConCiudadInvalida_EntoncesSeLanzanExcepciones() {
        dadoQueHayUnaCuentaIniciada();

        assertThrows(FormatoDeCiudadInvalidoException.class, () -> {
            cuandoEditaSuPerfil("Juan", "Perez", "juanperez", "juan.perez@test.com",
                    "1122334455", "Av. de Mayo", "1234", "", "", "Ciudad123@", Provincias.BUENOS_AIRES, "1704");
        });
    }

    @Test
    public void dadoQueHayUnaCuentaIniciada_cuandoEditaSuPerfilConCodigoPostalInvalido_EntoncesSeLanzanExcepciones() {
        dadoQueHayUnaCuentaIniciada();

        assertThrows(FormatoDeCodigoPostalInvalidoException.class, () -> {
            cuandoEditaSuPerfil("Juan", "Perez", "juanperez", "juan.perez@test.com",
                    "1122334455", "Av. de Mayo", "1234", "", "", "Ramos Mejia", Provincias.BUENOS_AIRES, "12AB");
        });
    }

    private void dadoQueHayUnaCuentaIniciada() {
        usuario.setEmail("juan.perez@test.com");
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setDomicilio(new Domicilio());

        when(sessionMock.getAttribute("usuarioLogueado")).thenReturn(usuario);
    }

    private void cuandoEditaSuPerfil(String nombre, String apellido, String nombreDeUsuario, String email,
                                     String telefono, String calle, String numero, String piso,
                                     String departamento, String ciudad, Provincias provincia, String codigoPostal) {
        DatosEdicionPerfil datosEdicionPerfil = new DatosEdicionPerfil(
                nombre, apellido, nombreDeUsuario, email, telefono,
                calle, numero, piso, departamento, ciudad, provincia, codigoPostal
        );

        servicioPerfil.guardarCambiosPerfil(datosEdicionPerfil);
    }

}
