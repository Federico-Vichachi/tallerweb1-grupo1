package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.ServicioComentario;
import com.tallerwebi.dominio.ServicioPublicacion;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.ComentarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorComentario {

    private final ServicioComentario servicioComentario;
    private final ServicioPublicacion servicioPublicacion;

    @Autowired
    public ControladorComentario(ServicioComentario servicioComentario, ServicioPublicacion servicioPublicacion){
        this.servicioComentario = servicioComentario;
        this.servicioPublicacion = servicioPublicacion;
    }

    @RequestMapping(path = "/comentario/guardar", method = RequestMethod.POST)
    public ModelAndView guardarComentario(
            @RequestParam("texto") String texto,
            @RequestParam("publicacionId") Long publicacionId,
            HttpServletRequest request) {

        Usuario autor = (Usuario) request.getSession().getAttribute("usuarioLogueado");

        Publicacion publicacion = servicioPublicacion.buscarPorId(publicacionId);

        try {
            servicioComentario.guardarComentario(texto, autor, publicacion);
            return new ModelAndView("redirect:/publicacion/" + publicacionId);

        } catch (ComentarioException e) {

            ModelMap model = new ModelMap();

            model.put("publicacion", publicacion);
            model.put("errorComentario", e.getMessage());

            return new ModelAndView("publicacion-detalle", model);
        }
    }
}