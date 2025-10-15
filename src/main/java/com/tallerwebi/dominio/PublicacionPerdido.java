package com.tallerwebi.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PublicacionPerdido extends Publicacion2 {
    private String fechaDesaparicion;
    private String horaDesaparicion;
    private Boolean llevaCollar;
    private String recompensa;
}
