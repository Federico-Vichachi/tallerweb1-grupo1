package com.tallerwebi.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String contrasenia;
    private String nombreDeUsuario;
    private String urlFotoDePerfil;
    @Enumerated(EnumType.STRING)
    private Roles rol;
    @OneToMany
    private List<Domicilio> domicilios;
}
