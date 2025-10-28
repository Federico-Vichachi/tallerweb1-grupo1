package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosPerdido;
import com.tallerwebi.presentacion.DatosRecaudacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PublicacionPerdido extends Publicacion {
    private String fechaDesaparicion;
    private String horaDesaparicion;
    private Boolean llevaCollar;
    private String recompensa;

    public  PublicacionPerdido() {}

    public  PublicacionPerdido(DatosPerdido datosPerdido) {
        super(datosPerdido.getTitulo(), datosPerdido.getDescripcionCorta(), datosPerdido.getDescripcionDetallada(), datosPerdido.getImagen(), datosPerdido.getRaza(), datosPerdido.getTamanio(), datosPerdido.getUbicacion(), datosPerdido.getTelefono(), datosPerdido.getEmail());
        this.fechaDesaparicion = datosPerdido.getFechaDesaparicion();
        this.horaDesaparicion = datosPerdido.getHoraDesaparicion();
        this.llevaCollar = datosPerdido.getLlevaCollar();
        this.recompensa = datosPerdido.getRecompensa();
    }

    @Override
    public int puntosPorCreacion() {
        return 10;
    }

}
