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

        // Validar campos obligatorios vacíos
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
        if (datosRegistro.getRol() == null) {
            return devolverRegistroFallido(model, "El rol es obligatorio.");
        }
        if (datosRegistro.getCalle() == null || datosRegistro.getCalle().trim().isEmpty()) {
            return devolverRegistroFallido(model, "La calle es obligatoria.");
        }
        if (datosRegistro.getNumero() == null || datosRegistro.getNumero().trim().isEmpty()) {
            return devolverRegistroFallido(model, "El número es obligatorio.");
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

        // Validar que las contraseñas coincidan
        if (!datosRegistro.getContrasenia().equals(datosRegistro.getRepeticionDeContrasenia())) {
            return devolverRegistroFallido(model, "La repetición de la contraseña no coincide con la contraseña.");
        }

        try {
            Usuario usuario = new Usuario();
            usuario.setNombre(datosRegistro.getNombre());
            usuario.setApellido(datosRegistro.getApellido());
            usuario.setNombreDeUsuario(datosRegistro.getNombreDeUsuario());
            usuario.setEmail(datosRegistro.getEmail());
            usuario.setTelefono(datosRegistro.getTelefono());
            usuario.setContrasenia(datosRegistro.getContrasenia());
            usuario.setRol(datosRegistro.getRol());
            usuario.setDomicilio(new Domicilio());
            usuario.getDomicilio().setCalle(datosRegistro.getCalle());
            usuario.getDomicilio().setNumero(datosRegistro.getNumero());
            usuario.getDomicilio().setCiudad(datosRegistro.getCiudad());
            usuario.getDomicilio().setProvincia(datosRegistro.getProvincia());
            usuario.getDomicilio().setCodigoPostal(datosRegistro.getCodigoPostal());
            usuario.getDomicilio().setDepartamento(datosRegistro.getDepartamento());
            usuario.getDomicilio().setPiso(datosRegistro.getPiso());

            servicioRegistro.registrarUsuario(usuario);
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
