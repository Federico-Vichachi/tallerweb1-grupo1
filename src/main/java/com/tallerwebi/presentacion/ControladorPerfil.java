package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorPerfil {
    @RequestMapping(path = "/perfil", method = RequestMethod.GET)
    public ModelAndView irAlPerfil(HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        model.put("usuario", usuarioLogueado);

        return new ModelAndView("perfil", model);
    }
}
