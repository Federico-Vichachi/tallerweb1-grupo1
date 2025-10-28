package com.tallerwebi.dominio;

import com.tallerwebi.presentacion.DatosAdopcion;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PublicacionAdopcion extends Publicacion {

   private Integer edad;

   public PublicacionAdopcion() {}

   public PublicacionAdopcion(DatosAdopcion datosAdopcion) {
       super(datosAdopcion.getTitulo(), datosAdopcion.getDescripcionCorta(), datosAdopcion.getDescripcionDetallada(), datosAdopcion.getImagen(), datosAdopcion.getRaza(), datosAdopcion.getTamanio(), datosAdopcion.getUbicacion(), datosAdopcion.getTelefono(), datosAdopcion.getEmail());
       this.edad = datosAdopcion.getEdad();
   }

   @Override
    public int puntosPorCreacion(){
       return 15;
   }

}
