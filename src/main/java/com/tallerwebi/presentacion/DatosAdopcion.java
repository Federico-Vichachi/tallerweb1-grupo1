package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Provincias;
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
                         String raza, Integer tamanio, Provincias provincia, String localidad, String telefono, String email,
                         Integer edad) {
        super(titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, provincia,localidad, telefono, email);
        this.edad = edad;
    }

}
