package com.tallerwebi.presentacion;

import com.mercadopago.resources.preference.Preference;
import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ControladorDonacion {

    private final ServicioPublicacion servicioPublicacion;
    private final ServicioPago servicioPago;

    @Autowired
    public ControladorDonacion(ServicioPublicacion servicioPublicacion, ServicioPago servicioPago){
        this.servicioPublicacion = servicioPublicacion;
        this.servicioPago = servicioPago;
    }

    @RequestMapping(value = "/donar", method = RequestMethod.POST)
    public ModelAndView donar(@RequestParam("id") Long id,
                              @RequestParam("monto") Double monto,
                              HttpSession session) {

        ModelMap model = new ModelMap();

        try {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
            if (usuario == null) {
                model.put("error", "Tenés que iniciar sesión para donar.");
                return new ModelAndView("redirect:/inicio-de-sesion");
            }

            if (id == null || monto == null || monto <= 0) {
                model.put("error", "Monto inválido o publicación no especificada.");
                return new ModelAndView("redirect:/feed");
            }

            Publicacion publicacion = servicioPublicacion.obtenerTodasLasPublicaciones()
                    .stream()
                    .filter(p -> p.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (publicacion == null) {
                model.put("error", "No se encontró la publicación.");
                return new ModelAndView("redirect:/feed");
            }

            Preference preference = servicioPago.generarLinkPagoParaDonacion(publicacion.getTitulo(), monto, publicacion.getId());

            if (preference == null) {
                model.put("error", "Error al generar el link de pago.");
                return new ModelAndView("publicacion", model);
            }

            return new ModelAndView("redirect:" + preference.getInitPoint());

        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", "Error al procesar la donación.");
            return new ModelAndView("publicacion", model);
        }
    }

    @RequestMapping(value = "/donacion-exitosa", method = RequestMethod.GET)
    public ModelAndView donacionExitosa(@RequestParam("payment_id") String paymentId,
                                        @RequestParam("external_reference") Long idPublicacion,
                                        @RequestParam(value = "status", required = false) String status) {
        ModelMap model = new ModelMap();

        try {
            if ("approved".equalsIgnoreCase(status)) {
                PublicacionRecaudacion publicacion = (PublicacionRecaudacion) servicioPublicacion.obtenerTodasLasPublicaciones()
                        .stream()
                        .filter(p -> p.getId().equals(idPublicacion))
                        .findFirst()
                        .orElse(null);

                if (publicacion != null) {
                    Double monto = servicioPago.obtenerMontoDePago(paymentId);
                    publicacion.incrementarMontoRecaudado(monto);
                    servicioPublicacion.actualizar(publicacion);
                }
            }
            model.put("mensaje", "¡Gracias por tu donación!");
            return new ModelAndView("redirect:/feed", model);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("error", "Error al confirmar la donación.");
            return new ModelAndView("redirect:/feed", model);
        }
    }
}
