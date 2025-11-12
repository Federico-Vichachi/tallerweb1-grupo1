package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class RepositorioComentarioTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RepositorioComentario repositorioComentario;

    private Usuario usuario;
    private Publicacion publicacion;

    @BeforeEach
    public void init() {
        usuario = new Usuario();
        usuario.setEmail("asdsadsa@gmail.com");
        sessionFactory.getCurrentSession().save(usuario);

        publicacion = new PublicacionAdopcion();
        publicacion.setTitulo("Publicacion de prueba");
        sessionFactory.getCurrentSession().save(publicacion);
    }

    @Test
    @Transactional
    @Rollback
    public void queAlGuardarUnComentarioSePersistaCorrectamente() {
        Comentario comentario = tengoUnComentarioValido();

        cuandoGuardoElComentario(comentario);

        elComentarioSeGuardaEnLaBaseDeDatos(comentario);
    }

    private Comentario tengoUnComentarioValido() {
        Comentario comentario = new Comentario();
        comentario.setTexto("Comentario de prueba");
        comentario.setAutor(this.usuario);
        comentario.setPublicacion(this.publicacion);
        return comentario;
    }

    private void cuandoGuardoElComentario(Comentario comentario) {
        repositorioComentario.guardar(comentario);
    }

    private void elComentarioSeGuardaEnLaBaseDeDatos(Comentario comentarioOriginal) {
        Comentario comentarioGuardado = sessionFactory.getCurrentSession().get(Comentario.class, comentarioOriginal.getId());
        assertThat(comentarioGuardado, notNullValue());
        assertThat(comentarioGuardado.getId(), equalTo(comentarioOriginal.getId()));
        assertThat(comentarioGuardado.getTexto(), equalTo("Comentario de prueba"));
        assertThat(comentarioGuardado.getAutor().getId(), equalTo(usuario.getId()));
    }
}