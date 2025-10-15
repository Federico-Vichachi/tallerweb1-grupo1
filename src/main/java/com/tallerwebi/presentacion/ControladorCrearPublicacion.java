package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCrearPublicacion {

    ServicioCrearPublicacion servicioCrearPublicacion;

    @Autowired
    public ControladorCrearPublicacion(ServicioCrearPublicacion servicioCrearPublicacion) {
        this.servicioCrearPublicacion = servicioCrearPublicacion;
    }

    @RequestMapping(value = "/crear-publicacion", method =  RequestMethod.GET)
    public ModelAndView irCrearPublicacion() {
        ModelMap model = new ModelMap();
        model.put("datosAdopcion", new DatosAdopcion());
        model.put("datosRecaudacion", new DatosRecaudacion());
        model.put("datosSalud", new DatosSalud());
        model.put("datosPerdido", new DatosPerdido());
        model.put("datosEncontrado", new DatosEncontrado());
        return new ModelAndView("crear-publicacion", model);
    }

    @RequestMapping(value = "/publicacion-adopcion", method = RequestMethod.POST)
    public ModelAndView crearPublicacionAdopcion(@ModelAttribute DatosAdopcion datosAdopcion) {

        PublicacionAdopcion publicacionDeAdopcion = new PublicacionAdopcion();
        publicacionDeAdopcion.setTitulo(datosAdopcion.getTitulo());
        publicacionDeAdopcion.setDescripcionCorta(datosAdopcion.getDescripcionCorta());
        publicacionDeAdopcion.setDescripcionDetallada(datosAdopcion.getDescripcionDetallada());
        publicacionDeAdopcion.setImagen(datosAdopcion.getImagen());
        publicacionDeAdopcion.setRaza(datosAdopcion.getRaza());
        publicacionDeAdopcion.setTamanio(datosAdopcion.getTamanio());
        publicacionDeAdopcion.setUbicacion(datosAdopcion.getUbicacion());
        publicacionDeAdopcion.setTelefono(datosAdopcion.getTelefono());
        publicacionDeAdopcion.setEmail(datosAdopcion.getEmail());
        publicacionDeAdopcion.setEdad(datosAdopcion.getEdad());

        servicioCrearPublicacion.guardar(publicacionDeAdopcion);

        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion adopcion guardada correctamente");
        model.put("datosAdopcion", datosAdopcion);
        return new ModelAndView("publicacion-adopcion", model);
    }

    @RequestMapping(value = "/publicacion-recaudacion", method = RequestMethod.POST)
    public ModelAndView crearPublicacionRecaudacion(@ModelAttribute DatosRecaudacion datosRecaudacion) {

        PublicacionRecaudacion publicacionDeRecaudacion = new PublicacionRecaudacion();
        publicacionDeRecaudacion.setTitulo(datosRecaudacion.getTitulo());
        publicacionDeRecaudacion.setDescripcionCorta(datosRecaudacion.getDescripcionCorta());
        publicacionDeRecaudacion.setDescripcionDetallada(datosRecaudacion.getDescripcionDetallada());
        publicacionDeRecaudacion.setImagen(datosRecaudacion.getImagen());
        publicacionDeRecaudacion.setRaza(datosRecaudacion.getRaza());
        publicacionDeRecaudacion.setTamanio(datosRecaudacion.getTamanio());
        publicacionDeRecaudacion.setUbicacion(datosRecaudacion.getUbicacion());
        publicacionDeRecaudacion.setTelefono(datosRecaudacion.getTelefono());
        publicacionDeRecaudacion.setEmail(datosRecaudacion.getEmail());
        publicacionDeRecaudacion.setEdad(datosRecaudacion.getEdad());
        publicacionDeRecaudacion.setMeta(datosRecaudacion.getMeta());
        publicacionDeRecaudacion.setCbu(datosRecaudacion.getCbu());
        publicacionDeRecaudacion.setMetodoPreferido(datosRecaudacion.getMetodoPreferido());

        servicioCrearPublicacion.guardar(publicacionDeRecaudacion);

        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion recaudacion creada correctamente");
        model.put("datosRecaudacion", datosRecaudacion);
        return new ModelAndView("publicacion-recaudacion", model);
    }

    @RequestMapping(value = "/publicacion-salud", method = RequestMethod.POST)
    public ModelAndView crearPublicacionSalud(@ModelAttribute DatosSalud datosSalud) {
        PublicacionSalud publicacionDeSalud = new PublicacionSalud();
        publicacionDeSalud.setTitulo(datosSalud.getTitulo());
        publicacionDeSalud.setDescripcionCorta(datosSalud.getDescripcionCorta());
        publicacionDeSalud.setDescripcionDetallada(datosSalud.getDescripcionDetallada());
        publicacionDeSalud.setImagen(datosSalud.getImagen());
        publicacionDeSalud.setRaza(datosSalud.getRaza());
        publicacionDeSalud.setTamanio(datosSalud.getTamanio());
        publicacionDeSalud.setUbicacion(datosSalud.getUbicacion());
        publicacionDeSalud.setTelefono(datosSalud.getTelefono());
        publicacionDeSalud.setEmail(datosSalud.getEmail());
        publicacionDeSalud.setEdad(datosSalud.getEdad());
        publicacionDeSalud.setSintomasPrincipales(datosSalud.getSintomasPrincipales());
        publicacionDeSalud.setDiagnostico(datosSalud.getDiagnostico());
        publicacionDeSalud.setNivelUrgencia(datosSalud.getNivelUrgencia());

        servicioCrearPublicacion.guardar(publicacionDeSalud);

        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion salud creada correctamente");
        model.put("datosSalud", datosSalud);
        return new ModelAndView("publicacion-salud", model);
    }

    @RequestMapping(value = "/publicacion-perdido", method = RequestMethod.POST)
    public ModelAndView crearPublicacionPerdido(@ModelAttribute DatosPerdido datosPerdido) {

        PublicacionPerdido publicacionDePerdido = new PublicacionPerdido();
        publicacionDePerdido.setTitulo(datosPerdido.getTitulo());
        publicacionDePerdido.setDescripcionCorta(datosPerdido.getDescripcionCorta());
        publicacionDePerdido.setDescripcionDetallada(datosPerdido.getDescripcionDetallada());
        publicacionDePerdido.setImagen(datosPerdido.getImagen());
        publicacionDePerdido.setRaza(datosPerdido.getRaza());
        publicacionDePerdido.setTamanio(datosPerdido.getTamanio());
        publicacionDePerdido.setUbicacion(datosPerdido.getUbicacion());
        publicacionDePerdido.setTelefono(datosPerdido.getTelefono());
        publicacionDePerdido.setEmail(datosPerdido.getEmail());
        publicacionDePerdido.setFechaDesaparicion(datosPerdido.getFechaDesaparicion());
        publicacionDePerdido.setHoraDesaparicion(datosPerdido.getHoraDesaparicion());
        publicacionDePerdido.setLlevaCollar(datosPerdido.getLlevaCollar());
        publicacionDePerdido.setRecompensa(datosPerdido.getRecompensa());

        servicioCrearPublicacion.guardar(publicacionDePerdido);

        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion perdido creada correctamente");
        model.put("datosPerdido", datosPerdido);
        return new ModelAndView("publicacion-perdido", model);
    }

    @RequestMapping(value = "/publicacion-encontrado", method = RequestMethod.POST)
    public ModelAndView crearPublicacionEncontrado(@ModelAttribute DatosEncontrado datosEncontrado) {

        PublicacionEncontrado publicacionDeEncontrado = new PublicacionEncontrado();
        publicacionDeEncontrado.setTitulo(datosEncontrado.getTitulo());
        publicacionDeEncontrado.setDescripcionCorta(datosEncontrado.getDescripcionCorta());
        publicacionDeEncontrado.setDescripcionDetallada(datosEncontrado.getDescripcionDetallada());
        publicacionDeEncontrado.setImagen(datosEncontrado.getImagen());
        publicacionDeEncontrado.setRaza(datosEncontrado.getRaza());
        publicacionDeEncontrado.setTamanio(datosEncontrado.getTamanio());
        publicacionDeEncontrado.setUbicacion(datosEncontrado.getUbicacion());
        publicacionDeEncontrado.setTelefono(datosEncontrado.getTelefono());
        publicacionDeEncontrado.setEmail(datosEncontrado.getEmail());

        servicioCrearPublicacion.guardar(publicacionDeEncontrado);

        ModelMap model = new ModelMap();
        model.put("mensaje", "Publicacion encontrado creada correctamente");
        model.put("datosEncontrado", datosEncontrado);
        return new ModelAndView("publicacion-encontrado", model);
    }
}
