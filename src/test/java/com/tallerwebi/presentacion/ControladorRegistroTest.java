package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioRegistroImpl;
import org.dom4j.rule.Mode;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.management.relation.Role;
import java.security.SecureRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class ControladorRegistroTest {

    ControladorRegistro controladorRegistro = new ControladorRegistro(new ServicioRegistroImpl());

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConDatosValidos_EntoncesElRegistroEsExitoso(){
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "password123", "password123", Roles.USUARIO_COMUN);

        entoncesElRegistroEsExitoso(mav);
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConContraseniaYRepeticionDeContraseniaDistintas_EntoncesElRegistroFalla(){
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "password123", "password456", Roles.USUARIO_COMUN);

        entoncesElRegistroFalla(mav, "La repetición de la contraseña no coincide con la contraseña.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnaContraseniaQueNoContieneAlMenosUnNumero_EntoncesElRegistroFalla(){
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("testgmail.com", "password", "password", Roles.USUARIO_COMUN);

        entoncesElRegistroFalla(mav, "La contraseña debe contener al menos un número.");
    }



    private ModelAndView cuandoSeRegistra(String email, String contrasenia, String repeticionDeContrasenia, Roles rol) {
        return controladorRegistro.registrar(email, contrasenia, repeticionDeContrasenia, rol);
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