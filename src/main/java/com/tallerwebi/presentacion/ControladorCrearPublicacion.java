package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import com.tallerwebi.dominio.excepcion.ValidacionPublicacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControladorCrearPublicacion {

    private final ServicioPublicacion servicioPublicacion;
    private final ServicioPuntos servicioPuntos;

    @Autowired
    public ControladorCrearPublicacion(ServicioPublicacion servicioPublicacion, ServicioPuntos servicioPuntos) {
        this.servicioPublicacion = servicioPublicacion;

        this.servicioPuntos = servicioPuntos;
    }

    @RequestMapping(value = "/crear-publicacion", method =  RequestMethod.GET)
    public ModelAndView irCrearPublicacion() {
        ModelMap model = new ModelMap();
        model.put("datosAdopcion", new DatosAdopcion());
        model.put("datosRecaudacion", new DatosRecaudacion());
        model.put("datosSalud", new DatosSalud());
        model.put("datosPerdido", new DatosPerdido());
        model.put("datosEncontrado", new DatosEncontrado());
        model.put("provincias", Provincias.values());
        return new ModelAndView("crear-publicacion", model);
    }


    @RequestMapping(value = "/publicacion-adopcion", method = RequestMethod.POST)
    public ModelAndView crearPublicacionAdopcion(@ModelAttribute DatosAdopcion datosAdopcion, HttpSession session) {
            ModelMap model = new ModelMap();

        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
            PublicacionAdopcion publicacionDeAdopcion = new PublicacionAdopcion(datosAdopcion);
            publicacionDeAdopcion.setUsuario(usuario);
            servicioPublicacion.guardar(publicacionDeAdopcion);

            servicioPuntos.sumarPuntos(usuario, publicacionDeAdopcion);

            model.put("mensaje", "Publicacion adopcion guardada correctamente");
            model.put("datosAdopcion", datosAdopcion);
            return new ModelAndView("redirect:/feed");
        }catch(ValidacionPublicacionException e){
            model.put("error", e.getMessage());
            return new ModelAndView("crear-publicacion", model);
        }
    }


    @RequestMapping(value = "/publicacion-recaudacion", method = RequestMethod.POST)
    public ModelAndView crearPublicacionRecaudacion(@ModelAttribute DatosRecaudacion datosRecaudacion, HttpSession session) {
            ModelMap model = new ModelMap();
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
            PublicacionRecaudacion publicacionDeRecaudacion = new PublicacionRecaudacion(datosRecaudacion);
            publicacionDeRecaudacion.setUsuario(usuario);
            servicioPublicacion.guardar(publicacionDeRecaudacion);



            model.put("mensaje", "Publicacion recaudacion creada correctamente");
            model.put("datosRecaudacion", datosRecaudacion);
            return new ModelAndView("redirect:/feed");
        }catch(ValidacionPublicacionException e){
            model.put("error", e.getMessage());
            return new ModelAndView("crear-publicacion", model);
        }
    }

    @RequestMapping(value = "/publicacion-salud", method = RequestMethod.POST)
    public ModelAndView crearPublicacionSalud(@ModelAttribute DatosSalud datosSalud, HttpSession session) {
        ModelMap model = new ModelMap();
    try {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        PublicacionSalud publicacionDeSalud = new PublicacionSalud(datosSalud);
        publicacionDeSalud.setUsuario(usuario);
        servicioPublicacion.guardar(publicacionDeSalud);
        model.put("mensaje", "Publicacion salud creada correctamente");
        model.put("datosSalud", datosSalud);
        return new ModelAndView("redirect:/feed");
    }catch (ValidacionPublicacionException e){
        model.put("error", e.getMessage());
        return new ModelAndView("crear-publicacion", model);
        }
    }

    @RequestMapping(value = "/publicacion-perdido", method = RequestMethod.POST)
    public ModelAndView crearPublicacionPerdido(@ModelAttribute DatosPerdido datosPerdido, HttpSession session) {
        ModelMap model = new ModelMap();
    try {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        PublicacionPerdido publicacionDePerdido = new PublicacionPerdido(datosPerdido);
        publicacionDePerdido.setUsuario(usuario);
        servicioPublicacion.guardar(publicacionDePerdido);

        model.put("mensaje", "Publicacion perdido creada correctamente");
        model.put("datosPerdido", datosPerdido);
        return new ModelAndView("redirect:/feed");

    }catch (ValidacionPublicacionException e){
        model.put("error", e.getMessage());
        return new ModelAndView("crear-publicacion", model);
        }
    }

    @RequestMapping(value = "/publicacion-encontrado", method = RequestMethod.POST)
    public ModelAndView crearPublicacionEncontrado(@ModelAttribute DatosEncontrado datosEncontrado, HttpSession session) {
        ModelMap model = new ModelMap();
    try {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        PublicacionEncontrado publicacionDeEncontrado = new PublicacionEncontrado(datosEncontrado);
        publicacionDeEncontrado.setUsuario(usuario);
        servicioPublicacion.guardar(publicacionDeEncontrado);
        model.put("mensaje", "Publicacion encontrado creada correctamente");
        model.put("datosEncontrado", datosEncontrado);
        return new ModelAndView("redirect:/feed");
    }catch (ValidacionPublicacionException e){
        model.put("error", e.getMessage());
        return new ModelAndView("crear-publicacion", model);
        }
    }
}
