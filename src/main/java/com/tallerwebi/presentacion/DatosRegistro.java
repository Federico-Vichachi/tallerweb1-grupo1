package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Provincias;
import com.tallerwebi.dominio.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosRegistro {
    private String nombre;
    private String apellido;
    private String nombreDeUsuario;
    private String email;
    private String telefono;
    private String contrasenia;
    private String repeticionDeContrasenia;
    private Roles rol;
    private String calle;
    private String numero;
    private String ciudad;
    private Provincias provincia;
    private String codigoPostal;
    private String departamento;
    private String piso;

    public DatosRegistro() {}
}
