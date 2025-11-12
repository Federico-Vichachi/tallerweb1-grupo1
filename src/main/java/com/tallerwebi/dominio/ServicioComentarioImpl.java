package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ComentarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioComentarioImpl implements ServicioComentario {

    private final RepositorioComentario repositorioComentario;

    @Autowired
    public ServicioComentarioImpl(RepositorioComentario repositorioComentario) {
        this.repositorioComentario = repositorioComentario;
    }

    @Override
    public void guardarComentario(String texto, Usuario autor, Publicacion publicacion) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new ComentarioException("El comentario no puede estar vacio.");
        }

        if (autor == null) {
            throw new ComentarioException("Debe iniciar sesion para comentar.");
        }


        if (publicacion instanceof PublicacionSalud) {
            if (autor.getRol() != Roles.VETERINARIO) {
                throw new ComentarioException("Solo los veterinarios pueden comentar en publicaciones de salud.");
            }
        }

        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setTexto(texto);
        nuevoComentario.setAutor(autor);
        nuevoComentario.setPublicacion(publicacion);

        repositorioComentario.guardar(nuevoComentario);
    }
}