package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioProducto {
    void guardar(Producto producto);
    List<Producto> buscarProductos();
}
