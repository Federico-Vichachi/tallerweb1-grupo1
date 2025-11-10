package com.tallerwebi.dominio;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    private Double latitud;
    private Double longitud;


    @Enumerated(EnumType.STRING)
    private Provincias provincia;
    private String localidad;
    private LocalDateTime fechaPublicacion;
    private String titulo;
    private String descripcionCorta;
    private String descripcionDetallada;
    private String imagen;
    private String raza;
    private Integer tamanio;
    private String telefono;
    private String email;

    public Publicacion() {}

    public Publicacion(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, String raza, Integer tamanio, String telefono, String email, Provincias provincia, String localidad) {
        this.titulo = titulo;
        this.descripcionCorta = descripcionCorta;
        this.descripcionDetallada = descripcionDetallada;
        this.imagen = imagen;
        this.raza = raza;
        this.tamanio = tamanio;
        this.telefono = telefono;
        this.email = email;
        this.provincia=provincia;
        this.localidad=localidad;
    }
}