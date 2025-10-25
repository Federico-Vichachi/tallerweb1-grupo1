package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.*;
import com.tallerwebi.presentacion.DatosRegistro;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.tallerwebi.dominio.ExpresionesRegularesParaLaValidacionDeDatosDeRegistro.*;

@Service
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro {

    RepositorioUsuario repositorioUsuario;

    public ServicioRegistroImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void registrarUsuario(DatosRegistro datosRegistro) {

        verificarFormatoDeLosDatosDeRegistro(datosRegistro);

        Usuario nuevoUsuario = crearNuevoUsuario(datosRegistro);

        repositorioUsuario.guardar(nuevoUsuario);

    }

    private Usuario crearNuevoUsuario(DatosRegistro datosRegistro) {
        return new  Usuario(datosRegistro.getNombre(),
                datosRegistro.getApellido(),
                datosRegistro.getEmail(),
                datosRegistro.getTelefono(),
                datosRegistro.getContrasenia(),
                datosRegistro.getNombreDeUsuario(),
                datosRegistro.getRol(),
                new Domicilio(
                        datosRegistro.getCalle(),
                        datosRegistro.getNumero(),
                        datosRegistro.getCiudad(),
                        datosRegistro.getProvincia(),
                        datosRegistro.getCodigoPostal(),
                        datosRegistro.getDepartamento(),
                        datosRegistro.getPiso()
                )
        );
    }

    public void verificarFormatoDeLosDatosDeRegistro(DatosRegistro datosRegistro) {

        if (!datosRegistro.getEmail().matches(FORMATO_EMAIL)) {
            throw new FormatoDeEmailInvalidoException("El formato del email es inválido.");
        }

        if (!datosRegistro.getContrasenia().matches(FORMATO_CONTRASENIA)) {
            throw new FormatoDeContraseniaInvalidoException("El formato de la contraseña es inválido.");
        }

        if (!datosRegistro.getNombre().matches(FORMATO_NOMBRE_O_APELLIDO)) {
            throw new FormatoDeNombreOApellidoInvalidoException("El formato del nombre es inválido.");
        }

        if (!datosRegistro.getApellido().matches(FORMATO_NOMBRE_O_APELLIDO)) {
            throw new FormatoDeNombreOApellidoInvalidoException("El formato del apellido es inválido.");
        }

        if (!datosRegistro.getNombreDeUsuario().matches(FORMATO_NOMBRE_DE_USUARIO)) {
            throw new FormatoDeNombreDeUsuarioInvalidoException("El formato del nombre de usuario es inválido.");
        }

        if (!datosRegistro.getTelefono().matches(FORMATO_TELEFONO)) {
            throw new FormatoDeTelefonoInvalidoException("El formato del teléfono es inválido.");
        }

        if (!datosRegistro.getCalle().matches(FORMATO_CALLE)) {
            throw new FormatoDeCalleInvalidoException("El formato de la calle es inválido.");
        }

        if (!datosRegistro.getNumero().matches(FORMATO_NUMERO_DE_DOMICILIO)) {
            throw new FormatoDeNumeroInvalidoException("El formato del número de domicilio es inválido.");
        }

        if (!datosRegistro.getCiudad().matches(FORMATO_CIUDAD)) {
            throw new FormatoDeCiudadInvalidoException("El formato de la ciudad es inválido.");
        }

        if (!datosRegistro.getCodigoPostal().matches(FORMATO_CODIGO_POSTAL)) {
            throw new FormatoDeCodigoPostalInvalidoException("El formato del código postal es inválido.");
        }

        if (datosRegistro.getPiso() != null && !datosRegistro.getPiso().isEmpty()
                && !datosRegistro.getPiso().matches(FORMATO_PISO)) {
            throw new FormatoDePisoInvalidoException("El formato del piso es inválido.");
        }

        if (datosRegistro.getDepartamento() != null && !datosRegistro.getDepartamento().isEmpty()
                && !datosRegistro.getDepartamento().matches(FORMATO_DEPARTAMENTO)) {
            throw new FormatoDeDepartamentoInvalidoException("El formato del departamento es inválido.");
        }

        if (repositorioUsuario.buscarPorEmail(datosRegistro.getEmail()) != null) {
            throw new EmailYaUsadoException("Ya existe un usuario registrado con ese email.");
        }
        if (repositorioUsuario.buscarPorNombreDeUsuario(datosRegistro.getNombreDeUsuario()) != null) {
            throw new NombreDeUsuarioYaUsadoException("Ya existe un usuario registrado con ese nombre de usuario.");
        }
        if (repositorioUsuario.buscarPorTelefono(datosRegistro.getTelefono()) != null) {
            throw new TelefonoYaUsadoException("Ya existe un usuario registrado con ese teléfono.");
        }

    }

}
