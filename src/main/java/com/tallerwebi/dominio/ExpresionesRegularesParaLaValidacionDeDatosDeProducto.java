package com.tallerwebi.dominio;

public class ExpresionesRegularesParaLaValidacionDeDatosDeProducto {
    public static final String FORMATO_NOMBRE = "^[a-zA-ZÀ-ÿ0-9 .,!?]{3,255}$";
    public static final String FORMATO_DESCRIPCION = "^[a-zA-ZÀ-ÿ0-9 .,!?]{1,100}$";
    public static final String FORMATO_IMAGEN = "(?i)^.+\\.(jpg|jpeg|png|webp)$";
}
