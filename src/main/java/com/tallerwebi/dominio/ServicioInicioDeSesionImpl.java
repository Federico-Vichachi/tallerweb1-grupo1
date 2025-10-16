package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.DatosDeInicioDeSesionIncorrectosException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioInicioDeSesionImpl implements ServicioInicioDeSesion {

    RepositorioUsuario repositorioUsuario;

    @Override
    public void iniciarSesion(String email, String contrasenia) {

        if (repositorioUsuario.buscarPorEmail(email) == null
        || !repositorioUsuario.buscarPorEmail(email).getContrasenia().equals(contrasenia)) {
            throw new DatosDeInicioDeSesionIncorrectosException("El email o la contraseña son incorrectos.");
        }

    }
}
