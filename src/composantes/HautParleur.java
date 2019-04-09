package composantes;

import composantes.ComposanteActivable;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class HautParleur extends ComposanteActivable {
    public HautParleur() {
        tabNomVariante = new String[4];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";

        tabVariante = new Image[tabNomVariante.length];
        description = "Un haut-parleur produit des sons à partir d'un signal électrique.";
        tooltip = new Tooltip("Tooltip Haut-Parleur");
        Tooltip.install(this,tooltip);
        nom = "Haut-Parleur";
        realImage = new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i] = Main.getImagesContainer().getHashMapImage().get(nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);

        this.setFitHeight(100);
        this.setFitWidth(100);
    }
}
