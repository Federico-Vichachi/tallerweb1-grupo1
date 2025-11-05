package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosEdicionPerfil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioPerfilImpl implements  ServicioPerfil {

    private RepositorioUsuario repositorioUsuario;

    public ServicioPerfilImpl(RepositorioUsuario repositorioUsuario) { this.repositorioUsuario = repositorioUsuario; }

    @Override
    public void guardarCambiosPerfil(DatosEdicionPerfil datosEdicionPerfil) {
        repositorioUsuario.guardarCambiosPerfil(datosEdicionPerfil);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return repositorioUsuario.buscarPorId(id);
    }
}
