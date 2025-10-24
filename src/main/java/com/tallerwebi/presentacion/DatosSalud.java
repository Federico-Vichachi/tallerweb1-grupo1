package com.tallerwebi.presentacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosSalud extends DatosPublicacion {
    private Integer edad;
    private String sintomasPrincipales;
    private String diagnostico;
    private String nivelUrgencia;

    public DatosSalud() {
        super();
    }

    public DatosSalud(String titulo, String descripcionCorta, String descripcionDetallada, String imagen,
                      String raza, Integer tamanio, String ubicacion, String telefono, String email,
                      Integer edad, String sintomasPrincipales, String diagnostico, String nivelUrgencia) {
        super(titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, ubicacion, telefono, email);
        this.edad = edad;
        this.sintomasPrincipales = sintomasPrincipales;
        this.diagnostico = diagnostico;
        this.nivelUrgencia = nivelUrgencia;
    }
}
