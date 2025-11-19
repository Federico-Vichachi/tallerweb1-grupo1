package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioProducto {
    void guardar(Producto producto);
    List<Producto> listarProductos();
    Producto buscarPorId(Long id);
    void descontarStock(Producto producto, int cantidad);
    void actualizar(Producto producto);
}
