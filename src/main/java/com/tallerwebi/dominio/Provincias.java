package com.tallerwebi.dominio;

public enum Provincias {
    TIERRA_DEL_FUEGO("Tierra del Fuego"),
    SANTA_CRUZ("Santa Cruz"),
    CHUBUT("Chubut"),
    RIO_NEGRO("Río Negro"),
    NEUQUEN("Neuquén"),
    LA_PAMPA("La Pampa"),
    BUENOS_AIRES("Buenos Aires"),
    CAPITAL_FEDERAL("Capital Federal"),
    MENDOZA("Mendoza"),
    SAN_LUIS("San Luis"),
    CORDOBA("Córdoba"),
    SANTA_FE("Santa Fe"),
    ENTRE_RIOS("Entre Ríos"),
    SAN_JUAN("San Juan"),
    LA_RIOJA("La Rioja"),
    CATAMARCA("Catamarca"),
    SANTIAGO_DEL_ESTERO("Santiago del Estero"),
    CORRIENTES("Corrientes"),
    CHACO("Chaco"),
    MISIONES("Misiones"),
    TUCUMAN("Tucumán"),
    FORMOSA("Formosa"),
    SALTA("Salta"),
    JUJUY("Jujuy");

    private final String nombreFormateado;

    Provincias(String nombreFormateado) {
        this.nombreFormateado = nombreFormateado;
    }

    public String getNombreFormateado() {
        return nombreFormateado;
    }
}
