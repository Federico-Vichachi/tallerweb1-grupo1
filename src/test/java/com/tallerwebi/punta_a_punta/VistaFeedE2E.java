package com.tallerwebi.punta_a_punta;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.tallerwebi.punta_a_punta.vistas.VistaFeed;
import org.junit.jupiter.api.*;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class VistaFeedE2E {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    VistaFeed vistaFeed;
    Page page;

    @BeforeAll
    static void abrirNavegador() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        // browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
    }

    @AfterAll
    static void cerrarNavegador() {
        playwright.close();
    }

    @BeforeEach
    void crearContextoYPagina() {
        ReiniciarDB.limpiarBaseDeDatos();

        context = browser.newContext();
        page = context.newPage();
        vistaFeed = new VistaFeed(page);
    }

    @AfterEach
    void cerrarContexto() {
        context.close();
    }

    @Test
    void deberiaMostrarPublicacionesAlCargarLaPagina() {
        dadoQueElUsuarioEstaEnLaVistaDeFeed();
        entoncesDeberiaVerAlMenosUnaPublicacion();
    }

    @Test
    void elNombreDeLaPrimeraMascotaDeberiaSerElEsperado() {
        dadoQueElUsuarioEstaEnLaVistaDeFeed();
        entoncesElNombreDeLaPrimeraMascotaEs("Toto");
    }

    @Test
    void deberiaNavegarALaPaginaDeCrearPublicacion() throws MalformedURLException {
        dadoQueElUsuarioEstaEnLaVistaDeFeed();
        cuandoElUsuarioHaceClicEnNuevaPublicacion();
        entoncesEsRedirigidoALaPaginaDeCrearPublicacion();
    }

    private void dadoQueElUsuarioEstaEnLaVistaDeFeed() {
        try {
            URL urlActual = vistaFeed.obtenerURLActual();
            assertThat("La URL actual no es la del feed", urlActual.getPath(), endsWith("/spring/feed"));
        } catch (MalformedURLException e) {
            Assertions.fail("La URL actual es inválida: " + e.getMessage());
        }
    }

    private void cuandoElUsuarioHaceClicEnNuevaPublicacion() {
        vistaFeed.irANuevaPublicacion();
    }

    private void entoncesDeberiaVerAlMenosUnaPublicacion() {
        int cantidadDePublicaciones = vistaFeed.obtenerPublicaciones().count();
        assertThat("No se encontraron publicaciones en la página", cantidadDePublicaciones, is(greaterThan(0)));
    }



    private void entoncesElNombreDeLaPrimeraMascotaEs(String nombreEsperado) {
        String nombreObtenido = vistaFeed.obtenerNombreDeMascotaDeLaPrimeraPublicacion();
        assertThat("El nombre de la primera mascota no es el esperado", nombreObtenido, is(equalTo(nombreEsperado)));
    }

    private void entoncesEsRedirigidoALaPaginaDeCrearPublicacion() throws MalformedURLException {
        URL urlActual = vistaFeed.obtenerURLActual();
        assertThat("No se redirigió a la página de crear publicación", urlActual.getPath(), endsWith("/spring/crear-publicacion"));
    }
}