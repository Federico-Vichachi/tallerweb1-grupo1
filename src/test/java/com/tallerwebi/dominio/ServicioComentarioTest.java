package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ComentarioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ServicioComentarioTest {

    private ServicioComentario servicioComentario;
    private RepositorioComentario repositorioComentarioMock;

    private Usuario autor;
    private Publicacion publicacion;
    private String textoComentario;

    @BeforeEach
    public void init() {
        repositorioComentarioMock = mock(RepositorioComentario.class);
        servicioComentario = new ServicioComentarioImpl(repositorioComentarioMock);
    }

    @Test
    public void queAlGuardarUnComentarioValidoElComentarioSeGuardeCorrectamente() {
        tengoUnComentarioValidoEnUnaPublicacionNormal();
        guardoElComentario();
        entoncesElComentarioSeGuarda();
    }

    @Test
    public void queAlIntentarGuardarUnComentarioVacioSeLanceUnaExcepcion() {
        tengoUnComentarioConTextoVacio();

        ComentarioException excepcion = assertThrows(ComentarioException.class, this::guardoElComentario);

        entoncesLaExcepcionTieneElMensajeCorrecto(excepcion, "El comentario no puede estar vacio.");
        entoncesNoSeGuardaElComentario();
    }

    @Test
    public void queAlIntentarGuardarUnComentarioSinUsuarioLogueadoSeLanceUnaExcepcion() {
        tengoUnComentarioSinAutor();

        ComentarioException excepcion = assertThrows(ComentarioException.class, this::guardoElComentario);

        entoncesLaExcepcionTieneElMensajeCorrecto(excepcion, "Debe iniciar sesion para comentar.");
        entoncesNoSeGuardaElComentario();
    }

    @Test
    public void queSiUnUsuarioNoVeterinarioComentaEnPublicacionDeSaludSeLanceUnaExcepcion() {
        tengoUnComentarioDeUnUsuarioComunEnUnaPublicacionDeSalud();

        ComentarioException excepcion = assertThrows(ComentarioException.class, this::guardoElComentario);

        entoncesLaExcepcionTieneElMensajeCorrecto(excepcion, "Solo los veterinarios pueden comentar en publicaciones de salud.");
        entoncesNoSeGuardaElComentario();
    }

    @Test
    public void queSiUnUsuarioVeterinarioComentaEnPublicacionDeSaludElComentarioSeGuardeCorrectamente() {
        tengoUnComentarioDeUnVeterinarioEnUnaPublicacionDeSalud();
        guardoElComentario();
        entoncesElComentarioSeGuarda();
    }

    private void tengoUnComentarioValidoEnUnaPublicacionNormal() {
        this.autor = new Usuario();
        this.autor.setRol(Roles.USUARIO_COMUN);
        this.publicacion = new PublicacionAdopcion();
        this.textoComentario = "Este es un comentario valido.";
    }

    private void tengoUnComentarioConTextoVacio() {
        this.autor = new Usuario();
        this.publicacion = new PublicacionAdopcion();
        this.textoComentario = "    ";
    }

    private void tengoUnComentarioSinAutor() {
        this.autor = null;
        this.publicacion = new PublicacionAdopcion();
        this.textoComentario = "Este es un comentario valido.";
    }

    private void tengoUnComentarioDeUnUsuarioComunEnUnaPublicacionDeSalud() {
        this.autor = new Usuario();
        this.autor.setRol(Roles.USUARIO_COMUN);
        this.publicacion = new PublicacionSalud();
        this.textoComentario = "Esta gordito tiene que caminar mas";
    }

    private void tengoUnComentarioDeUnVeterinarioEnUnaPublicacionDeSalud() {
        this.autor = new Usuario();
        this.autor.setRol(Roles.VETERINARIO);
        this.publicacion = new PublicacionSalud();
        this.textoComentario = "Le duele la panza";
    }

    private void guardoElComentario() {
        servicioComentario.guardarComentario(this.textoComentario, this.autor, this.publicacion);
    }

    private void entoncesElComentarioSeGuarda() {
        verify(repositorioComentarioMock, times(1)).guardar(any(Comentario.class));
    }

    private void entoncesNoSeGuardaElComentario() {
        verify(repositorioComentarioMock, never()).guardar(any(Comentario.class));
    }

    private void entoncesLaExcepcionTieneElMensajeCorrecto(Exception e, String mensajeEsperado) {
        assertEquals(mensajeEsperado, e.getMessage());
    }
}