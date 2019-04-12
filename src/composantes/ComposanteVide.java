package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class ComposanteVide extends Composante {
    public ComposanteVide() {
        tabNomVariante = new String[1];
        tabNomVariante[0] = "vide";
        tabVariante = new Image[tabNomVariante.length];
        nom = "vide";
        tabVariante[0] = Main.getImagesContainer().getHashMapImage().get("vide.png");
        this.setImage(tabVariante[0]);
        enPlace = true;
        this.setFitHeight(100);
        this.setFitWidth(100);
    }
}
