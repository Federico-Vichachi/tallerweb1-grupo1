package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioRegistroImpl;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class ControladorRegistroTest {

    ControladorRegistro controladorRegistro = new ControladorRegistro(new ServicioRegistroImpl());

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConDatosValidos_EntoncesElRegistroEsExitoso() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "StrongPass@2025", "StrongPass@2025", Roles.USUARIO_COMUN);

        entoncesElRegistroEsExitoso(mav);
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinEmail_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("", "StrongPass@2025", "StrongPass@2025", Roles.USUARIO_COMUN);

        entoncesElRegistroFalla(mav, "El email es obligatorio.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinContrasenia_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "", "StrongPass@2025", Roles.USUARIO_COMUN);

        entoncesElRegistroFalla(mav, "La contraseña es olbigatoria.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraSinRepeticionDeContrasenia_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "StrongPass@2025", "", Roles.USUARIO_COMUN);

        entoncesElRegistroFalla(mav, "Por favor, repita la contraseña.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConContraseniaYRepeticionDeContraseniaDistintas_EntoncesElRegistroFalla(){
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "StrongPass@2025", "StrongPass@2026", Roles.USUARIO_COMUN);

        entoncesElRegistroFalla(mav, "La repetición de la contraseña no coincide con la contraseña.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnEmailInvalido_EntoncesElRegistroFalla(){
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("email.com", "StrongPass@2025", "StrongPass@2025", Roles.USUARIO_COMUN);

        entoncesElRegistroFalla(mav, "El formato del email es inválido.");
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConUnaContraseniaInvalida_EntoncesElRegistroFalla(){
        dadoQueNoExisteUnUsuarioConEseEmail();

        ModelAndView mav = cuandoSeRegistra("test@gmail.com", "weakpass2025", "weakpass2025", Roles.USUARIO_COMUN);

        entoncesElRegistroFalla(mav, "El formato de la contraseña es inválido.");
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