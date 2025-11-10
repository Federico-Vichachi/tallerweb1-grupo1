package com.tallerwebi.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioPuntosImpl implements ServicioPuntos {

    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioPuntosImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void sumarPuntos(Usuario usuario, Double monto) {
        int puntos = calcularPuntosPorDonacion(monto);
        usuario.sumarPuntos(puntos);
        repositorioUsuario.guardar(usuario);
    }

    @Override
    public boolean gastarPuntos(Usuario usuario, Producto producto) {
        int puntos = producto.getPrecioEnPuntos();
        boolean pudoGastar = usuario.gastarPuntos(puntos);
        if (pudoGastar) {
            repositorioUsuario.guardar(usuario);
        }
        return pudoGastar;
    }

    @Override
    public int calcularPuntosPorDonacion(Double monto) {
        if (monto <= 1000) {
            return 10;
        }
        if (monto > 1000 && monto <= 3000) {
            return 25;
        }
        if (monto > 3000 && monto <= 6000) {
            return 40;
        }
        return 50;
    }
}
