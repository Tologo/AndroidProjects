package com.tologo.energynotes.model;

public class Fachada {

    private String orientacion;
    private String longfachada;
    private String alturafachada;
    private String tipofachada;
    private String camaraaire;
    private String aislamientofachada;

    public Fachada() {
    }

    // Generamos el constructor de la clase
    public Fachada(String orientacion, String longfachada, String alturafachada, String tipofachada, String camaraaire,
               String aislamientofachada) {
        this.orientacion = orientacion;
        this.longfachada = longfachada;
        this.alturafachada = alturafachada;
        this.tipofachada = tipofachada;
        this.camaraaire = camaraaire;
        this.aislamientofachada = aislamientofachada;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public String getLongfachada() {
        return longfachada;
    }

    public void setLongfachada(String longfachada) {
        this.longfachada = longfachada;
    }

    public String getAlturafachada() {
        return alturafachada;
    }

    public void setAlturafachada(String alturafachada) {
        this.alturafachada = alturafachada;
    }

    public String getTipofachada() {
        return tipofachada;
    }

    public void setTipofachada(String tipofachada) {
        this.tipofachada = tipofachada;
    }

    public String getCamaraaire() {
        return camaraaire;
    }

    public void setCamaraaire(String camaraaire) {
        this.camaraaire = camaraaire;
    }

    public String getAislamientofachada() {
        return aislamientofachada;
    }

    public void setAislamientofachada(String aislamientofachada) {
        this.aislamientofachada = aislamientofachada;
    }

    @Override
    public String toString() {
        return "Fachada{" +
                "orientacion='" + orientacion + '\'' +
                ", longfachada='" + longfachada + '\'' +
                ", alturafachada='" + alturafachada + '\'' +
                ", tipofachada='" + tipofachada + '\'' +
                ", camaraaire='" + camaraaire + '\'' +
                ", aislamientofachada='" + aislamientofachada + '\'' +
                '}';
    }
}
