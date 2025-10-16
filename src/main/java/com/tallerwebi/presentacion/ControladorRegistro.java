package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioRegistro;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.Domicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        ModelMap model = new ModelMap();
        model.put("datosRegistro", new DatosRegistro());
        return new ModelAndView("registro", model);
    }

    @RequestMapping(path = "/registrar", method = RequestMethod.POST)
    public ModelAndView registrar(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {

        ModelMap model = new ModelMap();
        ModelAndView camposObligatoriosVacios = verificarCamposObligatoriosVacios(datosRegistro, model);
        if (camposObligatoriosVacios.getModelMap().containsKey("error")) {
            return camposObligatoriosVacios;
        }

        try {
            servicioRegistro.registrarUsuario(datosRegistro);
        } catch (Exception e) {
            return devolverRegistroFallido(model, e.getMessage());
        }

        return new ModelAndView("inicio-de-sesion");
    }


    private ModelAndView verificarCamposObligatoriosVacios(DatosRegistro datosRegistro, ModelMap model) {
        if (datosRegistro.getEmail() == null || datosRegistro.getEmail().trim().isEmpty()) {
            return devolverRegistroFallido(model, "El email es obligatorio.");
        }
        if (datosRegistro.getNombre() == null || datosRegistro.getNombre().trim().isEmpty()) {
            return devolverRegistroFallido(model, "El nombre es obligatorio.");
        }
        if (datosRegistro.getApellido() == null || datosRegistro.getApellido().trim().isEmpty()) {
            return devolverRegistroFallido(model, "El apellido es obligatorio.");
        }
        if (datosRegistro.getNombreDeUsuario() == null || datosRegistro.getNombreDeUsuario().trim().isEmpty()) {
            return devolverRegistroFallido(model, "El nombre de usuario es obligatorio.");
        }
        if (datosRegistro.getTelefono() == null || datosRegistro.getTelefono().trim().isEmpty()) {
            return devolverRegistroFallido(model, "El teléfono es obligatorio.");
        }
        if (datosRegistro.getContrasenia() == null || datosRegistro.getContrasenia().trim().isEmpty()) {
            return devolverRegistroFallido(model, "La contraseña es obligatoria.");
        }
        if (datosRegistro.getRepeticionDeContrasenia() == null || datosRegistro.getRepeticionDeContrasenia().trim().isEmpty()) {
            return devolverRegistroFallido(model, "Por favor, repita la contraseña.");
        }
        if (!datosRegistro.getContrasenia().equals(datosRegistro.getRepeticionDeContrasenia())) {
            return devolverRegistroFallido(model, "La repetición de la contraseña no coincide con la contraseña.");
        }
        if (datosRegistro.getRol() == null) {
            return devolverRegistroFallido(model, "El rol es obligatorio.");
        }
        if (datosRegistro.getCalle() == null || datosRegistro.getCalle().trim().isEmpty()) {
            return devolverRegistroFallido(model, "La calle es obligatoria.");
        }
        if (datosRegistro.getNumero() == null || datosRegistro.getNumero().trim().isEmpty()) {
            return devolverRegistroFallido(model, "El número de domicilio es obligatorio.");
        }
        if (datosRegistro.getCiudad() == null || datosRegistro.getCiudad().trim().isEmpty()) {
            return devolverRegistroFallido(model, "La ciudad es obligatoria.");
        }
        if (datosRegistro.getProvincia() == null) {
            return devolverRegistroFallido(model, "La provincia es obligatoria.");
        }
        if (datosRegistro.getCodigoPostal() == null || datosRegistro.getCodigoPostal().trim().isEmpty()) {
            return devolverRegistroFallido(model, "El código postal es obligatorio.");
        }
        return new  ModelAndView("registro", model);
    }

    private ModelAndView devolverRegistroFallido(ModelMap model, String mensajeDeError) {
        model.put("error", mensajeDeError);
        return new ModelAndView("registro", model);
    }

}
