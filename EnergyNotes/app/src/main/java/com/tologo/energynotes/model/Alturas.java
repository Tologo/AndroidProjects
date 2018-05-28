package com.tologo.energynotes.model;

public class Alturas {

    private String alturamax;
    private String alturamedia;
    private String alturamin;

    public Alturas() {
    }

    // Generamos el constructor de la clase
    public Alturas(String alturamax, String alturamedia, String alturamin) {
        this.alturamax = alturamax;
        this.alturamedia = alturamedia;
        this.alturamin = alturamin;
    }

    public String getAlturamax() {
        return alturamax;
    }

    public void setAlturamax(String alturamax) {
        this.alturamax = alturamax;
    }

    public String getAlturamedia() {
        return alturamedia;
    }

    public void setAlturamedia(String alturamedia) {
        this.alturamedia = alturamedia;
    }

    public String getAlturamin() {
        return alturamin;
    }

    public void setAlturamin(String alturamin) {
        this.alturamin = alturamin;
    }

    @Override
    public String toString() {
        return "Alturas{" +
                ", alturamax='" + alturamax + '\'' +
                ", alturamedia='" + alturamedia + '\'' +
                ", alturamin='" + alturamin + '\'' +
                '}';
    }
}
