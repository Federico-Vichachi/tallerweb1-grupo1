package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.ServicioPublicacion;
import com.tallerwebi.dominio.excepcion.PublicacionNoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorPublicacion {

    private final ServicioPublicacion servicioPublicacion;

    @Autowired
    public ControladorPublicacion(ServicioPublicacion servicioPublicacion) {
        this.servicioPublicacion = servicioPublicacion;
    }

    @RequestMapping(path = "/publicacion/{id}", method = RequestMethod.GET)
    public ModelAndView irADetallePublicacion(@PathVariable("id") Long id) {
        ModelMap model = new ModelMap();

        try {
            Publicacion publicacionEncontrada = servicioPublicacion.buscarPorId(id);
            model.put("publicacion", publicacionEncontrada);

            return new ModelAndView("publicacion-detalle", model);

        } catch (PublicacionNoEncontradaException e) {
            model.put("mensajeError", e.getMessage());
            return new ModelAndView("publicacion-no-encontrada", model);
        }
    }
}