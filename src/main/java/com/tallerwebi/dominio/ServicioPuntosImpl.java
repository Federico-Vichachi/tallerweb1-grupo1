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
    public boolean gastarPuntos(Usuario usuario, Producto producto, int cantidad) {
        int puntos = producto.getPrecioEnPuntos() * cantidad;

        if (usuario.getPuntos() >= puntos) {
            usuario.setPuntos(usuario.getPuntos() - puntos);
            repositorioUsuario.guardar(usuario);
            return true;
        }
        return false;
    }

    @Override
    public int calcularPuntosPorDonacion(Double monto) {
        double porcentaje;

        if (monto <= 1000) {
            porcentaje = 0.01;
        }else if (monto <= 3000) {
            porcentaje = 0.015;
        }else if (monto <= 6000) {
            porcentaje = 0.02;
        }else{
            porcentaje = 0.025;
        }
        return (int) Math.round(monto * porcentaje);
    }
}
