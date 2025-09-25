package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ContraseniaNoContieneNumeroException;
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

        Usuario usuario = cuandoSeRegistra("test@gmail.com", "password123", "password123", Roles.USUARIO_COMUN);

        entoncesElRegistroEsExitoso(usuario);
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnaContraseniaQueNoContieneAlMenosUnNumero_EntoncesElRegistroFalla(){
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(ContraseniaNoContieneNumeroException.class, () -> cuandoSeRegistra("testgmail.com", "password", "password", Roles.USUARIO_COMUN));
    }

    private void dadoQueNoExisteUnUsuarioConEseEmail() {
    }

    private Usuario cuandoSeRegistra(String email, String contrasenia, String repeticionDeContrasenia, Roles rol) {
        return servicioRegistro.registrar(email, contrasenia, repeticionDeContrasenia, rol);
    }

    private void entoncesElRegistroEsExitoso(Usuario usuario) {
        assertThat(usuario, notNullValue());
    }

    private void entoncesElRegistroFalla(Usuario usuario) {
        assertThat(usuario, nullValue());
    }
}