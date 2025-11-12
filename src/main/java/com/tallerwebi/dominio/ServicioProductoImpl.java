package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ValidacionProductoException;
import com.tallerwebi.dominio.excepcion.ValidacionPublicacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

import static com.tallerwebi.dominio.ExpresionesRegularesParaLaValidacionDeDatosDeProducto.*;


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
        verificarFormatoDeLosDatosDeProducto(producto);
        repositorioProducto.guardar(producto);
    }

    private void verificarFormatoDeLosDatosDeProducto(Producto producto) {
        if (producto.getNombre() == null || !producto.getNombre().matches(FORMATO_NOMBRE)) {
            throw new ValidacionProductoException("El título no cumple con el formato requerido (3-255 caracteres).");
        }

        if (producto.getDescripcion() != null && !producto.getDescripcion().matches(FORMATO_DESCRIPCION)) {
            throw new ValidacionProductoException("La descripción corta no cumple con el formato requerido (1-100 caracteres).");
        }

        if (producto.getPrecio() == null || producto.getPrecio().doubleValue() <= 0) {
            throw new ValidacionProductoException("El precio debe ser mayor a 0.");
        }

        if (producto.getPrecioEnPuntos() < 0) {
            throw new ValidacionProductoException("El precio en puntos no puede ser negativo.");
        }

        if (producto.getStock() < 0) {
            throw new ValidacionProductoException("El stock no puede ser negativo.");
        }

        if (producto.getImagen() == null || !producto.getImagen().matches(FORMATO_IMAGEN)) {
            throw new ValidacionProductoException("El formato de imagen no es válido (solo .jpg, .jpeg, .png o .webp).");
        }

    }

    @Override
    public List<Producto> listarProductos() {
        return repositorioProducto.buscarProductos();
    }

    @Override
    public Producto buscarPorId(Long id) {
        return repositorioProducto.buscarPorId(id);
    }

    @Override
    public boolean hayStockDisponible(Producto producto, int cantidad) {
        return producto.getStock() != 0 && producto.getStock() >= cantidad;
    }

    @Override
    public void descontarStock(Producto producto, int cantidad) {
        producto.descontarStock(cantidad);
        repositorioProducto.guardar(producto);
    }
}
