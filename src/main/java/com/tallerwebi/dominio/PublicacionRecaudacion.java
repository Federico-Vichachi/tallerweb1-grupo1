package com.tallerwebi.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PublicacionRecaudacion extends Publicacion {

    private Integer edad;
    private Double meta;
    private String cbu;
    private String metodoPreferido;
}
