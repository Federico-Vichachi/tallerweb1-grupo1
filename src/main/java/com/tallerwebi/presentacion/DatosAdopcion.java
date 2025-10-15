package com.tallerwebi.presentacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosAdopcion extends DatosPublicacion {
    private Integer edad;

    public DatosAdopcion() {
        super();
    }

    public DatosAdopcion(String titulo, String descripcionCorta, String descripcionDetallada, String imagen,
                         String raza, Integer tamanio, String ubicacion, Integer telefono, String email,
                         Integer edad) {
        super(titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, ubicacion, telefono, email);
        this.edad = edad;
    }

}
