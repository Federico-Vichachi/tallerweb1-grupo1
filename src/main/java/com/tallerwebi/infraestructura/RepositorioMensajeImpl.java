package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Mensaje;
import com.tallerwebi.dominio.RepositorioMensaje;
import com.tallerwebi.dominio.Usuario;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("repositorioMensaje")
public class RepositorioMensajeImpl implements RepositorioMensaje {

    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioMensajeImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Mensaje mensaje) {
        sessionFactory.getCurrentSession().save(mensaje);
    }

    @Override
    public List<Mensaje> obtenerMensajesEntre(Usuario usuario1, Usuario usuario2) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Mensaje.class);
        criteria.add(Restrictions.or(
                Restrictions.and(Restrictions.eq("remitente", usuario1), Restrictions.eq("destinatario", usuario2)),
                Restrictions.and(Restrictions.eq("remitente", usuario2), Restrictions.eq("destinatario", usuario1))
        ));
        criteria.addOrder(Order.asc("timestamp"));
        return (List<Mensaje>) criteria.list();
    }

    @Override
    public List<Usuario> obtenerUsuariosConConversacion(Usuario usuario) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Mensaje.class);

        criteria.add(Restrictions.or(
                Restrictions.eq("remitente", usuario),
                Restrictions.eq("destinatario", usuario)
        ));

        List<Mensaje> mensajes = criteria.list();

        Set<Usuario> usuariosConConversacion = new HashSet<>();
        for (Mensaje mensaje : mensajes) {
            if (!mensaje.getRemitente().equals(usuario)) {
                usuariosConConversacion.add(mensaje.getRemitente());
            }
            if (!mensaje.getDestinatario().equals(usuario)) {
                usuariosConConversacion.add(mensaje.getDestinatario());
            }
        }

        return new ArrayList<>(usuariosConConversacion);
    }
}