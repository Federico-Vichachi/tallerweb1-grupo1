package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Provincias;
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
                      String raza, Integer tamanio, Provincias provincia, String localidad, String telefono, String email,
                      Integer edad, String sintomasPrincipales, String diagnostico, String nivelUrgencia) {
        super(titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, provincia,localidad, telefono, email);
        this.edad = edad;
        this.sintomasPrincipales = sintomasPrincipales;
        this.diagnostico = diagnostico;
        this.nivelUrgencia = nivelUrgencia;
    }
}
