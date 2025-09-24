package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCrearPublicacion {

    @RequestMapping("/crear-publicacion")
    public ModelAndView irCrearPublicacion(){
        ModelMap model = new ModelMap();
        model.put("datosAdopcion", new DatosAdopcion());
        model.put("datosRecaudacion", new DatosRecaudacion());
        model.put("datosSalud", new DatosSalud());
        model.put("datosPerdido", new DatosPerdido());
        model.put("datosEncontrado", new DatosEncontrado());
        return new ModelAndView("crear-publicacion", model);
    }

    //Ir a vista adopcion.
    @RequestMapping(value = "/publicacion-adopcion-creada", method = RequestMethod.POST)
    public ModelAndView irPublicacionAdopcionCreada(DatosAdopcion datosAdopcion){
        ModelMap model = new ModelMap();
        model.put("datosAdopcion", datosAdopcion);
        return new ModelAndView("publicacion-adopcion-creada", model);
    }

    //Ir a vista recaudacion.
    @RequestMapping(value = "/publicacion-recaudacion-creada", method = RequestMethod.POST)
    public ModelAndView irPublicacionRecaudacionCreada(DatosRecaudacion datosRecaudacion){
        ModelMap model = new ModelMap();
        model.put("datosRecaudacion", datosRecaudacion);
        return new ModelAndView("publicacion-recaudacion-creada", model);
    }

    @RequestMapping(value = "/publicacion-salud-creada" , method = RequestMethod.POST)
    public ModelAndView irPublicacionSaludCreada(DatosSalud datosSalud){
        ModelMap model = new ModelMap();
        model.put("datosSalud", datosSalud);
        return new ModelAndView("publicacion-salud-creada", model);
    }

    @RequestMapping(value = "/publicacion-perdido-creada" , method = RequestMethod.POST)
    public ModelAndView irPublicacionPerdidoCreada(DatosPerdido datosPerdido){
        ModelMap model = new ModelMap();
        model.put("datosPerdido", datosPerdido);
        return new ModelAndView("publicacion-perdido-creada", model);
    }

    @RequestMapping(value = "/publicacion-encontrado-creada" , method = RequestMethod.POST)
    public ModelAndView irPublicacionEncontradoCreada(DatosEncontrado datosEncontrado){
        ModelMap model = new ModelMap();
        model.put("datosEncontrado", datosEncontrado);
        return new ModelAndView("publicacion-encontrado-creada", model);
    }


}
