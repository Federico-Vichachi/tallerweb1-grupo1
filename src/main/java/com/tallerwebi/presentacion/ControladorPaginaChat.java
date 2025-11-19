package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Mensaje;
import com.tallerwebi.dominio.ServicioMensaje;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.dominio.excepcion.ChatInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorPaginaChat {

    private final ServicioMensaje servicioMensaje;

    @Autowired
    public ControladorPaginaChat(ServicioMensaje servicioMensaje) {
        this.servicioMensaje = servicioMensaje;
    }

    @RequestMapping(path = "/chat", method = RequestMethod.GET)
    public ModelAndView irAChat(
            @RequestParam(value = "iniciarCon", required = false) String destinatarioUsername,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            return new ModelAndView("redirect:/inicio-de-sesion");
        }


        try {
            ModelMap model = new ModelMap();

            List<Usuario> conversaciones = servicioMensaje.obtenerConversaciones(usuarioLogueado.getNombreDeUsuario());
            model.put("conversaciones", conversaciones);
            model.put("usuarioLogueado", usuarioLogueado);

            if (destinatarioUsername != null) {
                List<Mensaje> historial = servicioMensaje.iniciarYObtenerConversacion(usuarioLogueado.getNombreDeUsuario(), destinatarioUsername);
                model.put("historialMensajes", historial);
                model.put("destinatarioActivo", destinatarioUsername);
            }

            return new ModelAndView("chat", model);

        } catch (ChatInvalidoException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:/feed");
        }
    }
}