package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.*;
import org.hibernate.SessionFactory;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
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
public class RepositorioCrearPublicacionTest {

    /*
    * 1. Puedo guarda una publicacion.
    *
     */

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RepositorioPublicacion repositorioCrearPublicacion;

    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnaPublicacionAdopcion(){
        Publicacion publicacionAdopcion = givenTengoUnaPublicacionAdopcion("Adopto a Firulais", "Perro juguetón busca familia", "Golden Retriever de 3 años muy cariñoso", "firulais.jpg", 3, 45, "Golden Retriever", Provincias.CORDOBA, "La matanza" ,"1145123412", "firulais@mail.com");
        whenGuardoLaPublicacionAdopcion(publicacionAdopcion);
        thenSePuedeGuardarCorrectamenteLaPublicacionAdopcion(publicacionAdopcion);
    }


    private Publicacion givenTengoUnaPublicacionAdopcion(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, Integer edad, Integer tamanio, String raza, Provincias provincia,String localidad, String telefono, String email) {
        PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion();
        publicacionAdopcion.setTitulo(titulo);
        publicacionAdopcion.setDescripcionCorta(descripcionCorta);
        publicacionAdopcion.setDescripcionDetallada(descripcionDetallada);
        publicacionAdopcion.setImagen(imagen);
        publicacionAdopcion.setEdad(edad);
        publicacionAdopcion.setTamanio(tamanio);
        publicacionAdopcion.setRaza(raza);
        publicacionAdopcion.setProvincia(provincia);
        publicacionAdopcion.setLocalidad(localidad);
        publicacionAdopcion.setTelefono(telefono);
        publicacionAdopcion.setEmail(email);
        repositorioCrearPublicacion.guardar(publicacionAdopcion);
        return publicacionAdopcion;
    }

    private void whenGuardoLaPublicacionAdopcion(Publicacion publicacionAdopcion) {
    repositorioCrearPublicacion.guardar(publicacionAdopcion);
    }


    private void thenSePuedeGuardarCorrectamenteLaPublicacionAdopcion(Publicacion publicacionAdopcion) {
        PublicacionAdopcion guardada = sessionFactory.getCurrentSession()
                .get(PublicacionAdopcion.class, publicacionAdopcion.getId());
        assertThat(guardada, notNullValue());
        assertThat(guardada.getTitulo(), equalTo(publicacionAdopcion.getTitulo()));
    }


    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnaPublicacionRecaudacion(){
        Publicacion publicacionRecaudacion = givenTengoUnaPublicacionRecaudacion("Campaña para operar a Pelusa", "Ayudanos a que Pelusa vuelva a correr.", "Pelusa es un perrito mestizo de 5 años que fue atropellado y necesita una cirugía urgente en su patita trasera.", "Pelusa.jpg", "Mestizo",50 ,Provincias.CATAMARCA, "Chacarita", "1145332211", "ayuda.rocky@gmail.com", 5, 150000.0, "0000003100035478292345", "Transferencia bancaria");
        whenGuardoLaPublicacionRecaudacion(publicacionRecaudacion);
        thenSePuedeGuardarCorrectamenteLaPublicacionRecaudacion(publicacionRecaudacion);
    }


    private Publicacion givenTengoUnaPublicacionRecaudacion(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, String raza, int tamanio, Provincias provincia,String localidad, String telefono, String email, int edad, double meta, String cbu, String metodoPreferido) {
        PublicacionRecaudacion publicacion = new PublicacionRecaudacion();
        publicacion.setTitulo(titulo);
        publicacion.setDescripcionCorta(descripcionCorta);
        publicacion.setDescripcionDetallada(descripcionDetallada);
        publicacion.setImagen(imagen);
        publicacion.setRaza(raza);
        publicacion.setTamanio(tamanio);
        publicacion.setProvincia(provincia);
        publicacion.setLocalidad(localidad);
        publicacion.setTelefono(telefono);
        publicacion.setEmail(email);
        publicacion.setEdad(edad);
        publicacion.setMeta(meta);
        publicacion.setCbu(cbu);
        publicacion.setMetodoPreferido(metodoPreferido);
        repositorioCrearPublicacion.guardar(publicacion);
        return publicacion;
    }

    private void whenGuardoLaPublicacionRecaudacion(Publicacion publicacionRecaudacion) {
        repositorioCrearPublicacion.guardar(publicacionRecaudacion);
    }


    private void thenSePuedeGuardarCorrectamenteLaPublicacionRecaudacion(Publicacion publicacionRecaudacion) {
        PublicacionRecaudacion guardada = sessionFactory.getCurrentSession()
                .get(PublicacionRecaudacion.class, publicacionRecaudacion.getId());
        assertThat(guardada, notNullValue());
        assertThat(guardada.getTitulo(), equalTo(publicacionRecaudacion.getTitulo()));
    }


    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnaPublicacionSalud(){
        Publicacion publicacionSalud = givenTengoUnaPublicacionSalud("Fito", "Informe veterinario.", "Se observan signos de letargo y cojera en la pata trasera derecha .Se realizaron radiografías que muestran fractura parcial, y se recomienda reposo y control médico constante.", "Fito.jpg", "Siames", 35, Provincias.BUENOS_AIRES,"San Isidro", "1185262211", "ayuda.fito@gmail.com", 3, "Inflamación en pata delantera izquierda, disminución de apetito", "Inflamación leve en articulación; posible deficiencia nutricional", "Prioritario");
        whenGuardoLaPublicacionSalud(publicacionSalud);
        thenSePuedeGuardaCorrectamenteLaPublicacionSalud(publicacionSalud);
    }

    private Publicacion givenTengoUnaPublicacionSalud(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, String raza, int tamanio, Provincias provincia,String localidad, String telefono, String email, int edad, String sintomasPrincipales, String diagnostico, String nivelUrgencia) {
         PublicacionSalud publicacion = new PublicacionSalud();
         publicacion.setTitulo(titulo);
         publicacion.setDescripcionCorta(descripcionCorta);
         publicacion.setDescripcionDetallada(descripcionDetallada);
         publicacion.setImagen(imagen);
         publicacion.setRaza(raza);
         publicacion.setTamanio(tamanio);
         publicacion.setProvincia(provincia);
         publicacion.setLocalidad(localidad);
         publicacion.setTelefono(telefono);
         publicacion.setEmail(email);
         publicacion.setEdad(edad);
         publicacion.setSintomasPrincipales(sintomasPrincipales);
         publicacion.setDiagnostico(diagnostico);
         publicacion.setNivelUrgencia(nivelUrgencia);
         repositorioCrearPublicacion.guardar(publicacion);
         return publicacion;
    }

    private void whenGuardoLaPublicacionSalud(Publicacion publicacionSalud) {
        repositorioCrearPublicacion.guardar(publicacionSalud);
    }


    private void thenSePuedeGuardaCorrectamenteLaPublicacionSalud(Publicacion publicacionSalud) {
        PublicacionSalud guardada = sessionFactory.getCurrentSession()
                .get(PublicacionSalud.class, publicacionSalud.getId());
        assertThat(guardada, notNullValue());
        assertThat(guardada.getTitulo(), equalTo(publicacionSalud.getTitulo()));
    }


    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnaPublicacionPerdido(){
        Publicacion publicacionPerdido = givenTengoUnaPublicacionPerdido("Se perdio Luna", "Ayudanos a encontrarla.", "Luna es una gatita mestiza de tamaño mediano, juguetona y muy amigable. Se escapó de su casa y necesitamos localizarla lo antes posible.", "Luna.jpg", "Mestizo", 25,Provincias.CAPITAL_FEDERAL, "Merlo", "1110652113", "luna@gmail.com", "luna@gmail.com", "12:00", true, "Si" );
        whenGuardoLaPublicacionPerdido(publicacionPerdido);
        thenSePuedeGuardarCorrectamenteLaPublicacionPerdido(publicacionPerdido);
    }


    private Publicacion givenTengoUnaPublicacionPerdido(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, String raza, int tamanio, Provincias provincia,String localidad, String telefono, String email, String fechaDesaparicion, String horaDesaparicion, boolean llevaCollar, String recompensa) {
        PublicacionPerdido publicacion = new PublicacionPerdido();
        publicacion.setTitulo(titulo);
        publicacion.setDescripcionCorta(descripcionCorta);
        publicacion.setDescripcionDetallada(descripcionDetallada);
        publicacion.setImagen(imagen);
        publicacion.setRaza(raza);
        publicacion.setTamanio(tamanio);
        publicacion.setProvincia(provincia);
        publicacion.setLocalidad(localidad);
        publicacion.setTelefono(telefono);
        publicacion.setEmail(email);
        publicacion.setFechaDesaparicion(fechaDesaparicion);
        publicacion.setHoraDesaparicion(horaDesaparicion);
        publicacion.setLlevaCollar(llevaCollar);
        publicacion.setRecompensa(recompensa);
        repositorioCrearPublicacion.guardar(publicacion);
        return publicacion;
    }

    private void whenGuardoLaPublicacionPerdido(Publicacion publicacionPerdido) {
        repositorioCrearPublicacion.guardar(publicacionPerdido);
    }


    private void thenSePuedeGuardarCorrectamenteLaPublicacionPerdido(Publicacion publicacionPerdido) {
        PublicacionPerdido guardada = sessionFactory.getCurrentSession()
                .get(PublicacionPerdido.class, publicacionPerdido.getId());
        assertThat(guardada, notNullValue());
        assertThat(guardada.getTitulo(), equalTo(publicacionPerdido.getTitulo()));
    }


    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnaPublicacionEncontrado(){
        Publicacion publicacionEncontrado = givenTengoUnaPublicacionEncontrado("Perrito encontrado", "Se encontro un cachorro desorientado cerca de la plaza", "Cachorro mestizo de tamaño pequeño-mediano, aproximadamente 2 años. Muy juguetón y dócil. No tiene collar y parecía perdido desde hace unas horas. Se busca al dueño para poder devolverlo a su hogar.", "Mancha.jpg", "Mestizo", 35,Provincias.CORRIENTES, "Ituzaingo", "1185262211", "ayuda.manchita@gmail.com");
        whenGuardoLaPublicacionEncontrado(publicacionEncontrado);
        thenSePuedeGuardarCorrectamenteLaPublicacionEncontrado(publicacionEncontrado);
    }


    private Publicacion givenTengoUnaPublicacionEncontrado(String titulo, String descripcionCorta, String descripcionDetallada, String imagen, String raza, int tamanio, Provincias provincia,String localidad, String telefono, String email) {
        PublicacionEncontrado publicacion = new PublicacionEncontrado();
        publicacion.setTitulo(titulo);
        publicacion.setDescripcionCorta(descripcionCorta);
        publicacion.setDescripcionDetallada(descripcionDetallada);
        publicacion.setImagen(imagen);
        publicacion.setRaza(raza);
        publicacion.setTamanio(tamanio);
        publicacion.setProvincia(provincia);
        publicacion.setLocalidad(localidad);
        publicacion.setTelefono(telefono);
        publicacion.setEmail(email);
        repositorioCrearPublicacion.guardar(publicacion);
        return publicacion;
    }

    private void whenGuardoLaPublicacionEncontrado(Publicacion publicacionEncontrado) {
        repositorioCrearPublicacion.guardar(publicacionEncontrado);
    }


    private void thenSePuedeGuardarCorrectamenteLaPublicacionEncontrado(Publicacion publicacionEncontrado) {
        PublicacionEncontrado guardada = sessionFactory.getCurrentSession()
                .get(PublicacionEncontrado.class, publicacionEncontrado.getId());
        assertThat(guardada, notNullValue());
        assertThat(guardada.getTitulo(), equalTo(publicacionEncontrado.getTitulo()));
    }



}
