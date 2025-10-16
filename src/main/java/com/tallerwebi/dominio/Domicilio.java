package com.tallerwebi.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private String numero;
    private String ciudad;
    @Enumerated(EnumType.STRING)
    private Provincias provincia;
    private String codigoPostal;
    private String departamento;
    private String piso;

    public Domicilio(String calle, String numero, String ciudad, Provincias provincia, String codigoPostal, String departamento, String piso) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.departamento = departamento;
        this.piso = piso;
    }

    public Domicilio() {
    }
}
