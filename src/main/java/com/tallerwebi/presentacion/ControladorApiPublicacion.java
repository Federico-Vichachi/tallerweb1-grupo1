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

@Controller
public class ControladorApiPublicacion {

    private final ServicioPublicacion servicioPublicacion;

    @Autowired
    public ControladorApiPublicacion(ServicioPublicacion servicioPublicacion) {
        this.servicioPublicacion = servicioPublicacion;
    }

    @RequestMapping(value = "/api/publicaciones-cercanas", method = RequestMethod.GET)
    @ResponseBody
    public List<PublicacionMapa> getPublicacionesCercanas(
            @RequestParam("lat") Double lat,
            @RequestParam("lon") Double lon) {

        List<Publicacion> publicaciones = servicioPublicacion.buscarPublicacionesCercanas(lat, lon, 2.0);

        return publicaciones.stream()
                .map(PublicacionMapa::new)
                .collect(Collectors.toList());
    }
}