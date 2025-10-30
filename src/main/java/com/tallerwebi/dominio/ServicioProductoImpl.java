package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioProductoImpl implements ServicioProducto{

    private final RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioProductoImpl(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    @Override
    public void guardar(Producto producto) {
        repositorioProducto.guardar(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        return repositorioProducto.buscarProductos();
    }

    @Override
    public Producto buscarPorId(Long id) {
        return repositorioProducto.buscarPorId(id);
    }
}
