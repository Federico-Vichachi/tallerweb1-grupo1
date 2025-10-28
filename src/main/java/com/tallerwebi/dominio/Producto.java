package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosProducto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private int precioEnCentavos;
    private int precioEnPuntos;
    private int stock;
    private String imagen;

    public Producto() {}

    public Producto(DatosProducto datosProducto) {
        this.nombre = datosProducto.getNombre();
        this.descripcion = datosProducto.getDescripcion();
        this.precioEnCentavos = datosProducto.getPrecioEnCentavos();
        this.precioEnPuntos = datosProducto.getPrecioEnPuntos();
        this.stock = datosProducto.getStock();
        this.imagen = datosProducto.getImagen();
    }
}
