package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.FormatoDeContraseniaInvalidoException;
import com.tallerwebi.dominio.excepcion.FormatoDeEmailInvalidoException;
import com.tallerwebi.presentacion.Roles;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServicioRegistroTest {

    ServicioRegistro servicioRegistro = new ServicioRegistroImpl();

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConDatosValidos_EntoncesElRegistroEsExitoso() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        Usuario usuario = cuandoSeRegistra("test@gmail.com", "StrongPass@2025", Roles.USUARIO_COMUN);

        entoncesElRegistroEsExitoso(usuario);
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConEmailInvalido_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeEmailInvalidoException.class, () -> {
            cuandoSeRegistra("invalid-email", "StrongPass@2025", Roles.USUARIO_COMUN);
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConContraseniaInvalida_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeContraseniaInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "weakpass2025", Roles.USUARIO_COMUN);
        });
    }



    private void dadoQueNoExisteUnUsuarioConEseEmail() {
    }

    private Usuario cuandoSeRegistra(String email, String contrasenia, Roles rol) {
        return servicioRegistro.registrar(email, contrasenia, rol);
    }

    private void entoncesElRegistroEsExitoso(Usuario usuario) {
        assertThat(usuario, notNullValue());
    }

    private void entoncesElRegistroFalla(Usuario usuario) {
        assertThat(usuario, nullValue());
    }
}