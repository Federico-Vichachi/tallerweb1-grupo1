package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosRecaudacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PublicacionRecaudacion extends Publicacion2 {

    private Integer edad;
    private Double meta;
    private String cbu;
    private String metodoPreferido;

    public  PublicacionRecaudacion() {}

    public PublicacionRecaudacion(DatosRecaudacion datosRecaudacion) {
        super(datosRecaudacion.getTitulo(), datosRecaudacion.getDescripcionCorta(), datosRecaudacion.getDescripcionDetallada(), datosRecaudacion.getImagen(), datosRecaudacion.getRaza(), datosRecaudacion.getTamanio(), datosRecaudacion.getUbicacion(), datosRecaudacion.getTelefono(), datosRecaudacion.getEmail());
        this.edad = datosRecaudacion.getEdad();
        this.meta = datosRecaudacion.getMeta();
        this.cbu = datosRecaudacion.getCbu();
        this.metodoPreferido = datosRecaudacion.getMetodoPreferido();
    }
}
