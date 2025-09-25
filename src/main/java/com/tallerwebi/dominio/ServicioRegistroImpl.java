package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ContraseniaNoContieneNumeroException;
import com.tallerwebi.presentacion.Roles;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro {

    @Override
    public Usuario registrar(String email, String contrasenia, String repeticionDeContrasenia, Roles roles) {

        if (!contieneUnNumero(contrasenia)) {
            throw new ContraseniaNoContieneNumeroException();
        }

        return new Usuario();
    }

    public static boolean contieneUnNumero(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

}
