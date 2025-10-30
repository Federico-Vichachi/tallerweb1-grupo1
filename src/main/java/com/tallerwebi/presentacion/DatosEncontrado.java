package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Provincias;

public class DatosEncontrado extends DatosPublicacion {

    public DatosEncontrado() {
        super();
    }

    public DatosEncontrado(String titulo, String descripcionCorta, String descripcionDetallada, String imagen,
                           String raza, Integer tamanio, Provincias provincia, String localidad, String telefono, String email) {
        super(titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, provincia,localidad, telefono, email);
    }
}


