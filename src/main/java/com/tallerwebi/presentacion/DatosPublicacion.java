package com.tallerwebi.presentacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DatosPublicacion {
    private String titulo;
    private String descripcionCorta;
    private String descripcionDetallada;
    private String imagen;
    private String raza;
    private Integer tamanio;
    private String ubicacion;
    private Integer telefono;
    private String email;

    public DatosPublicacion() {
    }

    public DatosPublicacion(String titulo, String descripcionCorta, String descripcionDetallada, String imagen,
                            String raza, Integer tamanio, String ubicacion, Integer telefono, String email) {
        this.titulo = titulo;
        this.descripcionCorta = descripcionCorta;
        this.descripcionDetallada = descripcionDetallada;
        this.imagen = imagen;
        this.raza = raza;
        this.tamanio = tamanio;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.email = email;
    }
}
