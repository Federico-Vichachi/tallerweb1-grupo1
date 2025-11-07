package com.tallerwebi.dominio;

public class ExpresionesRegularesParaLaValidacionDeDatosDeUsuario {
    public static final String FORMATO_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String FORMATO_CONTRASENIA = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    public static final String FORMATO_NOMBRE_DE_USUARIO = "^[a-zA-Z0-9._]{3,30}$";
    public static final String FORMATO_NOMBRE_O_APELLIDO = "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]{2,50}$";
    public static final String FORMATO_TELEFONO = "^[+]?[0-9]{8,15}$";
    public static final String FORMATO_CALLE = "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s0-9.]{3,100}$";
    public static final String FORMATO_NUMERO_DE_DOMICILIO = "^[0-9]{1,6}$";
    public static final String FORMATO_CIUDAD = "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s0-9]{2,50}$";
    public static final String FORMATO_CODIGO_POSTAL = "^[0-9]{4,8}$";
    public static final String FORMATO_PISO = "^[0-9]{1,3}$";
    public static final String FORMATO_DEPARTAMENTO = "^[a-zA-Z0-9]{1,10}$";



}
