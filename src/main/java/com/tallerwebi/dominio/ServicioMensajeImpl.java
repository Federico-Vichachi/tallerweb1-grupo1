package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ChatInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioMensajeImpl implements ServicioMensaje {

    private final RepositorioMensaje repositorioMensaje;
    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioMensajeImpl(RepositorioMensaje repositorioMensaje, RepositorioUsuario repositorioUsuario) {
        this.repositorioMensaje = repositorioMensaje;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void guardarMensaje(String contenido, String remitenteUsername, String destinatarioUsername) {
        Usuario remitente = repositorioUsuario.buscarPorNombreDeUsuario(remitenteUsername);
        Usuario destinatario = repositorioUsuario.buscarPorNombreDeUsuario(destinatarioUsername);

        if (remitente == null || destinatario == null) {
            return;
        }

        Mensaje mensaje = new Mensaje();
        mensaje.setContenido(contenido);
        mensaje.setRemitente(remitente);
        mensaje.setDestinatario(destinatario);

        repositorioMensaje.guardar(mensaje);
    }



    @Override
    public List<Mensaje> iniciarYObtenerConversacion(String usuarioRemitente, String usuarioDestinatario) {
        if (usuarioRemitente.equals(usuarioDestinatario)) {
            throw new ChatInvalidoException("No podes chatear con vos mismo");
        }

        Usuario usuario1 = repositorioUsuario.buscarPorNombreDeUsuario(usuarioRemitente);
        Usuario usuario2 = repositorioUsuario.buscarPorNombreDeUsuario(usuarioDestinatario);

        if (usuario1 == null || usuario2 == null) {
            throw new ChatInvalidoException("Uno de los usuarios no existe.");
        }

        return repositorioMensaje.obtenerMensajesEntre(usuario1, usuario2);
    }

    @Override
    public List<Usuario> obtenerConversaciones(String username) {
        Usuario usuario = repositorioUsuario.buscarPorNombreDeUsuario(username);
        if (usuario == null) {
            return new ArrayList<>();
        }
        return repositorioMensaje.obtenerUsuariosConConversacion(usuario);
    }

}