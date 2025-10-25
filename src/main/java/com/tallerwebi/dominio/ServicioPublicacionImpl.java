package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.CategoriaInvalidaException;
import com.tallerwebi.dominio.excepcion.ValidacionPublicacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

import static com.tallerwebi.dominio.ExpresionesRegularesParaLaValidacionDeDatosDePublicacion.*;

@Service
@Transactional
public class ServicioPublicacionImpl implements ServicioPublicacion {

    private final RepositorioPublicacion repositorioPublicacion;

    @Autowired
    public ServicioPublicacionImpl(RepositorioPublicacion repositorioPublicacion) {
        this.repositorioPublicacion = repositorioPublicacion;
    }

    @Override
    public Publicacion guardar(Publicacion publicacion) {
        verificarFormatoDeLosDatosDePublicacion(publicacion);
        return repositorioPublicacion.guardar(publicacion);
    }


    private void verificarFormatoDeLosDatosDePublicacion(Publicacion publicacion) {
        if (publicacion.getTitulo() == null || !publicacion.getTitulo().matches(FORMATO_TITULO)) {
            throw new ValidacionPublicacionException("El título no cumple con el formato requerido (3-255 caracteres).");
        }

        if (publicacion.getDescripcionCorta() != null && !publicacion.getDescripcionCorta().matches(FORMATO_DESCRIPCION_CORTA)) {
            throw new ValidacionPublicacionException("La descripción corta no cumple con el formato requerido (1-100 caracteres).");
        }

        if (publicacion.getDescripcionDetallada() != null && !publicacion.getDescripcionDetallada().matches(FORMATO_DESCRIPCION_DETALLADA)) {
            throw new ValidacionPublicacionException("La descripción detallada no cumple con el formato requerido (1-255 caracteres).");
        }

        if (publicacion.getRaza() != null && !publicacion.getRaza().matches(FORMATO_RAZA_UBICACION)) {
            throw new ValidacionPublicacionException("La raza es inválida.");
        }

        if (publicacion.getImagen() != null && !publicacion.getImagen().matches(FORMATO_IMAGEN)) {
            throw new ValidacionPublicacionException("La imagen es valida");
        }

        if (publicacion.getUbicacion() != null && !publicacion.getUbicacion().matches(FORMATO_RAZA_UBICACION)) {
            throw new ValidacionPublicacionException("La ubicación es inválida.");
        }

        if (publicacion.getTelefono() != null && !publicacion.getTelefono().matches(FORMATO_TELEFONO)) {
            throw new ValidacionPublicacionException("El teléfono es inválido.");
        }

        if (publicacion.getEmail() != null && !publicacion.getEmail().matches(FORMATO_EMAIL)) {
            throw new ValidacionPublicacionException("El email es inválido.");
        }

        //ADOPCION.
        if (publicacion instanceof PublicacionAdopcion) {
            PublicacionAdopcion adopcion = (PublicacionAdopcion) publicacion;

            if (adopcion.getEdad() == null || adopcion.getEdad() < 0) {
                throw new ValidacionPublicacionException("La edad del animal es obligatoria y no puede ser negativa.");
            }
        }

        //RECAUDACION.
        if (publicacion instanceof PublicacionRecaudacion) {
            PublicacionRecaudacion recaudacion = (PublicacionRecaudacion) publicacion;

           //METODO PREFERIDO.

            if (recaudacion.getEdad() == null || recaudacion.getEdad() < 0) {
                throw new ValidacionPublicacionException("La edad del animal es obligatoria y no puede ser negativa.");
            }

            if (recaudacion.getCbu() == null || !recaudacion.getCbu().matches(FORMATO_CBU)) {
                throw new ValidacionPublicacionException("El CBU debe tener 22 dígitos numéricos.");
            }
        }

        //PERDIDO.
        if (publicacion instanceof PublicacionPerdido) {
            PublicacionPerdido perdido = (PublicacionPerdido) publicacion;
            if (perdido.getFechaDesaparicion() == null || !perdido.getFechaDesaparicion().matches(ExpresionesRegularesParaLaValidacionDeDatosDePublicacion.FORMATO_FECHA))
                throw new ValidacionPublicacionException("La fecha de desaparición no es válida.");
            if (perdido.getHoraDesaparicion() == null || !perdido.getHoraDesaparicion().matches(ExpresionesRegularesParaLaValidacionDeDatosDePublicacion.FORMATO_HORA))
                throw new ValidacionPublicacionException("La hora de desaparición no es válida.");
        }

        //SALUD.
        if (publicacion instanceof PublicacionSalud) {
            PublicacionSalud salud = (PublicacionSalud) publicacion;
            if (salud.getNivelUrgencia() == null || salud.getNivelUrgencia().trim().isEmpty())
                throw new ValidacionPublicacionException("Debe indicar el nivel de urgencia.");
        }
    }

    @Override
    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return repositorioPublicacion.buscarTodas();
    }

    @Override
    public List<Publicacion> buscarPublicacionesPorCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            return obtenerTodasLasPublicaciones();
        }

        switch (categoria.toUpperCase()) {
            case "ADOPCION":
                return (List<Publicacion>) (List<?>) repositorioPublicacion.buscarPorTipo(PublicacionAdopcion.class);
            case "PERDIDO":
                return (List<Publicacion>) (List<?>) repositorioPublicacion.buscarPorTipo(PublicacionPerdido.class);
            case "ENCONTRADO":
                return (List<Publicacion>) (List<?>) repositorioPublicacion.buscarPorTipo(PublicacionEncontrado.class);
            case "RECAUDACION":
                return (List<Publicacion>) (List<?>) repositorioPublicacion.buscarPorTipo(PublicacionRecaudacion.class);
            case "SALUD":
                return (List<Publicacion>) (List<?>) repositorioPublicacion.buscarPorTipo(PublicacionSalud.class);
            default:
                throw new CategoriaInvalidaException("La categoría '" + categoria + "' no es válida.");
        }
    }
}