package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class MiseAterre extends Composante {
    public MiseAterre() {
        tabNomVariante = new String[4];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";
        tabNomVariante[2] = "SN";
        tabNomVariante[3] = "EO";
        tabVariante = new Image[tabNomVariante.length];
        description = "Description Mise à Terre";
        tooltip = new Tooltip("Tooltip Mise à Terre");
        Tooltip.install(this,tooltip);
        nom = "Mise à Terre";
        realImage = new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i] = Main.getImagesContainer().getHashMapImage().get(nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);

        this.setFitHeight(100);
        this.setFitWidth(100);
    }
}
