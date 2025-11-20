package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Provincias;
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
    private Provincias provincia;
    private String localidad;
    private String telefono;
    private String email;

    private Double latitud;
    private Double longitud;

    public DatosPublicacion() {
    }

    public DatosPublicacion(String titulo, String descripcionCorta, String descripcionDetallada, String imagen,
                            String raza, Integer tamanio, Provincias provincia, String localidad, String telefono, String email) {
        this.titulo = titulo;
        this.descripcionCorta = descripcionCorta;
        this.descripcionDetallada = descripcionDetallada;
        this.imagen = imagen;
        this.raza = raza;
        this.tamanio = tamanio;
        this.provincia = provincia;
        this.localidad=localidad;
        this.telefono = telefono;
        this.email = email;
    }
}