package com.tallerwebi.dominio;

public interface ServicioPuntos {

    void sumarPuntos(Usuario usuario, Double monto);

    boolean gastarPuntos(Usuario usuario, Producto producto);

    int calcularPuntosPorDonacion(Double monto);
}
