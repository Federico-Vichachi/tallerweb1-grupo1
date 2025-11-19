package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorHome {
    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAlHome(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        model.addAttribute("usuario", usuario);
        return new ModelAndView("home", model);
    }
}

