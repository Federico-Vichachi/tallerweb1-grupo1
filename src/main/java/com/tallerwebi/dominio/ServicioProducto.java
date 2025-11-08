package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioProducto {
    void guardar(Producto producto);
    List<Producto> listarProductos();
    Producto buscarPorId(Long id);
    public boolean hayStockDisponible(Producto producto, int cantidad);
    public void descontarStock(Producto producto, int cantidad);

}
