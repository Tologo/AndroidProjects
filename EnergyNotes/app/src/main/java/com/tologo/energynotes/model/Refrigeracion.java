package com.tologo.energynotes.model;

public class Refrigeracion {

    private String generadorfrio;
    private String combustiblefrio;
    private String antiguedadfrio;
    private String rendimientofrio;
    private String rendimientocalor;

    public Refrigeracion() {
    }

    // Generamos el constructor de la clase
    public Refrigeracion(String generadorfrio, String combustiblefrio, String antiguedadfrio,
                         String rendimientofrio, String rendimientocalor) {
        this.generadorfrio = generadorfrio;
        this.combustiblefrio = combustiblefrio;
        this.antiguedadfrio = antiguedadfrio;
        this.rendimientofrio = rendimientofrio;
        this.rendimientocalor = rendimientocalor;
    }

    public String getGeneradorfrio() {
        return generadorfrio;
    }

    public void setGeneradorfrio(String generadorfrio) {
        this.generadorfrio = generadorfrio;
    }

    public String getCombustiblefrio() {
        return combustiblefrio;
    }

    public void setCombustiblefrio(String combustiblefrio) {
        this.combustiblefrio = combustiblefrio;
    }

    public String getAntiguedadfrio() {
        return antiguedadfrio;
    }

    public void setAntiguedadfrio(String antiguedadfrio) {
        this.antiguedadfrio = antiguedadfrio;
    }

    public String getRendimientofrio() {
        return rendimientofrio;
    }

    public void setRendimientofrio(String rendimientofrio) {
        this.rendimientofrio = rendimientofrio;
    }

    public String getRendimientocalor() {
        return rendimientocalor;
    }

    public void setRendimientocalor(String rendimientocalor) {
        this.rendimientocalor = rendimientocalor;
    }

    @Override
    public String toString() {
        return "Refrigeracion{" +
                "generadorfrio='" + generadorfrio + '\'' +
                ", combustiblefrio='" + combustiblefrio + '\'' +
                ", antiguedadfrio='" + antiguedadfrio + '\'' +
                ", rendimientofrio='" + rendimientofrio + '\'' +
                ", rendimientocalor='" + rendimientocalor + '\'' +
                '}';
    }
}
