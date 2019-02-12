package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Composante extends ImageView{
    Image tabVariante[];
    String tabNomVariante[];
    int direction;
    double amperage;
    double volt;
    double resistance;
    String description;
    Tooltip tooltip;
    String nom;
    Composante tabAutour;
    Image realImage;
    boolean enPlace;
}