package com.tallerwebi.presentacion;

public class DatosRecaudacion {
    private String tituloRecaudacion;
    private String descripcionCortaRecaudacion;
    private String descripcionDetalladaRecaudacion;
    private String imagenRecaudacion;
    private Integer edadRecaudacion;
    private String razaRecaudacion;
    private Double metaRecaudacion;
    private String cbuRecaudacion;
    private String metodoPreferidoRecaudacion;
    private Integer telefonoRecaudacion;
    private String emailRecaudacion;

    public DatosRecaudacion() {

    }

    public DatosRecaudacion(String tituloRecaudacion, String descripcionCortaRecaudacion, String descripcionDetalladaRecaudacion, String imagenRecaudacion, Integer edadRecaudacion, String razaRecaudacion, Double metaRecaudacion, String cbuRecaudacion, String metodoPreferidoRecaudacion , Integer telefonoRecaudacion, String emailRecaudacion ) {
        this.tituloRecaudacion = tituloRecaudacion;
        this.descripcionCortaRecaudacion = descripcionCortaRecaudacion;
        this.descripcionDetalladaRecaudacion = descripcionDetalladaRecaudacion;
        this.imagenRecaudacion = imagenRecaudacion;
        this.edadRecaudacion = edadRecaudacion;
        this.razaRecaudacion = razaRecaudacion;
        this.metaRecaudacion = metaRecaudacion;
        this.cbuRecaudacion = cbuRecaudacion;
        this.metodoPreferidoRecaudacion = metodoPreferidoRecaudacion;
        this.telefonoRecaudacion = telefonoRecaudacion;
        this.emailRecaudacion = emailRecaudacion;
    }

    public String getTituloRecaudacion() {return tituloRecaudacion;}
    public void setTituloRecaudacion(String tituloRecaudacion) {this.tituloRecaudacion = tituloRecaudacion;}

    public String getDescripcionCortaRecaudacion() {return descripcionCortaRecaudacion;}
    public void setDescripcionCortaRecaudacion(String descripcionCortaRecaudacion) {this.descripcionCortaRecaudacion = descripcionCortaRecaudacion;}

    public String getDescripcionDetalladaRecaudacion() {return descripcionDetalladaRecaudacion;}
    public void setDescripcionDetalladaRecaudacion(String descripcionDetalladaRecaudacion) {this.descripcionDetalladaRecaudacion = descripcionDetalladaRecaudacion;}

    public String getImagenRecaudacion() {return imagenRecaudacion;}
    public void setImagenRecaudacion(String imagenRecaudacion) {this.imagenRecaudacion = imagenRecaudacion;}

    public Integer getEdadRecaudacion() {return edadRecaudacion;}
    public void setEdadRecaudacion(Integer edadRecaudacion) {this.edadRecaudacion = edadRecaudacion;}

    public String getRazaRecaudacion() {return razaRecaudacion;}
    public void setRazaRecaudacion(String razaRecaudacion) {this.razaRecaudacion = razaRecaudacion;}

    public Double getMetaRecaudacion() {return metaRecaudacion;}
    public void setMetaRecaudacion(Double metaRecaudacion) {this.metaRecaudacion = metaRecaudacion;}

    public String getCbuRecaudacion() {return cbuRecaudacion;}
    public void setCbuRecaudacion(String cbuRecaudacion) {this.cbuRecaudacion = cbuRecaudacion;}

    public String getMetodoPreferidoRecaudacion(){return  metodoPreferidoRecaudacion;}
    public void setMetodoPreferidoRecaudacion(String metodoPreferidoRecaudacion){this.metodoPreferidoRecaudacion = metodoPreferidoRecaudacion;}

    public Integer getTelefonoRecaudacion() {return telefonoRecaudacion;}
    public void setTelefonoRecaudacion(Integer telefonoRecaudacion) {this.telefonoRecaudacion = telefonoRecaudacion;}

    public String getEmailRecaudacion() {return emailRecaudacion;}
    public void setEmailRecaudacion(String emailRecaudacion) {this.emailRecaudacion = emailRecaudacion;}
}