package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class Ohmetre extends Composante {
    public Ohmetre() {
        tabNomVariante = new String[2];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";
        tabVariante = new Image[tabNomVariante.length];
        description = "Description Ohmètre";
        tooltip = new Tooltip("Tooltip Ohmètre");
        Tooltip.install(this,tooltip);
        nom = "Ohmètre";
        realImage = new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i] = Main.getImagesContainer().getHashMapImage().get(nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);
        this.setFitHeight(100);
        this.setFitWidth(100);
    }
}
