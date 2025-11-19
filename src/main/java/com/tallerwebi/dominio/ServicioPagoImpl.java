package com.tallerwebi.dominio;


import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;

@Service
@Transactional
public class ServicioPagoImpl implements ServicioPago {

    static {
        // Inicialización del SDK de Mercado Pago al cargar la clase
        MercadoPagoConfig.setAccessToken("APP_USR-6362078788221946-110116-e7279ee6500edc790ec5217e8dfacc7d-2959447709");
    }

    @Override
    public Preference generarLinkPago(Producto producto, int cantidad) {
        try {
            if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El producto debe tener un precio mayor a 0");
            }

            BigDecimal precioNormalizado = producto.getPrecio().setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println("Precio normalizado a enviar: " + precioNormalizado);

            PreferenceItemRequest item = PreferenceItemRequest.builder()
                    .title(producto.getNombre())
                    .description(producto.getDescripcion())
                    .quantity(cantidad)
                    .currencyId("ARS")
                    .unitPrice(precioNormalizado)
                    .build();

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("https://haunched-unbraceleted-shaunta.ngrok-free.dev/spring/pago-exitoso")
                    .failure("https://haunched-unbraceleted-shaunta.ngrok-free.dev/spring/pago-fallido")
                    .pending("https://haunched-unbraceleted-shaunta.ngrok-free.dev/spring/pago-pendiente")
                    .build();


            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(Collections.singletonList(item))
                    .backUrls(backUrls)
                    .externalReference(producto.getId() + "-" + cantidad)
                    .autoReturn("approved")
                    .build();

            PreferenceClient client = new PreferenceClient();
            return client.create(preferenceRequest);

        }  catch (MPApiException e) {
        System.err.println(" Error de MP API: " + e.getApiResponse().getContent());
        e.printStackTrace();
        return null;

    } catch (MPException e) {
        System.err.println(" Error general de MercadoPago: " + e.getMessage());
        e.printStackTrace();
        return null;

    } catch (Exception e) {
        System.err.println(" Error inesperado: " + e.getMessage());
        e.printStackTrace();
        return null;
    }

    }

    @Override
    public Preference generarLinkPagoParaDonacion(String titulo, Double monto, Long idPublicacion) {
        try {
            if (monto == null || monto <= 0) {
                throw new IllegalArgumentException("El monto de la donación debe ser mayor a 0");
            }

            BigDecimal price = BigDecimal.valueOf(monto);

            PreferenceItemRequest item = PreferenceItemRequest.builder()
                    .title("Donación para " + titulo)
                    .description("Gracias por apoyar esta campaña")
                    .quantity(1)
                    .currencyId("ARS")
                    .unitPrice(price)
                    .build();

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("https://haunched-unbraceleted-shaunta.ngrok-free.dev/spring/donacion-exitosa")
                    .failure("https://haunched-unbraceleted-shaunta.ngrok-free.dev/spring/donacion-fallida")
                    .pending("https://haunched-unbraceleted-shaunta.ngrok-free.dev/spring/donacion-pendiente")
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(Collections.singletonList(item))
                    .externalReference(idPublicacion.toString())
                    .backUrls(backUrls)
                    .build();

            PreferenceClient client = new PreferenceClient();
            return client.create(preferenceRequest);

        } catch (MPApiException e) {
            System.err.println("Error de MP API: " + e.getApiResponse().getContent());
            e.printStackTrace();
            return null;

        } catch (MPException e) {
            System.err.println("Error general de MercadoPago: " + e.getMessage());
            e.printStackTrace();
            return null;

        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Double obtenerMontoDePago(String payment_id) {
        try {
            PaymentClient client = new PaymentClient();
            Payment payment = client.get(Long.parseLong(payment_id));
            return payment.getTransactionAmount().doubleValue();
        }catch (Exception e){
            e.printStackTrace();
            return 0.0;
        }
    }
}