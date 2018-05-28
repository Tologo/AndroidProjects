package com.tologo.energynotes.model;

public class Hueco {

    private String cerramiento;
    private String longhueco;
    private String alturahueco;
    private String multiplicador;
    private String tipovidrio;
    private String tipomarco;
    private String permeabilidad;
    private String color;
    private String voladizo;
    private String retranqueo;
    private String toldos;

    public Hueco() {
    }

    // Generamos el constructor de la clase
    public Hueco(String cerramiento, String longhueco, String alturahueco, String multiplicador, String tipovidrio,
               String tipomarco, String permeabilidad, String color, String voladizo, String retranqueo, String toldos) {
        this.cerramiento = cerramiento;
        this.longhueco = longhueco;
        this.alturahueco = alturahueco;
        this.multiplicador = multiplicador;
        this.tipovidrio = tipovidrio;
        this.tipomarco = tipomarco;
        this.permeabilidad = permeabilidad;
        this.color = color;
        this.voladizo = voladizo;
        this.retranqueo = retranqueo;
        this.toldos = toldos;
    }

    public String getCerramiento() {
        return cerramiento;
    }

    public void setCerramiento(String cerramiento) {
        this.cerramiento = cerramiento;
    }

    public String getLonghueco() {
        return longhueco;
    }

    public void setLonghueco(String longhueco) {
        this.longhueco = longhueco;
    }

    public String getAlturahueco() {
        return alturahueco;
    }

    public void setAlturahueco(String alturahueco) {
        this.alturahueco = alturahueco;
    }

    public String getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(String multiplicador) {
        this.multiplicador = multiplicador;
    }

    public String getTipovidrio() {
        return tipovidrio;
    }

    public void setTipovidrio(String tipovidrio) {
        this.tipovidrio = tipovidrio;
    }

    public String getTipomarco() {
        return tipomarco;
    }

    public void setTipomarco(String tipomarco) {
        this.tipomarco = tipomarco;
    }

    public String getPermeabilidad() {
        return permeabilidad;
    }

    public void setPermeabilidad(String permeabilidad) {
        this.permeabilidad = permeabilidad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVoladizo() {
        return voladizo;
    }

    public void setVoladizo(String voladizo) {
        this.voladizo = voladizo;
    }

    public String getRetranqueo() {
        return retranqueo;
    }

    public void setRetranqueo(String retranqueo) {
        this.retranqueo = retranqueo;
    }

    public String getToldos() {
        return toldos;
    }

    public void setToldos(String toldos) {
        this.toldos = toldos;
    }

    @Override
    public String toString() {
        return "Hueco{" +
                "cerramiento='" + cerramiento + '\'' +
                ", longhueco='" + longhueco + '\'' +
                ", alturahueco='" + alturahueco + '\'' +
                ", multiplicador='" + multiplicador + '\'' +
                ", tipovidrio='" + tipovidrio + '\'' +
                ", tipomarco='" + tipomarco + '\'' +
                ", permeabilidad='" + permeabilidad + '\'' +
                ", color='" + color + '\'' +
                ", voladizo='" + voladizo + '\'' +
                ", retranqueo='" + retranqueo + '\'' +
                ", toldos='" + toldos + '\'' +
                '}';
    }
}