package com.tallerwebi.presentacion;

public class DatosAdopcion {
    private String titulo;
    private String descripcionCorta;
    private String descripcionDetallada;
    private String imagen;
    private Integer edad;
    private Integer tamanio;
    private String raza;
    private String ubicacion;
    private Integer telefono;
    private String email;

    public DatosAdopcion() {

    }

    public DatosAdopcion(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, Integer edad, Integer tamanio, String raza, String ubicacion, Integer telefono, String email ) {
        this.titulo = titulo;
        this.descripcionCorta = descripcionCorta;
        this.descripcionDetallada = descripcionDetallada;
        this.imagen = imagen;
        this.edad = edad;
        this.tamanio = tamanio;
        this.raza = raza;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.email = email;
    }

    public String getTitulo() {return titulo;}
    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getDescripcionCorta() {return descripcionCorta;}
    public void setDescripcionCorta(String descripcionCorta) {this.descripcionCorta = descripcionCorta;}

    public String getDescripcionDetallada() {return descripcionDetallada;}
    public void setDescripcionDetallada(String descripcionDetallada) {this.descripcionDetallada = descripcionDetallada;}

    public String getImagen() {return imagen;}
    public void setImagen(String imagen) {this.imagen = imagen;}

    public Integer getEdad() {return edad;}
    public void setEdad(Integer edad) {this.edad = edad;}

    public Integer getTamanio() {return tamanio;}
    public void setTamanio(Integer tamanio) {this.tamanio = tamanio;}

    public String getRaza() {return raza;}
    public void setRaza(String raza) {this.raza = raza;}

    public String getUbicacion() {return ubicacion;}
    public void setUbicacion(String ubicacion) {this.ubicacion = ubicacion;}

    public Integer getTelefono() {return telefono;}
    public void setTelefono(Integer telefono) {this.telefono = telefono;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
}
