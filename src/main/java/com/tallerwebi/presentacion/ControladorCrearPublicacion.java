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

    private final ServicioPublicacion servicioPublicacion;
    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public ControladorCrearPublicacion(ServicioPublicacion servicioPublicacion, RepositorioUsuario repositorioUsuario) {
        this.servicioPublicacion = servicioPublicacion;
        this.repositorioUsuario = repositorioUsuario;
    }

    @RequestMapping(value = "/crear-publicacion", method =  RequestMethod.GET)
    public ModelAndView irCrearPublicacion() {
        ModelMap model = new ModelMap();
        model.put("datosAdopcion", new DatosAdopcion());
        model.put("datosRecaudacion", new DatosRecaudacion());
        model.put("datosSalud", new DatosSalud());
        model.put("datosPerdido", new DatosPerdido());
        model.put("datosEncontrado", new DatosEncontrado());
        return new ModelAndView("crear-publicacion", model);
    }

    @RequestMapping(value = "/publicacion-adopcion", method = RequestMethod.POST)
    public ModelAndView crearPublicacionAdopcion(@ModelAttribute DatosAdopcion datosAdopcion) {
        Usuario usuario = repositorioUsuario.buscarPorId(1L);

        PublicacionAdopcion publicacionDeAdopcion = new PublicacionAdopcion(datosAdopcion);
        servicioPublicacion.guardar(publicacionDeAdopcion);
        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion adopcion guardada correctamente");
        model.put("datosAdopcion", datosAdopcion);
        return new ModelAndView("publicacion-adopcion", model);
    }

    @RequestMapping(value = "/publicacion-recaudacion", method = RequestMethod.POST)
    public ModelAndView crearPublicacionRecaudacion(@ModelAttribute DatosRecaudacion datosRecaudacion) {
        Usuario usuario = repositorioUsuario.buscarPorId(1L);

        PublicacionRecaudacion publicacionDeRecaudacion = new PublicacionRecaudacion(datosRecaudacion);
        servicioPublicacion.guardar(publicacionDeRecaudacion);
        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion recaudacion creada correctamente");
        model.put("datosRecaudacion", datosRecaudacion);
        return new ModelAndView("publicacion-recaudacion", model);
    }

    @RequestMapping(value = "/publicacion-salud", method = RequestMethod.POST)
    public ModelAndView crearPublicacionSalud(@ModelAttribute DatosSalud datosSalud) {
        Usuario usuario = repositorioUsuario.buscarPorId(1L);

        PublicacionSalud publicacionDeSalud = new PublicacionSalud(datosSalud);
        servicioPublicacion.guardar(publicacionDeSalud);
        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion salud creada correctamente");
        model.put("datosSalud", datosSalud);
        return new ModelAndView("publicacion-salud", model);
    }

    @RequestMapping(value = "/publicacion-perdido", method = RequestMethod.POST)
    public ModelAndView crearPublicacionPerdido(@ModelAttribute DatosPerdido datosPerdido) {
        Usuario usuario = repositorioUsuario.buscarPorId(1L);

        PublicacionPerdido publicacionDePerdido = new PublicacionPerdido(datosPerdido);
        servicioPublicacion.guardar(publicacionDePerdido);
        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion perdido creada correctamente");
        model.put("datosPerdido", datosPerdido);
        return new ModelAndView("publicacion-perdido", model);
    }

    @RequestMapping(value = "/publicacion-encontrado", method = RequestMethod.POST)
    public ModelAndView crearPublicacionEncontrado(@ModelAttribute DatosEncontrado datosEncontrado) {
        Usuario usuario = repositorioUsuario.buscarPorId(1L);

        PublicacionEncontrado publicacionDeEncontrado = new PublicacionEncontrado(datosEncontrado);
        servicioPublicacion.guardar(publicacionDeEncontrado);
        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion encontrado creada correctamente");
        model.put("datosEncontrado", datosEncontrado);
        return new ModelAndView("publicacion-encontrado", model);
    }
}