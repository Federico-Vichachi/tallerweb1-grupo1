package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Producto;
import com.tallerwebi.dominio.ServicioProducto;
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

    @Autowired
    public ControladorProducto(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
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

}
