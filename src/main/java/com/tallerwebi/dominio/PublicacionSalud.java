package com.tallerwebi.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PublicacionSalud extends Publicacion {
    private Integer edad;
    private String sintomasPrincipales;
    private String diagnostico;
    private String nivelUrgencia;
}
