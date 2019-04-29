package composantes;

import composantes.Composante;

import java.io.Serializable;

public class ComposanteSave implements Serializable {
    private String nom;
    private int direction;
    private double volt;
    private double resistance;
    private double capacite;

    public ComposanteSave(Composante composante) {
        this.nom = composante.getNom();
        this.direction = composante.getDirection();
        this.volt = composante.getVolt();
        this.resistance = composante.getResistance();
        this.capacite = composante.getCapacite();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getVolt() {
        return volt;
    }

    public void setVolt(double volt) {
        this.volt = volt;
    }

    public double getResistance() {
        return resistance;
    }

    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }
}
