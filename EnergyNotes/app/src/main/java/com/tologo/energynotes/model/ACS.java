package com.tologo.energynotes.model;

public class ACS {

    private String generadoracs;
    private String combustibleacs;
    private String potenciaacs;
    private String rendimientoacs;
    private String aislamientoacs;
    private String acumulacionacs;

    public ACS() {
    }

    // Generamos el constructor de la clase
    public ACS(String generadoracs, String combustibleacs, String potenciaacs, String rendimientoacs, String aislamientoacs,
               String acumulacionacs) {
        this.generadoracs = generadoracs;
        this.combustibleacs = combustibleacs;
        this.potenciaacs = potenciaacs;
        this.rendimientoacs = rendimientoacs;
        this.aislamientoacs = aislamientoacs;
        this.acumulacionacs = acumulacionacs;
    }

    public String getGeneradoracs() {
        return generadoracs;
    }

    public void setGeneradoracs(String generadoracs) {
        this.generadoracs = generadoracs;
    }

    public String getCombustibleacs() {
        return combustibleacs;
    }

    public void setCombustibleacs(String combustibleacs) {
        this.combustibleacs = combustibleacs;
    }

    public String getPotenciaacs() {
        return potenciaacs;
    }

    public void setPotenciaacs(String potenciaacs) {
        this.potenciaacs = potenciaacs;
    }

    public String getRendimientoacs() {
        return rendimientoacs;
    }

    public void setRendimientoacs(String rendimientoacs) {
        this.rendimientoacs = rendimientoacs;
    }

    public String getAislamientoacs() {
        return aislamientoacs;
    }

    public void setAislamientoacs(String aislamientoacs) {
        this.aislamientoacs = aislamientoacs;
    }

    public String getAcumulacionacs() {
        return acumulacionacs;
    }

    public void setAcumulacionacs(String acumulacionacs) {
        this.acumulacionacs = acumulacionacs;
    }

    @Override
    public String toString() {
        return "ACS{" +
                "generadoracs='" + generadoracs + '\'' +
                ", combustibleacs='" + combustibleacs + '\'' +
                ", potenciaacs='" + potenciaacs + '\'' +
                ", rendimientoacs='" + rendimientoacs + '\'' +
                ", aislamientoacs='" + aislamientoacs + '\'' +
                ", acumulacionacs='" + acumulacionacs + '\'' +
                '}';
    }
}

