package com.tallerwebi.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String contrasenia;
    private String nombreDeUsuario;
    private String urlFotoDePerfil;
    @Enumerated(EnumType.STRING)
    private Roles rol;
    @OneToOne(cascade = CascadeType.ALL)
    private Domicilio domicilio;
}
