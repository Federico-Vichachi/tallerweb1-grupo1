package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.FormatoDeContraseniaInvalidoException;
import com.tallerwebi.dominio.excepcion.FormatoDeEmailInvalidoException;
import com.tallerwebi.presentacion.Roles;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro {

    @Override
    public Usuario registrarUsuario(String email, String contrasenia, Roles roles) {

        if (validarFormatoEmail(email)) {
            throw new FormatoDeEmailInvalidoException("El formato del email es inválido.");
        }

        if (validarFormatoContrasenia(contrasenia)) {
            throw new FormatoDeContraseniaInvalidoException("El formato de la contraseña es inválido.");
        }

        return new Usuario();
    }

    public static boolean validarFormatoEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && !email.matches(emailRegex);
    }

    public static boolean validarFormatoContrasenia(String contrasenia) {
        String contraseniaRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return contrasenia != null && !contrasenia.matches(contraseniaRegex);
    }

}
