package com.tallerwebi.presentacion;

public class DatosPerdido {
    private String tituloPerdido;
    private String descripcionCortaPerdido;
    private String descripcionDetalladaPerdido;
    private String imagenPerdido;
    private String razaPerdido;
    private Integer tamanioPerdido;
    private String ubicacionPerdido;
    private String fechaDesaparicion;
    private String horaDesaparicion;
    private Boolean llevaCollar;
    private String recompensa;


    public DatosPerdido(){

    }

    public DatosPerdido(String tituloPerdido, String descripcionCortaPerdido, String descripcionDetalladaPerdido, String imagenPerdido, String razaPerdido, Integer tamanioPerdido, String ubicacionPerdido, String fechaDesaparicion, String horaDesaparicion , Boolean llevaCollar, String recompensa) {
        this.tituloPerdido = tituloPerdido;
        this.descripcionCortaPerdido = descripcionCortaPerdido;
        this.descripcionDetalladaPerdido = descripcionDetalladaPerdido;
        this.imagenPerdido = imagenPerdido;
        this.razaPerdido = razaPerdido;
        this.tamanioPerdido = tamanioPerdido;
        this.ubicacionPerdido = ubicacionPerdido;
        this.fechaDesaparicion = fechaDesaparicion;
        this.horaDesaparicion = horaDesaparicion;
        this.llevaCollar = llevaCollar;
        this.recompensa = recompensa;
    }

    public String getTituloPerdido() {return tituloPerdido;}
    public void setTituloPerdido(String tituloPerdido) {this.tituloPerdido = tituloPerdido;}

    public String getDescripcionCortaPerdido() {return descripcionCortaPerdido;}
    public void setDescripcionCortaPerdido(String descripcionCortaPerdido) {this.descripcionCortaPerdido = descripcionCortaPerdido;}

    public String getDescripcionDetalladaPerdido() {return descripcionDetalladaPerdido;}
    public void setDescripcionDetalladaPerdido(String descripcionDetalladaPerdido) {this.descripcionDetalladaPerdido = descripcionDetalladaPerdido;}

    public String getImagenPerdido() {return imagenPerdido;}
    public void setImagenPerdido(String imagenPerdido) {this.imagenPerdido = imagenPerdido;}

    public Integer getTamanioPerdido() {return tamanioPerdido;}
    public void setTamanioPerdido(Integer tamanioPerdido) {this.tamanioPerdido = tamanioPerdido;}

    public String getRazaPerdido() {return razaPerdido;}
    public void setRazaPerdido(String razaPerdido) {this.razaPerdido = razaPerdido;}

    public String getUbicacionPerdido() {return ubicacionPerdido;}
    public void setUbicacionPerdido(String ubicacionPerdido) {this.ubicacionPerdido = ubicacionPerdido;}

    public String getFechaDesaparicion() {return fechaDesaparicion;}
    public void setFechaDesaparicion(String fechaDesaparicion) {this.fechaDesaparicion = fechaDesaparicion;}

    public String getHoraDesaparicion() {return horaDesaparicion;}

    public void setHoraDesaparicion(String horaDesaparicion) {this.horaDesaparicion = horaDesaparicion;}

    public Boolean getLlevaCollar() {return llevaCollar;}
    public void setLlevaCollar(Boolean llevaCollar) {this.llevaCollar = llevaCollar;}

    public String getRecompensa() {return recompensa;}
    public void setRecompensa(String recompensa) {this.recompensa = recompensa;}
}