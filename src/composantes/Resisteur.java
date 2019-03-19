package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class Resisteur extends Composante {
    public Resisteur() {
        tabNomVariante = new String[2];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";
        tabVariante = new Image[tabNomVariante.length];
        description = "Description Résisteur";
        tooltip = new Tooltip("Tooltip Résisteur");
        Tooltip.install(this,tooltip);
        nom = "Resisteur";
        realImage = new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i] = Main.getImagesContainer().getHashMapImage().get(nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);
        this.setFitHeight(100);
        this.setFitWidth(100);
        this.setResistance(100);
    }
}
