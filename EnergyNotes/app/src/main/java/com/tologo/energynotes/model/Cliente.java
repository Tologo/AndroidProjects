package com.tologo.energynotes.model;

public class Cliente {

    private String nombrecli;
    private String localidadcli;
    private String telefonocli;
    private String correocli;
    private String proyecto;

    public Cliente() {
    }

    // Generamos el constructor de la clase
    public Cliente(String nombrecli, String localidadcli, String telefonocli, String correocli, String proyecto) {
        this.nombrecli = nombrecli;
        this.localidadcli = localidadcli;
        this.telefonocli = telefonocli;
        this.correocli = correocli;
        this.proyecto = proyecto;
    }

    public String getNombrecli() {
        return nombrecli;
    }

    public void setNombrecli(String nombrecli) {
        this.nombrecli = nombrecli;
    }

    public String getLocalidadcli() {
        return localidadcli;
    }

    public void setLocalidadcli(String localidadcli) {
        this.localidadcli = localidadcli;
    }

    public String getTelefonocli() {
        return telefonocli;
    }

    public void setTelefonocli(String telefonocli) {
        this.telefonocli = telefonocli;
    }

    public String getCorreocli() {
        return correocli;
    }

    public void setCorreocli(String correocli) {
        this.correocli = correocli;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombrecli='" + nombrecli + '\'' +
                ", localidadcli='" + localidadcli + '\'' +
                ", telefonocli='" + telefonocli + '\'' +
                ", correocli='" + correocli + '\'' +
                '}';
    }
}
