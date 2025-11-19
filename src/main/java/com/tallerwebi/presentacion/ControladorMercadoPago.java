package com.tallerwebi.presentacion;

import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.resources.payment.Payment;
import com.tallerwebi.dominio.Producto;
import com.tallerwebi.dominio.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class ControladorMercadoPago {
    @Autowired
    private ServicioProducto servicioProducto;


    @PostMapping
    public ResponseEntity<String> recibirWebhook(@RequestBody Map<String, Object> body) {

        try {
            String type = (String) body.get("type");
            Map data = (Map) body.get("data");
            String id = data.get("id").toString();

            if (!"payment".equals(type)) {
                return ResponseEntity.ok("Ignored");
            }

            PaymentClient client = new PaymentClient();
            Payment payment = client.get(Long.parseLong(id));

            String status = payment.getStatus();

            String externalRef = payment.getExternalReference();
            String[] partes = externalRef.split("-");

            Long idProducto = Long.parseLong(partes[0]);
            int cantidad = Integer.parseInt(partes[1]);

            if ("approved".equalsIgnoreCase(status)) {
                Producto prod = servicioProducto.buscarPorId(idProducto);
                servicioProducto.descontarStock(prod, cantidad);
                System.out.println("STOCK DESCONTADO por webhook");
            }

            return ResponseEntity.ok("OK");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("ERROR");
        }
    }
}
