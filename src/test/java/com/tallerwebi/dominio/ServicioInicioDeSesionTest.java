package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.DatosDeInicioDeSesionIncorrectosException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ServicioInicioDeSesionTest {

    RepositorioUsuario repositorioUsuarioMock;
    ServicioInicioDeSesion servicioInicioDeSesion;

    @BeforeEach
    public void init() {
        repositorioUsuarioMock = org.mockito.Mockito.mock(RepositorioUsuario.class);
        servicioInicioDeSesion = new ServicioInicioDeSesionImpl(repositorioUsuarioMock);
    }

    @Test
    public void dadoQueExisteUnUsuario_CuandoIniciaSesionConDatosCorrectos_EntoncesElInicioDeSesionEsExitoso() {
        dadoQueExisteUnUsuario("usuario@test.com", "Contrasenia123@");

        Usuario usuario = cuandoIniciaSesion("usuario@test.com", "Contrasenia123@");

        entoncesElInicioDeSesionEsExitoso(usuario, "usuario@test.com", "Contrasenia123@");
    }

    @Test void dadoQueExisteUnUsuario_CuandoIniciaSesionConEmailIncorrecto_EntoncesSeLanzaUnaDatosDeInicioDeSesionIncorrectosException() {
        dadoQueExisteUnUsuario("usuario@test.com", "Contrasenia123@");

        assertThrows(DatosDeInicioDeSesionIncorrectosException.class, () -> {
            cuandoIniciaSesion("email@incorrecto.com", "Contrasenia123@");
        });
    }

    @Test void dadoQueExisteUnUsuario_CuandoIniciaSesionConContraseniaIncorrecta_EntoncesSeLanzaUnaDatosDeInicioDeSesionIncorrectosException() {
        dadoQueExisteUnUsuario("usuario@test.com", "Contrasenia123@");

        assertThrows(DatosDeInicioDeSesionIncorrectosException.class, () -> {
            cuandoIniciaSesion("usuario@test.com", "ContraseniaIncorrecta123@");
        });
    }



    private void dadoQueExisteUnUsuario(String email, String contrasenia) {
        Domicilio domicilio = new Domicilio("Calle Falsa", "1234", "Springfield", Provincias.BUENOS_AIRES, "1234", "A", "1");
        Usuario usuario = new Usuario("Test", "Usuario", email, "1112345678", contrasenia, "testusuario", Roles.USUARIO_COMUN, domicilio);

        when(repositorioUsuarioMock.buscarPorEmail("usuario@test.com"))
                .thenReturn(usuario);
    }


    private Usuario cuandoIniciaSesion(String email, String contrasenia) {
        return servicioInicioDeSesion.iniciarSesion(email, contrasenia);
    }

    private void entoncesElInicioDeSesionEsExitoso(Usuario usuario, String email, String contrasenia) {
        assertEquals(email, usuario.getEmail());
        assertEquals(contrasenia, usuario.getContrasenia());

    }

}
