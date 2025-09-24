package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorFeed {

    @RequestMapping(path= "/feed", method = RequestMethod.GET)
    public ModelAndView irAFeed(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Integer provincia,
            @RequestParam(required = false) Integer localidad,
            @RequestParam(required = false) List<String> tags
    ){
        ModelMap model = new ModelMap();
        List<Publicacion> publicaciones = new ArrayList<>();
        model.put("publicaciones", publicaciones);
        model.put("categoriaFiltro",categoria);
        model.put("provinciaFiltro",provincia);
        model.put("localidadFiltro",localidad);
        model.put("tagsFiltro",tags);
        return new ModelAndView("feed", model);
    }

}
