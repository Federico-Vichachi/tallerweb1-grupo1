package com.tallerwebi.presentacion;

public class DatosSalud {
    private String tituloSalud;
    private String descripcionCortaSalud;
    private String descripcionDetalladaSalud;
    private String imagenSalud;
    private Integer edadSalud;
    private String razaSalud;
    private Integer tamanioSalud;
    private String ubicacionSalud;
    private String sintomasPrincipalesSalud;
    private String diagnosticoSalud;
    private String nivelUrgenciaSalud;

    public DatosSalud(){

    }

    public DatosSalud(String tituloSalud, String descripcionCortaSalud, String descripcionDetalladaSalud, String imagenSalud, Integer edadSalud , String razaSalud, Integer tamanioSalud, String ubicacionSalud, String sintomasPrincipalesSalud, String diagnosticoSalud, String nivelUrgenciaSalud) {
        this.tituloSalud = tituloSalud;
        this.descripcionCortaSalud = descripcionCortaSalud;
        this.descripcionDetalladaSalud = descripcionDetalladaSalud;
        this.imagenSalud = imagenSalud;
        this.edadSalud = edadSalud;
        this.razaSalud = razaSalud;
        this.tamanioSalud = tamanioSalud;
        this.ubicacionSalud = ubicacionSalud;
        this.sintomasPrincipalesSalud = sintomasPrincipalesSalud;
        this.diagnosticoSalud = diagnosticoSalud;
        this.nivelUrgenciaSalud = nivelUrgenciaSalud;
    }

    public String getTituloSalud() {return tituloSalud;}
    public void setTituloSalud(String tituloSalud) {this.tituloSalud = tituloSalud;}

    public String getDescripcionCortaSalud() {return descripcionCortaSalud;}
    public void setDescripcionCortaSalud(String descripcionCortaSalud) {this.descripcionCortaSalud = descripcionCortaSalud;}

    public String getDescripcionDetalladaSalud() {return descripcionDetalladaSalud;}
    public void setDescripcionDetalladaSalud(String descripcionDetalladaSalud) {this.descripcionDetalladaSalud = descripcionDetalladaSalud;}

    public String getImagenSalud() {return imagenSalud;}
    public void setImagenSalud(String imagenSalud) {this.imagenSalud = imagenSalud;}

    public Integer getEdadSalud() {return edadSalud;}
    public void setEdadSalud(Integer edadSalud) {this.edadSalud = edadSalud;}

    public Integer getTamanioSalud() {return tamanioSalud;}
    public void setTamanioSalud(Integer tamanioSalud) {this.tamanioSalud = tamanioSalud;}

    public String getRazaSalud() {return razaSalud;}
    public void setRazaSalud(String razaSalud) {this.razaSalud = razaSalud;}

    public String getUbicacionSalud() {return ubicacionSalud;}
    public void setUbicacionSalud(String ubicacionSalud) {this.ubicacionSalud = ubicacionSalud;}

    public String getSintomasPrincipalesSalud() {return sintomasPrincipalesSalud;}
    public void setSintomasPrincipalesSalud(String sintomasPrincipalesSalud) {this.sintomasPrincipalesSalud = sintomasPrincipalesSalud;}

    public String getDiagnosticoSalud() {return diagnosticoSalud;}
    public void setDiagnosticoSalud(String diagnosticoSalud) {this.diagnosticoSalud = diagnosticoSalud;}

    public String getNivelUrgenciaSalud() {return nivelUrgenciaSalud;}
    public void setNivelUrgenciaSalud(String nivelUrgenciaSalud) {this.nivelUrgenciaSalud = nivelUrgenciaSalud;}
}
