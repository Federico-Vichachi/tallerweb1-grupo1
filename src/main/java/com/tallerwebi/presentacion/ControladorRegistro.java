
package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {

    ServicioRegistro servicioRegistro;

    @Autowired
    public ControladorRegistro(ServicioRegistro servicioRegistro) {
        this.servicioRegistro = servicioRegistro;
    }

    @RequestMapping(path = "/registro", method = RequestMethod.GET)
    public ModelAndView irAlRegistro() {
        return new ModelAndView("registro");
    }

    @RequestMapping(path = "/registrar", method = RequestMethod.POST)
    public ModelAndView registrar(String email, String contrasenia, String repeticionDeContrasenia, Roles rol) {

        ModelMap model = new ModelMap();

        if( email == null || email.isEmpty()) {
            return devolverRegistroFallido(model, "El email es obligatorio.");
        }

        if( contrasenia == null || contrasenia.isEmpty()) {
            return devolverRegistroFallido(model, "La contraseña es olbigatoria.");
        }

        if (repeticionDeContrasenia == null || repeticionDeContrasenia.isEmpty()) {
            return devolverRegistroFallido(model, "Por favor, repita la contraseña.");
        }

        if (!contrasenia.equals(repeticionDeContrasenia)) {
           return devolverRegistroFallido(model, "La repetición de la contraseña no coincide con la contraseña.");
        }

        try {
            servicioRegistro.registrarUsuario(email, contrasenia, rol);
        } catch (Exception e) {
            return devolverRegistroFallido(model, e.getMessage());
        }

        return new ModelAndView("login");
    }

    private ModelAndView devolverRegistroFallido(ModelMap model, String mensajeDeError) {
        model.put("error", mensajeDeError);
        return new ModelAndView("registro", model);
    }

}
