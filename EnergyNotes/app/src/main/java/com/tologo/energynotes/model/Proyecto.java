package com.tologo.energynotes.model;

public class Proyecto {

        private String usuario;
        private String nombreproyecto;
        private String fechavisita;

        public Proyecto() {
        }

    // Generamos el constructor de la clase
    public Proyecto(String usuario, String nombreproyecto, String fechavisita) {
            this.usuario = usuario;
            this.nombreproyecto = nombreproyecto;
            this.fechavisita = fechavisita;
    }

    // Generamos getters y setters

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getNombreproyecto() {
            return nombreproyecto;
        }

        public void setNombreproyecto(String nombreproyecto) {
            this.nombreproyecto = nombreproyecto;
        }

        public String getFechavisita() {
            return fechavisita;
        }

        public void setFechavisita(String fechavisita) {
            this.fechavisita = fechavisita;
        }

        @Override
        public String toString() {
            return nombreproyecto;
        }

}
