package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Publicacion;
import com.tallerwebi.dominio.PublicacionEncontrado;
import com.tallerwebi.dominio.PublicacionPerdido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PublicacionMapa {

    private Long id;
    private String titulo;
    private String tipo;
    private Double latitud;
    private Double longitud;
    private String imagen;

    public PublicacionMapa(Publicacion publicacion){
        this.id= publicacion.getId();
        this.titulo=publicacion.getTitulo();
        this.latitud = publicacion.getLatitud();
        this.longitud= publicacion.getLongitud();
        this.imagen = publicacion.getImagen();

        if(publicacion instanceof PublicacionPerdido){
            this.tipo = "Perdido";
        }else if(publicacion instanceof PublicacionEncontrado){
            this.tipo = "Encontrado";
        }else {
            this.tipo = "Otro";
        }
    }




}
