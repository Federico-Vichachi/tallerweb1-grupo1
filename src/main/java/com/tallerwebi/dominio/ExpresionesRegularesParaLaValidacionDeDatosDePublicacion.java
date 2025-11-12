package com.tallerwebi.dominio;

public class ExpresionesRegularesParaLaValidacionDeDatosDePublicacion {
    public static final String FORMATO_TITULO = "^[a-zA-ZÀ-ÿ0-9 .,!?]{3,255}$";
    public static final String FORMATO_DESCRIPCION_CORTA = "^[a-zA-ZÀ-ÿ0-9 .,!?]{1,100}$";
    public static final String FORMATO_DESCRIPCION_DETALLADA = "^[a-zA-ZÀ-ÿ0-9 .,!?]{1,255}$";
    public static final String FORMATO_RAZA_UBICACION = "^[a-zA-ZÀ-ÿ\\s,]{2,50}$";
    public static final String FORMATO_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String FORMATO_TELEFONO = "^[+]?[0-9]{8,15}$";
    public static final String FORMATO_CBU = "^[0-9]{22}$";
    public static final String FORMATO_FECHA = "^\\d{4}-\\d{2}-\\d{2}$";
    public static final String FORMATO_HORA = "^\\d{2}:\\d{2}$";
    public static final String FORMATO_IMAGEN = "(?i)^.+\\.(jpg|jpeg|png|webp)$";


}

