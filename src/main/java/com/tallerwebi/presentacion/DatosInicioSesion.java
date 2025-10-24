package com.tallerwebi.presentacion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosInicioSesion {
    private String email;
    private String contrasenia;

    public DatosInicioSesion() {}
}
