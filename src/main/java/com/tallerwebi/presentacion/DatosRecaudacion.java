package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Provincias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosRecaudacion extends DatosPublicacion {
    private Integer edad;
    private Double meta;
    private String cbu;
    private String metodoPreferido;

    public DatosRecaudacion() {
        super();
    }

    public DatosRecaudacion(String titulo, String descripcionCorta, String descripcionDetallada, String imagen,
                            String raza, Integer tamanio, Provincias provincia, String localidad , String telefono, String email,
                            Integer edad, Double meta, String cbu, String metodoPreferido) {
        super(titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, provincia,localidad, telefono, email);
        this.edad = edad;
        this.meta = meta;
        this.cbu = cbu;
        this.metodoPreferido = metodoPreferido;
    }
}