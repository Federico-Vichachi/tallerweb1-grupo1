package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Provincias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosPerdido extends DatosPublicacion {

    private String fechaDesaparicion;
    private String horaDesaparicion;
    private Boolean llevaCollar;
    private String recompensa;

    public DatosPerdido() {
        super();
    }

    public DatosPerdido(String titulo, String descripcionCorta, String descripcionDetallada, String imagen,
                        String raza, Integer tamanio, String localidad, Provincias provincia, String telefono, String email,
                        String fechaDesaparicion, String horaDesaparicion, Boolean llevaCollar, String recompensa) {
        super(titulo, descripcionCorta, descripcionDetallada, imagen, raza, tamanio, provincia,localidad, telefono, email);
        this.fechaDesaparicion = fechaDesaparicion;
        this.horaDesaparicion = horaDesaparicion;
        this.llevaCollar = llevaCollar;
        this.recompensa = recompensa;
    }
}