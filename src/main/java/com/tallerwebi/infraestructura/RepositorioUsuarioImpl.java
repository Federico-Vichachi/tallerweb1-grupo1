package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioUsuario;
import com.tallerwebi.dominio.Usuario;
import com.tallerwebi.presentacion.DatosEdicionPerfil;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuarioImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Usuario usuario) {
        sessionFactory.getCurrentSession().save(usuario);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public Usuario buscarPorNombreDeUsuario(String nombreDeUsuario) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class).add(Restrictions.eq("nombreDeUsuario", nombreDeUsuario)).uniqueResult();
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class).add(Restrictions.eq("email", email)).uniqueResult();
    }

    @Override
    public Usuario buscarPorTelefono(String telefono) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class).add(Restrictions.eq("telefono", telefono)).uniqueResult();
    }

    @Override
    public Usuario buscarPorEmailExcluyendoUsuario(String email, Long idUsuario) {
        return (Usuario) sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.ne("id", idUsuario))
                .uniqueResult();
    }

    @Override
    public Usuario buscarPorNombreDeUsuarioExcluyendoUsuario(String nombreDeUsuario, Long idUsuario) {
        return (Usuario) sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .add(Restrictions.eq("nombreDeUsuario", nombreDeUsuario))
                .add(Restrictions.ne("id", idUsuario))
                .uniqueResult();
    }

    @Override
    public Usuario buscarPorTelefonoExcluyendoUsuario(String telefono, Long idUsuario) {
        return (Usuario) sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .add(Restrictions.eq("telefono", telefono))
                .add(Restrictions.ne("id", idUsuario))
                .uniqueResult();
    }


    @Override
    public void eliminar(Usuario usuario) {
        sessionFactory.getCurrentSession().delete(usuario);
    }

    @Override
    public void guardarCambiosPerfil(DatosEdicionPerfil datosEdicionPerfil) {
        Usuario usuario = (Usuario) sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .add(Restrictions.eq("id", datosEdicionPerfil.getId()))
                .uniqueResult();

        if (usuario != null) {
            usuario.setNombre(datosEdicionPerfil.getNombre());
            usuario.setApellido(datosEdicionPerfil.getApellido());
            usuario.setNombreDeUsuario(datosEdicionPerfil.getNombreDeUsuario());
            usuario.setEmail(datosEdicionPerfil.getEmail());
            usuario.setTelefono(datosEdicionPerfil.getTelefono());

            if (usuario.getDomicilio() == null) {
                usuario.setDomicilio(new com.tallerwebi.dominio.Domicilio());
            }

            usuario.getDomicilio().setCalle(datosEdicionPerfil.getCalle());
            usuario.getDomicilio().setNumero(datosEdicionPerfil.getNumero());
            usuario.getDomicilio().setPiso(datosEdicionPerfil.getPiso());
            usuario.getDomicilio().setDepartamento(datosEdicionPerfil.getDepartamento());
            usuario.getDomicilio().setCiudad(datosEdicionPerfil.getCiudad());
            usuario.getDomicilio().setProvincia(datosEdicionPerfil.getProvincia());
            usuario.getDomicilio().setCodigoPostal(datosEdicionPerfil.getCodigoPostal());

            if (datosEdicionPerfil.getUrlFotoDePerfil() != null && !datosEdicionPerfil.getUrlFotoDePerfil().trim().isEmpty()) {
                usuario.setUrlFotoDePerfil(datosEdicionPerfil.getUrlFotoDePerfil());
            }

            sessionFactory.getCurrentSession().update(usuario);
        }
    }

}
