package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Diode extends Composante {
    public Diode() {
        tabNomVariante = new String[4];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";
        tabNomVariante[2] = "SN";
        tabNomVariante[3] = "EO";
        tabVariante = new Image[tabNomVariante.length];
        description = "Description Diode";
        tooltip = new Tooltip("Tooltip Diode");
        Tooltip.install(this,tooltip);
        nom = "Diode";
        realImage = new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i] = new Image("composantes/images/" + nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);

        this.setFitHeight(100);
        this.setFitWidth(100);
    }
}
