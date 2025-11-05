package com.tallerwebi.presentacion;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DatosProducto {

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int precioEnPuntos;
    private int stock;
    private String imagen;

    public DatosProducto() {}

    public DatosProducto(String nombre, String descripcion, BigDecimal precio, int precioEnPuntos, int stock, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.precioEnPuntos = precioEnPuntos;
        this.stock = stock;
        this.imagen = imagen;
    }

}
