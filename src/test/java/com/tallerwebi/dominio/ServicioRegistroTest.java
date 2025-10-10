package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.FormatoDeContraseniaInvalidoException;
import com.tallerwebi.dominio.excepcion.FormatoDeEmailInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ServicioRegistroTest {

    @Mock
    RepositorioUsuario repositorioUsuario;

    ServicioRegistro servicioRegistro;

    @BeforeEach
    public void init() {
        servicioRegistro = new ServicioRegistroImpl(repositorioUsuario);
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConDatosValidos_EntoncesElRegistroEsExitoso() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertDoesNotThrow(() -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConEmailInvalido_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeEmailInvalidoException.class, () -> {
            cuandoSeRegistra("email-invalido", "Juan", "Perez", "juanperez", "1234567890",
                    "Contrasenia@2025", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    @Test
    public void dadoQueNoExisteUnUsuarioConEseEmail_CuandoSeRegistraConContraseniaInvalida_EntoncesElRegistroFalla() {
        dadoQueNoExisteUnUsuarioConEseEmail();

        assertThrows(FormatoDeContraseniaInvalidoException.class, () -> {
            cuandoSeRegistra("test@gmail.com", "Juan", "Perez", "juanperez", "1234567890",
                    "contrasenia-invalida", Roles.USUARIO_COMUN,
                    "Calle Falsa", "123", "Springfield", Provincias.BUENOS_AIRES,"12345",
                    "1", "A");
        });
    }

    private void dadoQueNoExisteUnUsuarioConEseEmail() {
    }

    private void cuandoSeRegistra(String email, String nombre, String apellido, String nombreDeUsuario, String telefono, String contrasenia,
                                          Roles rol, String calle, String numero, String ciudad, Provincias provincia, String codigoPostal,
                                          String piso, String departamento) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setNombreDeUsuario(nombreDeUsuario);
        usuario.setTelefono(telefono);
        usuario.setContrasenia(contrasenia);
        usuario.setRol(rol);
        usuario.setDomicilio(new Domicilio());
        usuario.getDomicilio().setCalle(calle);
        usuario.getDomicilio().setNumero(numero);
        usuario.getDomicilio().setCiudad(ciudad);
        usuario.getDomicilio().setProvincia(provincia);
        usuario.getDomicilio().setCodigoPostal(codigoPostal);
        usuario.getDomicilio().setDepartamento(departamento);
        usuario.getDomicilio().setPiso(piso);

        servicioRegistro.registrarUsuario(usuario);
    }
}
