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
public class ControladorProducto {

    private final ServicioProducto servicioProducto;
    private final ServicioPuntos servicioPuntos;
    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public ControladorProducto(ServicioProducto servicioProducto, ServicioPuntos servicioPuntos, RepositorioUsuario repositorioUsuario) {
        this.servicioProducto = servicioProducto;
        this.servicioPuntos = servicioPuntos;
        this.repositorioUsuario = repositorioUsuario;
    }


    @RequestMapping(value = "/crear-producto", method = RequestMethod.GET)
    public ModelAndView irCrearProducto(DatosProducto datosProducto) {
        ModelMap model = new ModelMap();
        model.put("datosProducto", new DatosProducto());
        return new ModelAndView("crear-producto", model);
    }

    @RequestMapping(value = "/producto", method = RequestMethod.POST)
    public ModelAndView crearProducto(@ModelAttribute DatosProducto datosProducto) {
        Producto producto = new Producto(datosProducto);
        servicioProducto.guardar(producto);
        ModelMap model = new ModelMap();
        model.put("mensaje", "Producto guardado correctamente");
        model.put("datosProducto", datosProducto);
        return new ModelAndView("tienda", model);
    }

    @RequestMapping(value = "/tienda")
    public ModelAndView verTienda() {
        ModelMap model = new ModelMap();
        model.put("productos", servicioProducto.listarProductos());
        return new ModelAndView("tienda", model);
    }

    @RequestMapping(value = "/canjear-producto", method = RequestMethod.POST)
    public ModelAndView canjearProducto(Long id) {
        ModelMap model = new ModelMap();


        Usuario usuario = repositorioUsuario.buscarPorId(1L);

        Producto producto = servicioProducto.buscarPorId(id);

        if (producto == null) {
            model.put("error", "Producto no encontrado 😢");
        } else {
            boolean pudoCanjear = servicioPuntos.gastarPuntos(usuario, producto);
            if (pudoCanjear) {
                model.put("mensaje", "¡Canje exitoso! Gastaste " + producto.getPrecioEnPuntos() + " puntos en " + producto.getNombre());
            } else {
                model.put("error", "No tenés puntos suficientes para este producto 😭");
            }
        }

        model.put("productos", servicioProducto.listarProductos());
        model.put("usuario", usuario); // para mostrar puntos en la vista
        return new ModelAndView("tienda", model);
    }
}
