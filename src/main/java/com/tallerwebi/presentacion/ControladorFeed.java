package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Provincias;
import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.ServicioPublicacion;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.CategoriaInvalidaException;
import com.tallerwebi.dominio.DatosFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
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
            HttpServletRequest request,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Provincias provincia,
            @RequestParam(required = false) String localidad) {

        ModelMap model = new ModelMap();

        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        model.put("usuarioLogueado", usuarioLogueado);
        model.put("provincias", Provincias.values());

        model.put("categoriaFiltro", categoria);
        model.put("nombreFiltro", nombre);
        model.put("provinciaFiltro", provincia);
        model.put("localidadFiltro", localidad);

        try {
            DatosFiltro datosFiltro = new DatosFiltro();
            datosFiltro.setCategoria(categoria);
            datosFiltro.setNombre(nombre);
            datosFiltro.setProvincia(provincia);
            datosFiltro.setLocalidad(localidad);

            List<Publicacion> publicacionesFinales = servicioPublicacion.buscarPublicacionesConFiltros(datosFiltro);
            model.put("publicaciones", publicacionesFinales);

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