package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosRecaudacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PublicacionRecaudacion extends Publicacion {

    private Integer edad;
    private Double meta;
    private String cbu;
    private String metodoPreferido;

    public  PublicacionRecaudacion() {}

    public PublicacionRecaudacion(DatosRecaudacion datosRecaudacion) {
        super(datosRecaudacion.getTitulo(), datosRecaudacion.getDescripcionCorta(), datosRecaudacion.getDescripcionDetallada(), datosRecaudacion.getImagen(), datosRecaudacion.getRaza(), datosRecaudacion.getTamanio(), datosRecaudacion.getTelefono(),datosRecaudacion.getEmail(), datosRecaudacion.getProvincia(), datosRecaudacion.getLocalidad());
        this.edad = datosRecaudacion.getEdad();
        this.meta = datosRecaudacion.getMeta();
        this.cbu = datosRecaudacion.getCbu();
        this.metodoPreferido = datosRecaudacion.getMetodoPreferido();
    }

    @Override
    public int puntosPorCreacion() {
        return 20;
    }
}
