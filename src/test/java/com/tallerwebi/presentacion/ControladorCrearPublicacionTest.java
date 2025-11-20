package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorCrearPublicacionTest {

    private ControladorCrearPublicacion controlador;
    private ServicioPublicacion servicioMock;

    private HttpServletRequest requestMock;
    private HttpSession sessionMock;
    private Usuario usuarioMock;
    private MultipartFile archivoMock;
    private DatosAdopcion datosAdopcion;
    private DatosRecaudacion datosRecaudacion;
    private DatosSalud datosSalud;
    private DatosPerdido datosPerdido;
    private DatosEncontrado datosEncontrado;

    @BeforeEach
    public void setUp() {
        servicioMock = mock(ServicioPublicacion.class);
        controlador = new ControladorCrearPublicacion(servicioMock);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        usuarioMock = mock(Usuario.class);
        archivoMock = mock(MultipartFile.class);
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("usuarioLogueado")).thenReturn(usuarioMock);
        when(archivoMock.isEmpty()).thenReturn(true);
    }


    @Test
    public void dadoQueExistenDatosValidosDeAdopcionCuandoCreoLaPublicacionEntoncesSeMuestraLaVistaDeAdopcion(){
        givenExisteUnaPublicacionAdopcion();
        ModelAndView mav = whenCreoLaPublicacionAdopcion();
        thenSeMuestraLaVistaDeAdopcion(mav);
    }

    private void givenExisteUnaPublicacionAdopcion() {
        datosAdopcion = new DatosAdopcion();
        datosAdopcion.setTitulo("Perrito en busca de hogar");
        datosAdopcion.setDescripcionCorta("Perro juguetón busca familia.");
        datosAdopcion.setDescripcionDetallada("Firulais es un Golden Retriever de 3 años, muy cariñoso y activo. Ideal para familias con niños.");
        datosAdopcion.setImagen("firulais.jpg");
        datosAdopcion.setRaza("Golden Retriever");
        datosAdopcion.setTamanio(40);
        datosAdopcion.setProvincia(Provincias.CORRIENTES);
        datosAdopcion.setLocalidad("La matanza");
        datosAdopcion.setTelefono("1145123412");
        datosAdopcion.setEmail("firulais@mail.com");
        datosAdopcion.setEdad(2);
    }

    private ModelAndView whenCreoLaPublicacionAdopcion() {
        return controlador.crearPublicacionAdopcion(datosAdopcion, archivoMock, requestMock);
    }

    private void thenSeMuestraLaVistaDeAdopcion(ModelAndView mav) {
        assertThat(mav, is(notNullValue()));
        assertThat(mav.getViewName(), is("redirect:/feed"));
    }


    @Test
    public void dadoQueExistenDatosValidosDeRecaudacionCuandoCreoLaPublicacionEntoncesSeMuestraLaVistaDeRecaudacion(){
        givenExisteUnaPublicacionRecaudacion();
        ModelAndView mav = whenCreoLaPublicacionRecaudacion();
        thenSeMuestraLaVistaDeRecaudacion(mav);
    }


    private void givenExisteUnaPublicacionRecaudacion() {
        datosRecaudacion = new DatosRecaudacion();
        datosRecaudacion.setTitulo("Campaña para operar a Pelusa");
        datosRecaudacion.setDescripcionCorta("Ayudanos a que Pelusa vuelva a correr.");
        datosRecaudacion.setDescripcionDetallada("Pelusa es un perrito mestizo de 5 años que fue atropellado y necesita una cirugía urgente en su patita trasera.");
        datosRecaudacion.setImagen("Pelusa.jpg");
        datosRecaudacion.setRaza("Mestizo");
        datosRecaudacion.setTamanio(50);
        datosRecaudacion.setProvincia(Provincias.ENTRE_RIOS);
        datosRecaudacion.setLocalidad("Merlo");
        datosRecaudacion.setTelefono("1145332211");
        datosRecaudacion.setEmail("ayuda.rocky@gmail.com");
        datosRecaudacion.setEdad(5);
        datosRecaudacion.setMeta(150000.0);
        datosRecaudacion.setCbu("0000003100035478292345");
        datosRecaudacion.setMetodoPreferido("Transferencia bancaria");

    }

    private ModelAndView whenCreoLaPublicacionRecaudacion() {
        return controlador.crearPublicacionRecaudacion(datosRecaudacion, archivoMock, requestMock);
    }

    private void thenSeMuestraLaVistaDeRecaudacion(ModelAndView mav) {
        assertThat(mav, is(notNullValue()));
        assertThat(mav.getViewName(), is("redirect:/feed"));
    }


    @Test
    public void dadoQueExistenDatosValidosDeSaludCuandoCreoLaPublicacionEntoncesSeMuestraLaVistaDeSalud(){
        givenExisteUnaPublicacionSalud();
        ModelAndView mav = whenCreoLaPublicacionSalud();
        thenSeMuestraLaVistaDeSalud(mav);
    }


    private void givenExisteUnaPublicacionSalud() {
        datosSalud = new DatosSalud();
        datosSalud.setTitulo("Fito");
        datosSalud.setDescripcionCorta("Informe veterinario.");
        datosSalud.setDescripcionDetallada("Se observan signos de letargo y cojera en la pata trasera derecha .Se realizaron radiografías que muestran fractura parcial, y se recomienda reposo y control médico constante.");
        datosSalud.setImagen("Fito.jpg");
        datosSalud.setRaza("Siames");
        datosSalud.setTamanio(35);
        datosSalud.setProvincia(Provincias.CATAMARCA);
        datosSalud.setLocalidad("CABA");
        datosSalud.setTelefono("1185262211");
        datosSalud.setEmail("ayuda.fito@gmail.com");
        datosSalud.setEdad(3);
        datosSalud.setSintomasPrincipales("Inflamación en pata delantera izquierda, disminución de apetito");
        datosSalud.setDiagnostico("Inflamación leve en articulación; posible deficiencia nutricional");
        datosSalud.setNivelUrgencia("Prioritario");
    }

    private ModelAndView whenCreoLaPublicacionSalud() {
        return controlador.crearPublicacionSalud(datosSalud, archivoMock, requestMock);
    }

    private void thenSeMuestraLaVistaDeSalud(ModelAndView mav) {
        assertThat(mav, is(notNullValue()));
        assertThat(mav.getViewName(), is("redirect:/feed"));
    }


    @Test
    public void dadoQueExistenDatosValidosDePerdidoCuandoCreoLaPublicacionEntoncesSeMuestraLaVistaDePerdido(){
        givenExisteUnaPublicacionPerdido();
        ModelAndView mav = whenCreoLaPublicacionPerdido();
        thenSeMuestraLaVistaDePerdido(mav);
    }



    private void givenExisteUnaPublicacionPerdido() {
        datosPerdido = new DatosPerdido();
        datosPerdido.setTitulo("Se perdio Luna");
        datosPerdido.setDescripcionCorta("Ayudanos a encontrarla.");
        datosPerdido.setDescripcionDetallada("Luna es una gatita mestiza de tamaño mediano, juguetona y muy amigable. Se escapó de su casa y necesitamos localizarla lo antes posible.");
        datosPerdido.setImagen("Luna.jpg");
        datosPerdido.setRaza("Mestizo");
        datosPerdido.setTamanio(25);
        datosPerdido.setProvincia(Provincias.FORMOSA);
        datosPerdido.setLocalidad("Merlo");
        datosPerdido.setTelefono("1110652113");
        datosPerdido.setEmail("luna@gmail.com");
        datosPerdido.setFechaDesaparicion("12/10/2025");
        datosPerdido.setHoraDesaparicion("12:00");
        datosPerdido.setLlevaCollar(true);
        datosPerdido.setRecompensa("Si");
    }

    private ModelAndView whenCreoLaPublicacionPerdido() {
        return controlador.crearPublicacionPerdido(datosPerdido, archivoMock, requestMock);
    }

    private void thenSeMuestraLaVistaDePerdido(ModelAndView mav) {
        assertThat(mav, is(notNullValue()));
        assertThat(mav.getViewName(), is("redirect:/feed"));
    }


    @Test
    public void dadoQueExistenDatosValidosDeEncontradoCuandoCreoLaPublicacionEntoncesSeMuestraLaVistaDeEncontrado(){
        givenExisteUnaPublicacionEncontrado();
        ModelAndView mav = whenCreoLaPublicacionEncontrado();
        thenSeMuestraLaVistaDeEncontrado(mav);
    }

    private void givenExisteUnaPublicacionEncontrado() {
        datosEncontrado = new DatosEncontrado();
        datosEncontrado.setTitulo("Perrito encontrado");
        datosEncontrado.setDescripcionCorta("Se encontro un cachorro desorientado cerca de la plaza");
        datosEncontrado.setDescripcionDetallada("Cachorro mestizo de tamaño pequeño-mediano, aproximadamente 2 años. Muy juguetón y dócil. No tiene collar y parecía perdido desde hace unas horas. Se busca al dueño para poder devolverlo a su hogar.");
        datosEncontrado.setImagen("Mancha.jpg");
        datosEncontrado.setRaza("Mestizo");
        datosEncontrado.setTamanio(35);
        datosEncontrado.setProvincia(Provincias.CORDOBA);
        datosEncontrado.setLocalidad("Ituzaingo");
        datosEncontrado.setTelefono("1185262211");
        datosEncontrado.setEmail("ayuda.manchita@gmail.com");
    }

    private ModelAndView whenCreoLaPublicacionEncontrado() {
        return controlador.crearPublicacionEncontrado(datosEncontrado, archivoMock, requestMock);
    }

    private void thenSeMuestraLaVistaDeEncontrado(ModelAndView mav) {
        assertThat(mav, is(notNullValue()));
        assertThat(mav.getViewName(), is("redirect:/feed"));
    }




}