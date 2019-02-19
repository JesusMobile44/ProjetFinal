package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Composante extends ImageView{
    Image[] tabVariante;
    String[] tabNomVariante;
    int direction;
    double amperage;
    double volt;
    double resistance;
    String description;
    Tooltip tooltip;
    String nom;
    Composante[] tabAutour;
    Image realImage;
    boolean enPlace;

    public Composante() {
        this.direction = 0;
        this.amperage = 0;
        this.volt = 0;
        this.resistance = 0;
        this.tabAutour = new Composante[4];
        this.enPlace = false;
    }
}