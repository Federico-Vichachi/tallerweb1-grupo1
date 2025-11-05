package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioPerfil;
import com.tallerwebi.dominio.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class ControladorPerfil {

    ServicioPerfil servicioPerfil;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    public ControladorPerfil(ServicioPerfil servicioPerfil) {
        this.servicioPerfil = servicioPerfil;
    }

    @RequestMapping(path = "/perfil", method = RequestMethod.GET)
    public ModelAndView irAlPerfil(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return new ModelAndView("redirect:/inicio-de-sesion");
        }

        model.put("usuario", usuario);
        return new ModelAndView("perfil", model);
    }

    @RequestMapping(path = "/perfil/editar", method = RequestMethod.GET)
    public ModelAndView irAEditarPerfil(HttpServletRequest request) {
        ModelMap model = new ModelMap();
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return new ModelAndView("redirect:/inicio-de-sesion");
        }

        DatosEdicionPerfil datosEdicionPerfil = new DatosEdicionPerfil(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getNombreDeUsuario(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getDomicilio().getCalle(),
                usuario.getDomicilio().getNumero(),
                usuario.getDomicilio().getPiso(),
                usuario.getDomicilio().getDepartamento(),
                usuario.getDomicilio().getCiudad(),
                usuario.getDomicilio().getProvincia(),
                usuario.getDomicilio().getCodigoPostal()
        );

        model.put("usuario", usuario);
        model.put("datosEdicionPerfil", datosEdicionPerfil);

        return new ModelAndView("editar-perfil", model);
    }


    @RequestMapping(path = "/perfil/editar/guardar", method = RequestMethod.POST)
    public ModelAndView guardarPerfil(@ModelAttribute("datosEdicionPerfil") DatosEdicionPerfil datosEdicionPerfil,
                                      HttpServletRequest request) {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return new ModelAndView("redirect:/inicio-de-sesion");
        }

        datosEdicionPerfil.setId(usuario.getId());

        servicioPerfil.guardarCambiosPerfil(datosEdicionPerfil);

        Usuario usuarioActualizado = servicioPerfil.buscarPorId(usuario.getId());
        session.setAttribute("usuarioLogueado", usuarioActualizado);

        return new ModelAndView("redirect:/perfil");
    }


}
