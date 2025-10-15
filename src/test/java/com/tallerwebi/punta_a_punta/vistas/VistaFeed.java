package com.tallerwebi.punta_a_punta.vistas;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class VistaFeed extends VistaWeb {

    public VistaFeed(Page page) {
        super(page);
        page.navigate("localhost:8080/spring/feed");
    }

    public void seleccionarCategoria(String categoria) {
        this.darClickEnElElemento("input[name='categoria'][value='" + categoria + "']");
    }


    public void aplicarFiltros() {
        this.darClickEnElElemento("button[type='submit']");
    }

    public void irANuevaPublicacion() {
        this.darClickEnElElemento("a.btn-nueva-publicacion");
    }

    public Locator obtenerPublicaciones() {
        return this.obtenerElementos("div.carta-adopcion");
    }

    public String obtenerNombreDeMascotaDeLaPrimeraPublicacion() {
        Locator todosLosTitulos = this.page.locator("h2.nombre-animal");
        return todosLosTitulos.first().textContent();
    }

}