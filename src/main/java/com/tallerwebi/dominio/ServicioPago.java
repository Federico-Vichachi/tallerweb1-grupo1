package com.tallerwebi.dominio;

import com.mercadopago.resources.preference.Preference;

public interface ServicioPago {
    Preference generarLinkPago(Producto producto);

    Preference generarLinkPagoParaDonacion(String titulo, Double monto, Long idPublicacion);

    Double obtenerMontoDePago(String idPago);
}
