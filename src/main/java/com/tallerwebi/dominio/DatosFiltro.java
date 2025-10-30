package com.tallerwebi.dominio;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
public class DatosFiltro {

   private String categoria;
   private String nombre;
   private Provincias provincia;
    private String localidad;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DatosFiltro that = (DatosFiltro) o;
        return Objects.equals(categoria, that.categoria) && Objects.equals(nombre, that.nombre) && provincia == that.provincia && Objects.equals(localidad, that.localidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoria, nombre, provincia, localidad);
    }



}
