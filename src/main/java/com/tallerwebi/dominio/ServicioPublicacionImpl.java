// src/main/java/com/tallerwebi/dominio/ServicioPublicacionImpl.java
package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("servicioPublicacion")
public class ServicioPublicacionImpl implements ServicioPublicacion {

    // Por ahora, usamos la lista de prueba. En el futuro, esto usaría un Repositorio.
    private List<Publicacion> publicacionesMock() {
        return new ArrayList<>(Arrays.asList(
                new Publicacion("Illesca Dylan", "Hace 4 horas", "Adopcion", "/images/gatovagancia.webp", "Kitty", "3 meses", "Tucuman", "Europeo", "Luna es una gatita juguetona...", Arrays.asList("Vacunada", "Sociable")),
                new Publicacion("Juan Perez", "Hace 1 día", "Perdido", "/images/perro.webp", "Chiquita", "8 meses", "La Matanza", "Caniche", "Se perdió cerca del parque...", Arrays.asList("Activo")),
                new Publicacion("Maria Garcia", "Hace 2 días", "Encontrado", "/images/siames.webp", "Mia", "2 años", "Pinamar", "Siames", "Encontrada en la playa, muy dócil...", Arrays.asList("Tranquilo", "Sociable")),
                new Publicacion("Carlos Lopez", "Hace 3 días", "Recaudacion", "/images/hero.webp", "Rocky", "5 años", "Cordoba", "Mestizo", "Necesita operación urgente...", Arrays.asList("Castrado"))
        ));
    }

    @Override
    public List<Publicacion> obtenerTodasLasPublicaciones() {
        return publicacionesMock();
    }

    @Override
    public List<Publicacion> buscarPublicacionesPorCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            return obtenerTodasLasPublicaciones();
        }
        return publicacionesMock().stream()
                .filter(p -> p.getTipo().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }
}