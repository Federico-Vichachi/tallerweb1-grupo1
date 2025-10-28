package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosEncontrado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PublicacionEncontrado extends Publicacion {

    public PublicacionEncontrado() {}

    public PublicacionEncontrado(DatosEncontrado datosEncontrado) {
        super(datosEncontrado.getTitulo(), datosEncontrado.getDescripcionCorta(), datosEncontrado.getDescripcionDetallada(), datosEncontrado.getImagen(), datosEncontrado.getRaza(), datosEncontrado.getTamanio(), datosEncontrado.getUbicacion(), datosEncontrado.getTelefono(), datosEncontrado.getEmail());
    }

    @Override
    public int puntosPorCreacion() {
        return 10;
    }

}
