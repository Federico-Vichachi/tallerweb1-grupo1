package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.DatosDeInicioDeSesionIncorrectosException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioInicioDeSesionImpl implements ServicioInicioDeSesion {

    RepositorioUsuario repositorioUsuario;

    public ServicioInicioDeSesionImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public Usuario iniciarSesion(String email, String contrasenia) {
        Usuario usuario = repositorioUsuario.buscarPorEmail(email);
        if (usuario == null || !usuario.getContrasenia().equals(contrasenia)) {
            throw new DatosDeInicioDeSesionIncorrectosException("El email o la contraseña son incorrectos.");
        }
        return usuario;
    }

}
