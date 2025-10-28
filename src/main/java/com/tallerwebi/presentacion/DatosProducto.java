package com.tallerwebi.presentacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosProducto {

    private String nombre;
    private String descripcion;
    private int precioEnCentavos;
    private int precioEnPuntos;
    private int stock;
    private String imagen;

    public DatosProducto() {}

    public DatosProducto(String nombre, String descripcion, int precioEnCentavos, int precioEnPuntos, int stock, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioEnCentavos = precioEnCentavos;
        this.precioEnPuntos = precioEnPuntos;
        this.stock = stock;
        this.imagen = imagen;
    }

}
