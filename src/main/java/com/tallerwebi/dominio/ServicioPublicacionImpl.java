package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.CategoriaInvalidaException;
import com.tallerwebi.dominio.excepcion.ValidacionPublicacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

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

        if(publicacion instanceof PublicacionPerdido || publicacion instanceof PublicacionEncontrado){
            if(publicacion.getLatitud()==null){
                publicacion.setLatitud(-34.60 + (Math.random()-0.5)*0.1);
                publicacion.setLongitud(-34.60 + (Math.random()-0.5)*0.1);
            }
        }

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
            throw new ValidacionPublicacionException("La imagen es valida"); // No deberia ser invalida?
        }

        if (publicacion.getLocalidad() == null || publicacion.getLocalidad().trim().isEmpty() || !publicacion.getLocalidad().matches(FORMATO_RAZA_UBICACION)) {
            throw new ValidacionPublicacionException("La localidad es inválida o está vacía.");
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

    @Override
    public List<Publicacion> buscarPorNombre(String nombre){
        return repositorioPublicacion.buscarPorNombre(nombre);
    }

    @Override
    public List<Publicacion> buscarPorProvincia(Provincias provincia){
        return repositorioPublicacion.buscarPorProvincia(provincia);
    }

    @Override
    public List<Publicacion> buscarPorLocalidad(String localidad){
        return repositorioPublicacion.buscarPorLocalidad(localidad);
    }


    @Override
    public List<Publicacion> buscarPublicacionesCercanas(Double latitudUsuario, Double longitudUsuario, Double radioKm) {
        if (latitudUsuario == null || longitudUsuario == null || radioKm == null) {
            return new ArrayList<>();
        }

        List<Class<? extends Publicacion>> tiposConCoordenadas = Arrays.asList(PublicacionPerdido.class, PublicacionEncontrado.class);
        List<Publicacion> candidatas = repositorioPublicacion.buscarPorTipos(tiposConCoordenadas);

        List<Publicacion> resultado = new ArrayList<>();

        for (Publicacion publicacion : candidatas) {
            if (publicacion.getLatitud() != null && publicacion.getLongitud() != null) {
                double distancia = calcularDistancia(latitudUsuario, longitudUsuario, publicacion.getLatitud(), publicacion.getLongitud());

                if (distancia <= radioKm) {
                    resultado.add(publicacion);
                }
            }
        }
        return resultado;
    }

    private double calcularDistancia(Double latitud1, Double longitud1, Double latitud2, Double longitud2) {

        final double RADIO_TIERRA_KM = 6371;
        double dLat = Math.toRadians(latitud2 - latitud1);
        double dLon = Math.toRadians(longitud2 - longitud1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(latitud1)) * Math.cos(Math.toRadians(latitud2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RADIO_TIERRA_KM * c;
    }

    @Override
    public List<Publicacion> buscarPublicacionesConFiltros(DatosFiltro datosFiltro) {
        Set<Publicacion> resultadosUnicos = new HashSet<>();
        boolean seAplicoAlgunFiltro = false;

        if (datosFiltro != null) {
            if (datosFiltro.getCategoria() != null && !datosFiltro.getCategoria().trim().isEmpty()) {
                resultadosUnicos.addAll(this.buscarPublicacionesPorCategoria(datosFiltro.getCategoria()));
                seAplicoAlgunFiltro = true;
            }

            if (datosFiltro.getNombre() != null && !datosFiltro.getNombre().trim().isEmpty()) {
                resultadosUnicos.addAll(this.buscarPorNombre(datosFiltro.getNombre()));
                seAplicoAlgunFiltro = true;
            }

            if (datosFiltro.getProvincia() != null) {
                resultadosUnicos.addAll(this.buscarPorProvincia(datosFiltro.getProvincia()));
                seAplicoAlgunFiltro = true;
            }

            if (datosFiltro.getLocalidad() != null && !datosFiltro.getLocalidad().trim().isEmpty()) {
                resultadosUnicos.addAll(this.buscarPorLocalidad(datosFiltro.getLocalidad()));
                seAplicoAlgunFiltro = true;
            }
        }
        if (!seAplicoAlgunFiltro) {
            return this.obtenerTodasLasPublicaciones();
        }

        return resultadosUnicos.stream()
                .sorted((p1, p2) -> p2.getId().compareTo(p1.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void actualizar(Publicacion publicacion) {
        repositorioPublicacion.actualizar(publicacion);
    }

}