package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioInicioDeSesion;
import com.tallerwebi.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorInicioDeSesion {

    private ServicioInicioDeSesion servicioInicioDeSesion;

    @Autowired
    public ControladorInicioDeSesion(ServicioInicioDeSesion servicioInicioDeSesion) {this.servicioInicioDeSesion = servicioInicioDeSesion;}

    @RequestMapping(path = "/inicio-de-sesion", method = RequestMethod.GET)
    public ModelAndView irAlInicioDeSesion() {
        ModelMap model = new ModelMap();
        model.put("datosInicioSesion", new DatosInicioSesion());
        return new ModelAndView("inicio-de-sesion", model);
    }

    @RequestMapping(path = "/iniciar-sesion", method =  RequestMethod.POST)
    public ModelAndView iniciarSesion(@ModelAttribute ("datosInicioSesion") DatosInicioSesion datosInicioSesion,
                                      HttpServletRequest request) {

        ModelMap model = new ModelMap();

        try {
            Usuario usuario = this.servicioInicioDeSesion.iniciarSesion(datosInicioSesion.getEmail(), datosInicioSesion.getContrasenia());
            request.getSession().setAttribute("usuarioLogueado", usuario);
        } catch (Exception e) {
            model.put("error", e.getMessage());
            return new ModelAndView("inicio-de-sesion", model);
        }

        return new ModelAndView("redirect:/feed");
    }
}
