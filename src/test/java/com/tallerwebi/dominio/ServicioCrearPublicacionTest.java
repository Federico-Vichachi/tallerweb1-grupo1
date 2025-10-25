package com.tallerwebi.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;

public class ServicioCrearPublicacionTest {

    private RepositorioPublicacion repositorioMock;
    private ServicioPublicacion servicioCrearPublicacion;

    @BeforeEach
    public void setUp() {
        repositorioMock = mock(RepositorioPublicacion.class);
        servicioCrearPublicacion = new ServicioPublicacionImpl(repositorioMock);
    }



    @Test
    public void siLosDatosDeLaPublicacionAdopcionSonCorrectosLaCreacionEsExitosa(){
        givenNoExistePublicacionAdopcion();
        Publicacion publicacionAdopcion = whenCreoUnaPublicacionAdopcionCon("Adopto a Firulais", "Perro juguetón busca familia", "Firulais es un Golden Retriever de 3 años, muy cariñoso y activo. Ideal para familias con niños.", "firulais.jpg", "Golden Retriever", 40, "CABA, Argentina", "1145123412", "firulais@mail.com", 3 );
        thenLaCreacionDePublicacionAdopcionEsExitosa(publicacionAdopcion);
    }


    private void givenNoExistePublicacionAdopcion() {
    }

    private Publicacion whenCreoUnaPublicacionAdopcionCon(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, String raza, int tamanio, String ubicacion, String telefono, String email, int edad) {
        PublicacionAdopcion publicacion = new PublicacionAdopcion();
        publicacion.setTitulo(titulo);
        publicacion.setDescripcionCorta(descripcionCorta);
        publicacion.setDescripcionDetallada(descripcionDetallada);
        publicacion.setImagen(imagen);
        publicacion.setRaza(raza);
        publicacion.setTamanio(tamanio);
        publicacion.setUbicacion(ubicacion);
        publicacion.setTelefono(telefono);
        publicacion.setEmail(email);
        publicacion.setEdad(edad);
          servicioCrearPublicacion.guardar(publicacion);
        return publicacion;
    }

    private void thenLaCreacionDePublicacionAdopcionEsExitosa(Publicacion publicacion) {
        assertThat(publicacion, notNullValue());
    }

    @Test
    public void siLosDatosDeLaPublicacionRecaudacionSonCorrectosLaCreacionEsExitosa(){
        givenNoExistePublicacionRecaudacion();
        Publicacion publicacionRecaudacion = whenCreoUnaPublicacionRecaudacionCon("Campaña para operar a Pelusa", "Ayudanos a que Pelusa vuelva a correr.", "Pelusa es un perrito mestizo de 5 años que fue atropellado y necesita una cirugía urgente en su patita trasera.", "Pelusa.jpg", "Mestizo",50 , "Lanus, Argentina", "1145332211", "ayuda.rocky@gmail.com", 5, 150000.0, "0000003100035478292345", "Transferencia bancaria");
        thenLaCreacionDePublicacionRecaudacionEsExitosa(publicacionRecaudacion);
    }


    private void givenNoExistePublicacionRecaudacion() {
    }

    private Publicacion whenCreoUnaPublicacionRecaudacionCon(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, String raza, int tamanio, String ubicacion, String telefono, String email, int edad, double meta, String cbu, String metodoPreferido) {
        PublicacionRecaudacion publicacion = new PublicacionRecaudacion();
        publicacion.setTitulo(titulo);
        publicacion.setDescripcionCorta(descripcionCorta);
        publicacion.setDescripcionDetallada(descripcionDetallada);
        publicacion.setImagen(imagen);
        publicacion.setRaza(raza);
        publicacion.setTamanio(tamanio);
        publicacion.setUbicacion(ubicacion);
        publicacion.setTelefono(telefono);
        publicacion.setEmail(email);
        publicacion.setEdad(edad);
        publicacion.setMeta(meta);
        publicacion.setCbu(cbu);
        publicacion.setMetodoPreferido(metodoPreferido);
        servicioCrearPublicacion.guardar(publicacion);
        return publicacion;
    }

    private void thenLaCreacionDePublicacionRecaudacionEsExitosa(Publicacion publicacion) {
        assertThat(publicacion, notNullValue());
    }

    @Test
    public void siLosDatosDeLaPublicacionSaludSonCorrectosLaCreacionEsExitosa(){
        givenNoExistePublicacionSalud();
        Publicacion publicacionSalud = whenCreoUnaPublicacionSaludCon("Fito", "Informe veterinario.", "Se observan signos de letargo y cojera en la pata trasera derecha .Se realizaron radiografías que muestran fractura parcial, y se recomienda reposo y control médico constante.", "Fito.jpg", "Siames", 35, "CABA, Argentina", "1185262211", "ayuda.fito@gmail.com", 3, "Inflamación en pata delantera izquierda, disminución de apetito", "Inflamación leve en articulación; posible deficiencia nutricional", "Prioritario");
        thenLaCreacionDePublicacionSaludEsExitosa(publicacionSalud);
    }


    private void givenNoExistePublicacionSalud() {
    }

    private Publicacion whenCreoUnaPublicacionSaludCon(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, String raza, int tamanio, String ubicacion, String telefono, String email, int edad, String sintomasPrincipales, String diagnostico, String nivelUrgencia) {
        PublicacionSalud publicacion = new PublicacionSalud();
        publicacion.setTitulo(titulo);
        publicacion.setDescripcionCorta(descripcionCorta);
        publicacion.setDescripcionDetallada(descripcionDetallada);
        publicacion.setImagen(imagen);
        publicacion.setRaza(raza);
        publicacion.setTamanio(tamanio);
        publicacion.setUbicacion(ubicacion);
        publicacion.setTelefono(telefono);
        publicacion.setEmail(email);
        publicacion.setEdad(edad);
        publicacion.setSintomasPrincipales(sintomasPrincipales);
        publicacion.setDiagnostico(diagnostico);
        publicacion.setNivelUrgencia(nivelUrgencia);
        servicioCrearPublicacion.guardar(publicacion);
        return publicacion;
    }

    private void thenLaCreacionDePublicacionSaludEsExitosa(Publicacion publicacion) {
        assertThat(publicacion, notNullValue());
    }


    @Test
    public void siLosDatosDeLaPublicacionPerdidoSonCorrectosLaCreacionEsExitosa(){
        givenNoExistePublicacionPerdido();
        Publicacion publicacionPerdido = whenCreoUnaPublicacionPerdidoCon("Se perdio Luna", "Ayudanos a encontrarla.", "Luna es una gatita mestiza de tamaño mediano, juguetona y muy amigable. Se escapó de su casa y necesitamos localizarla lo antes posible.", "Luna.jpg", "Mestizo", 25, "Merlo, Argentina", "1110652113", "luna@gmail.com", "2025-10-21", "12:00", true, "Si" );
        thenLaCreacionDePublicacionPerdidoEsExitosa(publicacionPerdido);
    }


    private void givenNoExistePublicacionPerdido() {
    }

    private Publicacion whenCreoUnaPublicacionPerdidoCon(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, String raza, int tamanio, String ubicacion, String telefono, String email, String fechaDesaparicion, String horaDesaparicion, boolean llevaCollar, String recompensa) {
        PublicacionPerdido publicacion = new PublicacionPerdido();
        publicacion.setTitulo(titulo);
        publicacion.setDescripcionCorta(descripcionCorta);
        publicacion.setDescripcionDetallada(descripcionDetallada);
        publicacion.setImagen(imagen);
        publicacion.setRaza(raza);
        publicacion.setTamanio(tamanio);
        publicacion.setUbicacion(ubicacion);
        publicacion.setTelefono(telefono);
        publicacion.setEmail(email);
        publicacion.setFechaDesaparicion(fechaDesaparicion);
        publicacion.setHoraDesaparicion(horaDesaparicion);
        publicacion.setLlevaCollar(llevaCollar);
        publicacion.setRecompensa(recompensa);
        servicioCrearPublicacion.guardar(publicacion);
        return publicacion;
    }

    private void thenLaCreacionDePublicacionPerdidoEsExitosa(Publicacion publicacion) {
        assertThat(publicacion, notNullValue());
    }


    @Test
    public void siLosDatosDeLaPublicacionEncontradoSonCorrectosLaCreacionEsExitosa(){
        givenNoExistePublicacionEncontrado();
        Publicacion publicacionEncontrado = whenCreoUnaPublicacionEncontradoCon("Perrito encontrado", "Se encontro un cachorro desorientado cerca de la plaza", "Cachorro mestizo de tamaño pequeño, mediano, aproximadamente 2 años. Muy juguetón y dócil. No tiene collar y parecía perdido desde hace unas horas. Se busca al dueño para poder devolverlo a su hogar.", "Mancha.jpg", "Mestizo", 35, "Ituzaingo, Argentina", "1185262211", "ayuda.manchita@gmail.com");
        thenLaCreacionDePublicacionEncotradoEsExitosa(publicacionEncontrado);
    }


    private void givenNoExistePublicacionEncontrado() {
    }

    private Publicacion whenCreoUnaPublicacionEncontradoCon(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, String raza, int tamanio, String ubicacion, String telefono, String email) {
        PublicacionEncontrado publicacion = new PublicacionEncontrado();
        publicacion.setTitulo(titulo);
        publicacion.setDescripcionCorta(descripcionCorta);
        publicacion.setDescripcionDetallada(descripcionDetallada);
        publicacion.setImagen(imagen);
        publicacion.setRaza(raza);
        publicacion.setTamanio(tamanio);
        publicacion.setUbicacion(ubicacion);
        publicacion.setTelefono(telefono);
        publicacion.setEmail(email);
        servicioCrearPublicacion.guardar(publicacion);
        return publicacion;
    }

    private void thenLaCreacionDePublicacionEncotradoEsExitosa(Publicacion publicacion) {
        assertThat(publicacion, notNullValue());
    }
}
