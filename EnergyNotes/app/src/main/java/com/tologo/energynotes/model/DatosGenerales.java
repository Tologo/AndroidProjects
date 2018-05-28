package com.tologo.energynotes.model;

public class DatosGenerales {

    private String edificio;
    private String direccion;
    private String localidad;
    private String codigopostal;
    private String referencia;
    private String fechaconstruccion;
    private String proyecto;

    public DatosGenerales() {
    }

    // Generamos el constructor de la clase
    public DatosGenerales(String edificio, String direccion, String localidad, String codigopostal, String referencia,
               String fechaconstruccion, String proyecto) {
        this.edificio = edificio;
        this.direccion = direccion;
        this.localidad = localidad;
        this.codigopostal = codigopostal;
        this.referencia = referencia;
        this.fechaconstruccion = fechaconstruccion;
        this.proyecto = proyecto;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getFechaconstruccion() {
        return fechaconstruccion;
    }

    public void setFechaconstruccion(String fechaconstruccion) {
        this.fechaconstruccion = fechaconstruccion;
    }
    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public String toString() {
        return "DatosGenerales{" +
                "edificio='" + edificio + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", codigopostal='" + codigopostal + '\'' +
                ", referencia='" + referencia + '\'' +
                ", fechaconstruccion='" + fechaconstruccion + '\'' +
                ", proyecto='" + proyecto + '\'' +
                '}';
    }

}
