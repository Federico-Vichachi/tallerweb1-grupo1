package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.CampañaDonacionFinalizadaException;
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
    private Double montoActual;
    private Boolean activa;

    public  PublicacionRecaudacion() {}

    public PublicacionRecaudacion(DatosRecaudacion datosRecaudacion) {
        super(datosRecaudacion.getTitulo(), datosRecaudacion.getDescripcionCorta(), datosRecaudacion.getDescripcionDetallada(), datosRecaudacion.getImagen(), datosRecaudacion.getRaza(), datosRecaudacion.getTamanio(), datosRecaudacion.getTelefono(),datosRecaudacion.getEmail(), datosRecaudacion.getProvincia(), datosRecaudacion.getLocalidad());
        this.edad = datosRecaudacion.getEdad();
        this.meta = datosRecaudacion.getMeta();
        this.cbu = datosRecaudacion.getCbu();
        this.metodoPreferido = datosRecaudacion.getMetodoPreferido();
        this.montoActual = 0.0;
        this.activa = false;
    }

    @Override
    public int puntosPorCreacion() {
        return 20;
    }


    public void incrementarMontoRecaudado(Double montoDonado){
        if(this.activa == null){
            this.activa = true;
        }

        if (!this.activa){
            throw new CampañaDonacionFinalizadaException("La campaña finalizo.");
        }

        if(this.montoActual == null){
            this.montoActual = 0.0;
        }

        this.montoActual += montoDonado;

        if(this.montoActual >= meta){
            this.activa = false;
        }
    }

}
