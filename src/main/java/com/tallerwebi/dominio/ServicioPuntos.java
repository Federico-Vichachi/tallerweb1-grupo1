package com.tallerwebi.dominio;

public interface ServicioPuntos {

    void sumarPuntos(Usuario usuario, Publicacion publicacion);

    boolean gastarPuntos(Usuario usuario, Producto producto);
}
