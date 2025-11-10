package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosSalud;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PublicacionSalud extends Publicacion {
    private Integer edad;
    private String sintomasPrincipales;
    private String diagnostico;
    private String nivelUrgencia;

    public  PublicacionSalud() {}

    public PublicacionSalud(DatosSalud datosSalud) {
        super(datosSalud.getTitulo(), datosSalud.getDescripcionCorta(), datosSalud.getDescripcionDetallada(), datosSalud.getImagen(), datosSalud.getRaza(), datosSalud.getTamanio(), datosSalud.getTelefono(),datosSalud.getEmail(), datosSalud.getProvincia(), datosSalud.getLocalidad());
        this.edad = datosSalud.getEdad();
        this.sintomasPrincipales = datosSalud.getSintomasPrincipales();
        this.diagnostico = datosSalud.getDiagnostico();
        this.nivelUrgencia = datosSalud.getNivelUrgencia();
    }

}
