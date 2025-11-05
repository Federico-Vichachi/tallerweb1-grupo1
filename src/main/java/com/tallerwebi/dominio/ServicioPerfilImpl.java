package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.*;
import com.tallerwebi.presentacion.DatosEdicionPerfil;
import com.tallerwebi.presentacion.DatosRegistro;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.tallerwebi.dominio.ExpresionesRegularesParaLaValidacionDeDatosDeUsuario.*;

@Service
@Transactional
public class ServicioPerfilImpl implements  ServicioPerfil {

    private RepositorioUsuario repositorioUsuario;

    public ServicioPerfilImpl(RepositorioUsuario repositorioUsuario) { this.repositorioUsuario = repositorioUsuario; }

    @Override
    public void guardarCambiosPerfil(DatosEdicionPerfil datosEdicionPerfil) {
        verificarFormatoDeLosDatosDeEdicion(datosEdicionPerfil);
        repositorioUsuario.guardarCambiosPerfil(datosEdicionPerfil);
    }


    @Override
    public Usuario buscarPorId(Long id) {
        return repositorioUsuario.buscarPorId(id);
    }

    public void verificarFormatoDeLosDatosDeEdicion(DatosEdicionPerfil datosEdicionPerfil) {

        if (!datosEdicionPerfil.getEmail().matches(FORMATO_EMAIL)) {
            throw new FormatoDeEmailInvalidoException("El formato del email es inválido.");
        }

        if (!datosEdicionPerfil.getNombre().matches(FORMATO_NOMBRE_O_APELLIDO)) {
            throw new FormatoDeNombreOApellidoInvalidoException("El formato del nombre es inválido.");
        }

        if (!datosEdicionPerfil.getApellido().matches(FORMATO_NOMBRE_O_APELLIDO)) {
            throw new FormatoDeNombreOApellidoInvalidoException("El formato del apellido es inválido.");
        }

        if (!datosEdicionPerfil.getNombreDeUsuario().matches(FORMATO_NOMBRE_DE_USUARIO)) {
            throw new FormatoDeNombreDeUsuarioInvalidoException("El formato del nombre de usuario es inválido.");
        }

        if (!datosEdicionPerfil.getTelefono().matches(FORMATO_TELEFONO)) {
            throw new FormatoDeTelefonoInvalidoException("El formato del teléfono es inválido.");
        }

        if (!datosEdicionPerfil.getCalle().matches(FORMATO_CALLE)) {
            throw new FormatoDeCalleInvalidoException("El formato de la calle es inválido.");
        }

        if (!datosEdicionPerfil.getNumero().matches(FORMATO_NUMERO_DE_DOMICILIO)) {
            throw new FormatoDeNumeroInvalidoException("El formato del número de domicilio es inválido.");
        }

        if (!datosEdicionPerfil.getCiudad().matches(FORMATO_CIUDAD)) {
            throw new FormatoDeCiudadInvalidoException("El formato de la ciudad es inválido.");
        }

        if (!datosEdicionPerfil.getCodigoPostal().matches(FORMATO_CODIGO_POSTAL)) {
            throw new FormatoDeCodigoPostalInvalidoException("El formato del código postal es inválido.");
        }

        if (datosEdicionPerfil.getPiso() != null && !datosEdicionPerfil.getPiso().isEmpty()
                && !datosEdicionPerfil.getPiso().matches(FORMATO_PISO)) {
            throw new FormatoDePisoInvalidoException("El formato del piso es inválido.");
        }

        if (datosEdicionPerfil.getDepartamento() != null && !datosEdicionPerfil.getDepartamento().isEmpty()
                && !datosEdicionPerfil.getDepartamento().matches(FORMATO_DEPARTAMENTO)) {
            throw new FormatoDeDepartamentoInvalidoException("El formato del departamento es inválido.");
        }

        if (repositorioUsuario.buscarPorEmailExcluyendoUsuario(datosEdicionPerfil.getEmail(), datosEdicionPerfil.getId()) != null) {
            throw new EmailYaUsadoException("Ya existe un usuario registrado con ese email.");
        }
        if (repositorioUsuario.buscarPorNombreDeUsuarioExcluyendoUsuario(datosEdicionPerfil.getNombreDeUsuario(), datosEdicionPerfil.getId()) != null) {
            throw new NombreDeUsuarioYaUsadoException("Ya existe un usuario registrado con ese nombre de usuario.");
        }
        if (repositorioUsuario.buscarPorTelefonoExcluyendoUsuario(datosEdicionPerfil.getTelefono(), datosEdicionPerfil.getId()) != null) {
            throw new TelefonoYaUsadoException("Ya existe un usuario registrado con ese teléfono.");
        }

    }

}
