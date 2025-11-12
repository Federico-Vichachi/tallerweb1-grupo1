package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Provincias;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.File;

@Getter
@Setter
public class DatosEdicionPerfil {
    private Long id;
    private String nombre;
    private String apellido;
    private String nombreDeUsuario;
    private String email;
    private String telefono;
    private String calle;
    private String numero;
    private String piso;
    private String departamento;
    private String ciudad;
    private Provincias provincia;
    private String codigoPostal;
    private String urlFotoDePerfil; // nuevo campo para guardar la ruta en la BD

    public DatosEdicionPerfil() {
    }

    public DatosEdicionPerfil(String nombre, String apellido, String nombreDeUsuario, String email,
                              String telefono, String calle, String numero, String piso,
                              String departamento, String ciudad, Provincias provincia, String codigoPostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreDeUsuario = nombreDeUsuario;
        this.email = email;
        this.telefono = telefono;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
    }

}
