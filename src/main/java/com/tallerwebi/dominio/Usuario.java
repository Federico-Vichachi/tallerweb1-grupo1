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
    private int puntos;

    public Usuario(String nombre, String apellido, String email, String telefono, String contrasenia, String nombreDeUsuario, Roles rol, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.nombreDeUsuario = nombreDeUsuario;
        this.rol = rol;
        this.domicilio = domicilio;
        this.urlFotoDePerfil = "/images/user.jpg";
        this.puntos = 0;
    }

    public Usuario() {
        this.urlFotoDePerfil = "/images/user.jpg";
    }

    public void sumarPuntos(int cantidad){
        this.puntos += cantidad;
    }



}
