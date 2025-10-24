package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosAdopcion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Publicacion2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcionCorta;
    private String descripcionDetallada;
    private String imagen;
    private String raza;
    private Integer tamanio;
    private String ubicacion;
    private Integer telefono;
    private String email;

    public Publicacion2() {}

    public Publicacion2(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, String raza, Integer tamanio, String ubicacion, Integer telefono, String email) {
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
