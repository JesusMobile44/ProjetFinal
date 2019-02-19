package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Composante extends ImageView{
    protected Image[] tabVariante;
    protected String[] tabNomVariante;
    protected int direction;
    protected double amperage;
    protected double volt;
    protected double resistance;
    protected String description;
    protected Tooltip tooltip;
    protected String nom;
    protected Composante[] tabAutour;
    protected Image realImage;
    protected boolean enPlace;

    public Composante() {
        this.direction = 0;
        this.amperage = 0;
        this.volt = 0;
        this.resistance = 0;
        this.tabAutour = new Composante[4];
        this.enPlace = false;
    }

    public Image[] getTabVariante() {
        return tabVariante;
    }

    public void setTabVariante(Image[] tabVariante) {
        this.tabVariante = tabVariante;
    }

    public String[] getTabNomVariante() {
        return tabNomVariante;
    }

    public void setTabNomVariante(String[] tabNomVariante) {
        this.tabNomVariante = tabNomVariante;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getAmperage() {
        return amperage;
    }

    public void setAmperage(double amperage) {
        this.amperage = amperage;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Composante[] getTabAutour() {
        return tabAutour;
    }

    public void setTabAutour(Composante[] tabAutour) {
        this.tabAutour = tabAutour;
    }

    public Image getRealImage() {
        return realImage;
    }

    public void setRealImage(Image realImage) {
        this.realImage = realImage;
    }

    public boolean isEnPlace() {
        return enPlace;
    }

    public void setEnPlace(boolean enPlace) {
        this.enPlace = enPlace;
    }
}