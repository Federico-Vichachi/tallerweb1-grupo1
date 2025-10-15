package com.tallerwebi.dominio;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    private LocalDateTime fechaPublicacion;

    @Enumerated(EnumType.STRING)
    private TipoPublicacion tipo;

    private String urlImagen;
    private String nombreMascota;
    private String edad;
    private String ubicacion;
    private String raza;
    private String descripcion;
    private String tags;

    public Publicacion() {}
}