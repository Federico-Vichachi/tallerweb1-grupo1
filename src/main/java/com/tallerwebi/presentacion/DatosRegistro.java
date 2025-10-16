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

    public DatosRegistro(String nombre, String apellido, String nombreDeUsuario, String email, String telefono, String contrasenia, String repeticionDeContrasenia,
                         Roles rol, String calle, String numero, String ciudad, Provincias provincia, String codigoPostal, String departamento, String piso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreDeUsuario = nombreDeUsuario;
        this.email = email;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.repeticionDeContrasenia = repeticionDeContrasenia;
        this.rol = rol;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.departamento = departamento;
        this.piso = piso;
    }

    public DatosRegistro() {}
}
