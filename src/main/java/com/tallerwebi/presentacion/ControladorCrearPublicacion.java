package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCrearPublicacion {

    ServicioCrearPublicacion servicioCrearPublicacion;

    @Autowired
    public ControladorCrearPublicacion(ServicioCrearPublicacion servicioCrearPublicacion) {
        this.servicioCrearPublicacion = servicioCrearPublicacion;
    }

    @RequestMapping(value = "/crear-publicacion", method =  RequestMethod.GET)
    public ModelAndView irCrearPublicacion() {
        ModelMap model = new ModelMap();
        model.put("datosAdopcion", new DatosAdopcion());
        model.put("datosRecaudacion", new DatosRecaudacion());
        model.put("datosSalud", new DatosSalud());
        model.put("datosPerdido", new DatosPerdido());
        model.put("datosEncontrado", new DatosEncontrado());
        return new ModelAndView("feed", model);
    }

    @RequestMapping(value = "/publicacion-adopcion", method = RequestMethod.POST)
    public ModelAndView crearPublicacionAdopcion(@ModelAttribute DatosAdopcion datosAdopcion) {
        PublicacionAdopcion publicacionDeAdopcion = new PublicacionAdopcion(datosAdopcion);
        servicioCrearPublicacion.guardar(publicacionDeAdopcion);
        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion adopcion guardada correctamente");
        model.put("datosAdopcion", datosAdopcion);
        return new ModelAndView("feed", model);
    }

    @RequestMapping(value = "/publicacion-recaudacion", method = RequestMethod.POST)
    public ModelAndView crearPublicacionRecaudacion(@ModelAttribute DatosRecaudacion datosRecaudacion) {

        PublicacionRecaudacion publicacionDeRecaudacion = new PublicacionRecaudacion(datosRecaudacion);
        servicioCrearPublicacion.guardar(publicacionDeRecaudacion);
        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion recaudacion creada correctamente");
        model.put("datosRecaudacion", datosRecaudacion);
        return new ModelAndView("feed", model);
    }

    @RequestMapping(value = "/publicacion-salud", method = RequestMethod.POST)
    public ModelAndView crearPublicacionSalud(@ModelAttribute DatosSalud datosSalud) {
        PublicacionSalud publicacionDeSalud = new PublicacionSalud(datosSalud);
        servicioCrearPublicacion.guardar(publicacionDeSalud);
        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion salud creada correctamente");
        model.put("datosSalud", datosSalud);
        return new ModelAndView("feed", model);
    }

    @RequestMapping(value = "/publicacion-perdido", method = RequestMethod.POST)
    public ModelAndView crearPublicacionPerdido(@ModelAttribute DatosPerdido datosPerdido) {

        PublicacionPerdido publicacionDePerdido = new PublicacionPerdido(datosPerdido);
        servicioCrearPublicacion.guardar(publicacionDePerdido);
        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion perdido creada correctamente");
        model.put("datosPerdido", datosPerdido);
        return new ModelAndView("feed", model);
    }

    @RequestMapping(value = "/publicacion-encontrado", method = RequestMethod.POST)
    public ModelAndView crearPublicacionEncontrado(@ModelAttribute DatosEncontrado datosEncontrado) {

        PublicacionEncontrado publicacionDeEncontrado = new PublicacionEncontrado(datosEncontrado);
        servicioCrearPublicacion.guardar(publicacionDeEncontrado);
        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion encontrado creada correctamente");
        model.put("datosEncontrado", datosEncontrado);
        return new ModelAndView("feed", model);
    }
}
