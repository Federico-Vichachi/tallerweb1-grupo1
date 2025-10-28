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

    private LocalDateTime fechaPublicacion;
    private String titulo;
    private String descripcionCorta;
    private String descripcionDetallada;
    private String imagen;
    private String raza;
    private Integer tamanio;
    private String ubicacion;
    private String telefono;
    private String email;

    public Publicacion() {}

    public Publicacion(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, String raza, Integer tamanio, String ubicacion, String telefono, String email) {
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


    public abstract int puntosPorCreacion();

}