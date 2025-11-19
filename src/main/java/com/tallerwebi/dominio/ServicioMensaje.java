package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioMensaje {
    void guardarMensaje(String contenido, String remitenteUsername, String destinatarioUsername);
    List<Mensaje> iniciarYObtenerConversacion(String usuarioRemitente, String usuarioDestinatario);
    List<Usuario> obtenerConversaciones(String username);
}