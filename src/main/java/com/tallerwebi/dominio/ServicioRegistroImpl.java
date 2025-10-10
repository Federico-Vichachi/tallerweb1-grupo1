package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro {

    RepositorioUsuario repositorioUsuario;

    public ServicioRegistroImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void registrarUsuario(Usuario usuario) {

        if (!validarFormatoEmail(usuario.getEmail())) {
            throw new FormatoDeEmailInvalidoException("El formato del email es inválido.");
        }
        if (!validarFormatoNombreOApellido(usuario.getNombre())) {
            throw new FormatoDeNombreOApellidoInvalidoException("El formato del nombre es inválido.");
        }
        if (!validarFormatoNombreOApellido(usuario.getApellido())) {
            throw new FormatoDeNombreOApellidoInvalidoException("El formato del apellido es inválido.");
        }
        if (!validarFormatoNombreDeUsuario(usuario.getNombreDeUsuario())) {
            throw new FormatoDeNombreDeUsuarioInvalidoException("El formato del nombre de usuario es inválido.");
        }
        if (!validarFormatoTelefono(usuario.getTelefono())) {
            throw new FormatoDeTelefonoInvalidoException("El formato del teléfono es inválido.");
        }
        if (!validarFormatoContrasenia(usuario.getContrasenia())) {
            throw new FormatoDeContraseniaInvalidoException("El formato de la contraseña es inválido.");
        }
        if (!validarFormatoCalle(usuario.getDomicilio().getCalle())) {
            throw new FormatoDeCalleInvalidoException("El formato de la calle es inválido.");
        }
        if (!validarFormatoNumero(usuario.getDomicilio().getNumero())) {
            throw new FormatoDeNumeroInvalidoException("El formato del número es inválido.");
        }
        if (!validarFormatoCiudad(usuario.getDomicilio().getCiudad())) {
            throw new FormatoDeCiudadInvalidoException("El formato de la ciudad es inválido.");
        }
        if (!validarFormatoCodigoPostal(usuario.getDomicilio().getCodigoPostal())) {
            throw new FormatoDeCodigoPostalInvalidoException("El formato del código postal es inválido.");
        }
        if (!validarFormatoPiso(usuario.getDomicilio().getPiso())) {
            throw new FormatoDePisoInvalidoException("El formato del piso es inválido.");
        }
        if (!validarFormatoDepartamento(usuario.getDomicilio().getDepartamento())) {
            throw new FormatoDeDepartamentoInvalidoException("El formato del departamento es inválido.");
        }
        if (repositorioUsuario.buscarPorEmail(usuario.getEmail()) != null) {
            throw new EmailYaUsadoException("Ya existe un usuario registrado con ese email.");
        }
        if (repositorioUsuario.buscarPorNombreDeUsuario(usuario.getNombreDeUsuario()) != null) {
            throw new NombreDeUsuarioYaUsadoException("Ya existe un usuario registrado con ese nombre de usuario.");
        }
        if (repositorioUsuario.buscarPorTelefono(usuario.getTelefono()) != null) {
            throw new TelefonoYaUsadoException("Ya existe un usuario registrado con ese teléfono.");
        }

        repositorioUsuario.guardar(usuario);

    }

    public static boolean validarFormatoEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    public static boolean validarFormatoContrasenia(String contrasenia) {
        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            return false;
        }
        String contraseniaRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return contrasenia.matches(contraseniaRegex);
    }

    public static boolean validarFormatoNombreDeUsuario(String nombreDeUsuario) {
        if (nombreDeUsuario == null || nombreDeUsuario.trim().isEmpty()) {
            return false;
        }
        String nombreDeUsuarioRegex = "^[a-zA-Z0-9._]{3,30}$";
        return nombreDeUsuario.matches(nombreDeUsuarioRegex);
    }

    public static boolean validarFormatoNombreOApellido(String nombreOApellido) {
        String nombreOApellidoRegex = "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]{2,50}$";
        return nombreOApellido != null && !nombreOApellido.trim().isEmpty() && nombreOApellido.matches(nombreOApellidoRegex);
    }

    public static boolean validarFormatoTelefono(String telefono) {
        String telefonoRegex = "^[+]?[0-9]{8,15}$";
        return telefono != null && !telefono.trim().isEmpty() && telefono.replaceAll("[\\s-()]", "").matches(telefonoRegex);
    }

    public static boolean validarFormatoCalle(String calle) {
        String calleRegex = "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s0-9.]{3,100}$";
        return calle != null && !calle.trim().isEmpty() && calle.matches(calleRegex);
    }

    public static boolean validarFormatoNumero(String numero) {
        String numeroRegex = "^[0-9]{1,6}$";
        return numero != null && !numero.trim().isEmpty() && numero.matches(numeroRegex);
    }

    public static boolean validarFormatoCiudad(String ciudad) {
        String ciudadRegex = "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\s]{2,50}$";
        return ciudad != null && !ciudad.trim().isEmpty() && ciudad.matches(ciudadRegex);
    }

    public static boolean validarFormatoCodigoPostal(String codigoPostal) {
        String codigoPostalRegex = "^[0-9]{4,8}$";
        return codigoPostal != null && !codigoPostal.trim().isEmpty() && codigoPostal.matches(codigoPostalRegex);
    }

    public static boolean validarFormatoPiso(String piso) {
        if (piso == null || piso.trim().isEmpty()) {
            return true;
        }
        String pisoRegex = "^[0-9]{1,3}$";
        return piso.matches(pisoRegex);
    }

    public static boolean validarFormatoDepartamento(String departamento) {
        if (departamento == null || departamento.trim().isEmpty()) {
            return true;
        }
        String departamentoRegex = "^[a-zA-Z0-9]{1,10}$";
        return departamento.matches(departamentoRegex);
    }

}
