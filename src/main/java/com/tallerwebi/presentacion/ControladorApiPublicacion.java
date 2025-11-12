package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.ServicioPublicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.stream.Collectors;

// Archivo: src/main/java/com/tallerwebi/presentacion/ControladorApiPublicacion.java
@Controller
public class ControladorApiPublicacion {

    private final ServicioPublicacion servicioPublicacion;

    @Autowired
    public ControladorApiPublicacion(ServicioPublicacion servicioPublicacion) {
        this.servicioPublicacion = servicioPublicacion;
    }

    @RequestMapping(value = "/api/publicaciones-filtradas", method = RequestMethod.GET)
    @ResponseBody
    public List<PublicacionMapa> getPublicacionesFiltradas(
            @RequestParam("lat") Double lat,
            @RequestParam("lon") Double lon,
            @RequestParam(value = "radioKm", defaultValue = "2.0") Double radioKm,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String nombre)
    {
        List<Publicacion> publicacionesFiltradas = servicioPublicacion.buscarPublicacionesParaMapa(lat, lon, radioKm, categoria, nombre);

        return publicacionesFiltradas.stream()
                .map(PublicacionMapa::new)
                .collect(Collectors.toList());
    }
}