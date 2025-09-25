package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControladorFeed {

    @RequestMapping(path= "/feed", method = RequestMethod.GET)
    public ModelAndView irAFeed(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Integer provincia,
            @RequestParam(required = false) Integer localidad,
            @RequestParam(required = false) List<String> tags
    ){
        ModelMap model = new ModelMap();

        List<Publicacion> publicaciones = obtenerPublicacionesDePrueba();

        if(categoria!=null && !categoria.isEmpty()){
            List<Publicacion> publicacionesFiltradas = new ArrayList<>();
            for(Publicacion p : publicaciones){
                if(p.getTipo().equalsIgnoreCase(categoria)){
                    publicacionesFiltradas.add(p);
                }
            }
            publicaciones = publicacionesFiltradas;

        }


        model.put("publicaciones", publicaciones);
        model.put("categoriaFiltro",categoria);
        model.put("provinciaFiltro",provincia);
        model.put("localidadFiltro",localidad);
        model.put("tagsFiltro",tags);
        return new ModelAndView("feed", model);
    }
    private List<Publicacion> obtenerPublicacionesDePrueba() {
        return new ArrayList<>(Arrays.asList(
                new Publicacion("Illesca Dylan", "Hace 4 horas", "Adopcion", "/images/gatovagancia.webp", "Kitty", "3 meses", "Tucuman", "Europeo", "Luna es una gatita juguetona...", Arrays.asList("Vacunada", "Sociable")),
                new Publicacion("Juan Perez", "Hace 1 día", "Perdido", "/images/perro.webp", "Chiquita", "8 meses", "La Matanza", "Caniche", "Se perdió cerca del parque...", Arrays.asList("Activo")),
                new Publicacion("Maria Garcia", "Hace 2 días", "Encontrado", "/images/siames.webp", "Mia", "2 años", "Pinamar", "Siames", "Encontrada en la playa, muy dócil...", Arrays.asList("Tranquilo", "Sociable")),
                new Publicacion("Carlos Lopez", "Hace 3 días", "Recaudacion", "/images/hero.webp", "Rocky", "5 años", "Cordoba", "Mestizo", "Necesita operación urgente...", Arrays.asList("Castrado"))
        ));
    }
}




