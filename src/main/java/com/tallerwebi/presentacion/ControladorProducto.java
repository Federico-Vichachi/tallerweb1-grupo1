package com.tallerwebi.presentacion;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Controller
public class ControladorProducto {

    private final ServicioProducto servicioProducto;
    private final ServicioPuntos servicioPuntos;
    private final ServicioPago servicioPago;


    @Autowired
    public ControladorProducto(ServicioProducto servicioProducto, ServicioPuntos servicioPuntos, ServicioPago servicioPago) {
        this.servicioProducto = servicioProducto;
        this.servicioPuntos = servicioPuntos;
        this.servicioPago = servicioPago;
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

        return new ModelAndView("redirect:/tienda");
    }

    @RequestMapping(value = "/tienda")
    public ModelAndView verTienda(HttpSession session) {
        ModelMap model = new ModelMap();
        model.put("productos", servicioProducto.listarProductos());
        model.put("usuario", session.getAttribute("usuarioLogueado"));
        return new ModelAndView("tienda", model);
    }


    //----- PUNTOS -----
    @RequestMapping(value = "/canjear-producto", method = RequestMethod.POST)
    public ModelAndView canjearProducto(@RequestParam("id") Long id,@RequestParam("cantidad") int cantidad, HttpSession session, RedirectAttributes redirectAttributes) {
        ModelMap model = new ModelMap();



        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "Tenés que iniciar sesión para donar.");
            return new ModelAndView("redirect:/inicio-de-sesion");
        }

        Producto producto = servicioProducto.buscarPorId(id);

        if (producto == null) {
            redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
        } else if(producto.getStock() < cantidad) {
            redirectAttributes.addFlashAttribute("error", "No hay suficiente stock");
        }else {
            boolean pudoCanjear = servicioPuntos.gastarPuntos(usuario, producto, cantidad);
            if (pudoCanjear) {
                servicioProducto.descontarStock(producto, cantidad);
                redirectAttributes.addFlashAttribute("mensaje", "¡Canje exitoso! Gastaste " + producto.getPrecioEnPuntos() * cantidad + " puntos en " + producto.getNombre());
            } else {
                redirectAttributes.addFlashAttribute("error", "No tenes puntos suficientes ");
            }
        }

        return new ModelAndView("redirect:/tienda");
    }

    //----- MERCADO PAGO -----

    @RequestMapping(value = "/comprar-producto", method = RequestMethod.POST)
    public ModelAndView comprarProducto(@RequestParam("id") Long id,@RequestParam("cantidad") int cantidad ,HttpSession session) {
        ModelMap model = new ModelMap();

        try {

            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
            if (usuario == null) {
                model.put("error", "Tenés que iniciar sesión para donar.");
                return new ModelAndView("redirect:/inicio-de-sesion");
            }

            Producto producto = servicioProducto.buscarPorId(id);

            if (producto == null) {
                model.put("error", "Producto no encontrado");
                model.put("productos", servicioProducto.listarProductos());
                return new ModelAndView("tienda", model);
            }

            if (producto.getStock() < cantidad) {
                model.put("error", "No hay suficiente stock del producto");
                model.put("productos", servicioProducto.listarProductos());
                return new ModelAndView("tienda", model);
            }

            var  preference = servicioPago.generarLinkPago(producto,cantidad);
            if (preference == null){
                model.put("error", "Error al generar el link de pago");
                model.put("productos", servicioProducto.listarProductos());
                return new ModelAndView("tienda", model);
            }

            return new ModelAndView("redirect:" + preference.getInitPoint());

        }catch (Exception e){
            e.printStackTrace();
            model.put("error", "Error al iniciar el pago");
            model.put("productos", servicioProducto.listarProductos());
            return new ModelAndView("tienda", model);
        }
    }

    @RequestMapping("pago-exitoso")
    public ModelAndView pagoExitoso(@RequestParam("payment_id") String paymentId,
                                    @RequestParam("external_reference") String externalReference,
                                    @RequestParam(value = "status", required = false) String status,
                                    RedirectAttributes redirectAttributes) {
        ModelMap model = new ModelMap();

        try {
            String[] partes = externalReference.split("-");
            Long idProducto = Long.parseLong(partes[0]);
            int cantidad = partes.length > 1 ? Integer.parseInt(partes[1]) : 1;


            Producto producto = servicioProducto.buscarPorId(idProducto);

            if (producto == null) {
                redirectAttributes.addFlashAttribute("error", "El producto no existe.");
                return new ModelAndView("redirect:/tienda");
            }

            // Consulto el monto real pagado a Mercado Pago
            Double montoPagado = servicioPago.obtenerMontoDePago(paymentId);

            if ("approved".equalsIgnoreCase(status)) {
                servicioProducto.descontarStock(producto, cantidad);
                redirectAttributes.addFlashAttribute("mensaje", "¡Pago realizado con éxito!");
                redirectAttributes.addFlashAttribute("montoPagado", montoPagado);
                redirectAttributes.addFlashAttribute("producto", producto);
                redirectAttributes.addFlashAttribute("cantidad", cantidad);
            } else {
                redirectAttributes.addFlashAttribute("mensaje", "El pago no fue aprobado. Estado: " + status);
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al procesar el pago: " + e.getMessage());
        }

        return new ModelAndView("redirect:/tienda");
    }

    @RequestMapping("pago-fallido")
    public ModelAndView pagoFallido(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "El pago falló. Intentalo de nuevo.");
        return new ModelAndView("redirect:/tienda");
    }

    @RequestMapping("pago-pendiente")
    public ModelAndView pagoPendiente(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensaje", "El pago está pendiente de confirmación.");
        return new ModelAndView("redirect:/tienda");
    }

}
