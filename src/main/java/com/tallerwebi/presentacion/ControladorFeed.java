package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.ServicioPublicacion;
import com.tallerwebi.dominio.excepcion.CategoriaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
public class ControladorFeed {

    private final ServicioPublicacion servicioPublicacion;

    @Autowired
    public ControladorFeed(ServicioPublicacion servicioPublicacion) {
        this.servicioPublicacion = servicioPublicacion;
    }

    @RequestMapping(path = "/feed", method = RequestMethod.GET)
    public ModelAndView irAFeed(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer provincia,
            @RequestParam(required = false) Integer localidad,
            @RequestParam(required = false) List<String> tags) {

        ModelMap model = new ModelMap();

        model.put("categoriaFiltro", categoria);
        model.put("nombreFiltro", nombre);
        model.put("provinciaFiltro", provincia);
        model.put("localidadFiltro", localidad);
        model.put("tagsFiltro", tags);

        try {
            List<Publicacion> publicaciones = servicioPublicacion.buscarPublicacionesPorCategoria(categoria);
            model.put("publicaciones", publicaciones);

            if (publicaciones.isEmpty() && categoria != null && !categoria.trim().isEmpty()) {
                model.put("mensaje", "No se encontraron publicaciones para la categoría '" + categoria + "'.");
            }

        } catch (CategoriaInvalidaException e) {
            return devolverFeedFallido(model, e.getMessage());
        }

        return new ModelAndView("feed", model);
    }


    private ModelAndView devolverFeedFallido(ModelMap model, String mensajeDeError) {
        model.put("error", mensajeDeError);
        model.put("publicaciones", Collections.emptyList());
        return new ModelAndView("feed", model);
    }
}