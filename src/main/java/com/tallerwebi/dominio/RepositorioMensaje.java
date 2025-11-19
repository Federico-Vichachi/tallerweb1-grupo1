package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioMensaje {
    void guardar(Mensaje mensaje);
    List<Mensaje> obtenerMensajesEntre(Usuario usuario1, Usuario usuario2);
    List<Usuario> obtenerUsuariosConConversacion(Usuario usuario);
}