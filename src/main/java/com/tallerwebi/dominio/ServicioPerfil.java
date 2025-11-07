package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosEdicionPerfil;

public interface ServicioPerfil {
    void guardarCambiosPerfil(DatosEdicionPerfil datosEdicionPerfil);
    Usuario buscarPorId(Long id);
}
