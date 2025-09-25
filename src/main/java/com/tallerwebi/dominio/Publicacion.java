package com.tallerwebi.dominio;

import java.util.List;

public class Publicacion {

    private String nombreUsuario;
    private String fecha;
    private String tipo;
    private String urlImagen;
    private String nombreMascota;
    private String edad;
    private String ubicacion;
    private String raza;
    private String descripcion;
    private List<String> tags;

    public Publicacion(String nombreUsuario, String fecha, String tipo, String urlImagen, String nombreMascota, String edad, String ubicacion, String raza, String descripcion, List<String> tags) {
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
        this.tipo = tipo;
        this.urlImagen = urlImagen;
        this.nombreMascota = nombreMascota;
        this.edad = edad;
        this.ubicacion = ubicacion;
        this.raza = raza;
        this.descripcion = descripcion;
        this.tags = tags;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getUrlImagen() { return urlImagen; }
    public void setUrlImagen(String urlImagen) { this.urlImagen = urlImagen; }
    public String getNombreMascota() { return nombreMascota; }
    public void setNombreMascota(String nombreMascota) { this.nombreMascota = nombreMascota; }
    public String getEdad() { return edad; }
    public void setEdad(String edad) { this.edad = edad; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}