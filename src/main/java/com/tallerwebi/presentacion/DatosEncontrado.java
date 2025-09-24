package com.tallerwebi.presentacion;

public class DatosEncontrado {
        private String tituloEncontrado;
        private String descripcionCortaEncontrado;
        private String descripcionDetalladaEncontrado;
        private String imagenEncontrado;
        private String razaEncontrado;
        private Integer tamanioEncontrado;
        private String ubicacionEncontrado;
        private Integer telefonoEncontrado;
        private String emailEncontrado;


        public DatosEncontrado() {

        }

        public DatosEncontrado(String tituloEncontrado, String descripcionCortaEncontrado, String descripcionDetalladaEncontrado, String imagenEncontrado, String razaEncontrado, Integer tamanioEncontrado, String ubicacionEncontrado, Integer telefonoEncontrado, String emailEncontrado) {
            this.tituloEncontrado = tituloEncontrado;
            this.descripcionCortaEncontrado = descripcionCortaEncontrado;
            this.descripcionDetalladaEncontrado = descripcionDetalladaEncontrado;
            this.imagenEncontrado = imagenEncontrado;
            this.razaEncontrado = razaEncontrado;
            this.tamanioEncontrado = tamanioEncontrado;
            this.ubicacionEncontrado = ubicacionEncontrado;
            this.telefonoEncontrado = telefonoEncontrado;
            this.emailEncontrado = emailEncontrado;
        }

        public String getTituloEncontrado() {return tituloEncontrado;}
        public void setTituloEncontrado(String tituloEncontrado) {this.tituloEncontrado = tituloEncontrado;}

        public String getDescripcionCortaEncontrado() {return descripcionCortaEncontrado;}
        public void setDescripcionCortaEncontrado(String descripcionCortaEncontrado) {this.descripcionCortaEncontrado = descripcionCortaEncontrado;}

        public String getDescripcionDetalladaEncontrado() {return descripcionDetalladaEncontrado;}
        public void setDescripcionDetalladaEncontrado(String descripcionDetalladaEncontrado) {this.descripcionDetalladaEncontrado = descripcionDetalladaEncontrado;}

        public String getImagenEncontrado() {return imagenEncontrado;}
        public void setImagenEncontrado(String imagenEncontrado) {this.imagenEncontrado = imagenEncontrado;}

        public Integer getTamanioEncontrado() {return tamanioEncontrado;}
        public void setTamanioEncontrado(Integer tamanioEncontrado) {this.tamanioEncontrado = tamanioEncontrado;}

        public String getRazaEncontrado() {return razaEncontrado;}
        public void setRazaEncontrado(String razaEncontrado) {this.razaEncontrado = razaEncontrado;}

        public String getUbicacionEncontrado() {return ubicacionEncontrado;}
        public void setUbicacionEncontrado(String ubicacionEncontrado) {this.ubicacionEncontrado = ubicacionEncontrado;}


        public Integer getTelefonoEncontrado() {return telefonoEncontrado;}
        public void setTelefonoEncontrado( Integer telefonoEncontrado) {this.telefonoEncontrado = telefonoEncontrado;}

        public String getEmailEncontrado() {return emailEncontrado;}
        public void setEmailEncontrado(String emailEncontrado) {this.emailEncontrado = emailEncontrado;}
    }

