package com.tallerwebi.dominio;

public interface ServicioComentario {
    void guardarComentario(String texto, Usuario autor, Publicacion publicacion);
}