package infraestructura;

import com.tallerwebi.config.WebSocketConfig;
import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class, WebSocketConfig.class})
public class RepositorioUsuarioTest {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Transactional
    @Rollback
    public void dadoQueExisteUnUsuario_CuandoSeGuarda_EntoncesSePuedeBuscarPorId() {
        Usuario usuario = dadoQueExisteUnUsuario("usuario1", "test@gmail.com", "1234567890");
        cuandoSeGuarda(usuario);
        entoncesSePuedeBuscarPorId(usuario.getId(), usuario);
    }

    @Test
    @Transactional
    @Rollback
    public void dadoQueExisteUnUsuario_CuandoSeGuarda_EntoncesSePuedeBuscarPorNombreDeUsuario() {
        Usuario usuario = dadoQueExisteUnUsuario("usuario1", "test@gmail.com", "1234567890");
        cuandoSeGuarda(usuario);
        entoncesSePuedeBuscarPorNombreDeUsuario(usuario.getNombreDeUsuario(), usuario);
    }

    @Test
    @Transactional
    @Rollback
    public void dadoQueExisteUnUsuario_CuandoSeGuarda_EntoncesSePuedeBuscarPorEmail() {
        Usuario usuario = dadoQueExisteUnUsuario("usuario1", "test@gmail.com", "1234567890");
        cuandoSeGuarda(usuario);
        entoncesSePuedeBuscarPorEmail(usuario.getEmail(), usuario);
    }

    @Test
    @Transactional
    @Rollback
    public void dadoQueExisteUnUsuario_CuandoSeGuarda_EntoncesSePuedeBuscarPorTelefono() {
        Usuario usuario = dadoQueExisteUnUsuario("usuario1", "test@gmail.com", "1234567890");
        cuandoSeGuarda(usuario);
        entoncesSePuedeBuscarPorTelefono(usuario.getTelefono(), usuario);
    }

    @Test
    @Transactional
    @Rollback
    public void dadoQueExisteUnUsuario_CuandoSeGuarda_EntoncesSePuedeEliminar() {
        Usuario usuario = dadoQueExisteUnUsuario("usuario1", "test@gmail.com", "1234567890");
        cuandoSeGuarda(usuario);
        entoncesSePuedeEliminar(usuario);
    }

    private Usuario dadoQueExisteUnUsuario(String nombreDeUsuario, String email, String telefono) {
        Usuario usuario = new Usuario();
        usuario.setNombreDeUsuario(nombreDeUsuario);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        return usuario;
    }

    private void cuandoSeGuarda(Usuario usuario) {
        repositorioUsuario.guardar(usuario);
    }

    private  void entoncesSePuedeBuscarPorId(Long id, Usuario usuario) {
        Usuario usuarioBuscado = repositorioUsuario.buscarPorId(id);
        validarIgualdadUsuarios(usuarioBuscado, usuario);
    }

    private void entoncesSePuedeBuscarPorNombreDeUsuario(String nombreDeUsuario, Usuario usuario) {
        Usuario usuarioBuscado = repositorioUsuario.buscarPorNombreDeUsuario(nombreDeUsuario);
        validarIgualdadUsuarios(usuarioBuscado, usuario);
    }

    private void entoncesSePuedeBuscarPorEmail(String email, Usuario usuario) {
        Usuario usuarioBuscado = repositorioUsuario.buscarPorEmail(email);
        validarIgualdadUsuarios(usuarioBuscado, usuario);
    }

    private void entoncesSePuedeBuscarPorTelefono(String telefono, Usuario usuario) {
        Usuario usuarioBuscado = repositorioUsuario.buscarPorTelefono(telefono);
        validarIgualdadUsuarios(usuarioBuscado, usuario);
    }

    private void validarIgualdadUsuarios(Usuario usuario1, Usuario usuario2) {
        assertThat(usuario1.getId(), equalTo(usuario2.getId()));
        assertThat(usuario1.getNombreDeUsuario(), equalTo(usuario2.getNombreDeUsuario()));
        assertThat(usuario1.getEmail(), equalTo(usuario2.getEmail()));
        assertThat(usuario1.getTelefono(), equalTo(usuario2.getTelefono()));
    }

    private void entoncesSePuedeEliminar(Usuario usuario) {
        repositorioUsuario.eliminar(usuario);
        Usuario usuarioBuscado = repositorioUsuario.buscarPorId(usuario.getId());
        assertThat(usuarioBuscado, equalTo(null));
    }


}
