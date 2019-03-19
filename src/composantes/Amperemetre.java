package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class Amperemetre extends Composante {
    public Amperemetre() {
        tabNomVariante = new String[2];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";
        tabVariante = new Image[tabNomVariante.length];
        description = "Un ampèremètre est un appareil de mesure de l'intensité d'un courant électrique dans un circuit. L'unité de mesure de l'intensité est l'ampère, symbole : A";
        tooltip = new Tooltip("Appareil de mesure de l'intensité d'un courant électrique dans un circuit");
        Tooltip.install(this,tooltip);
        nom = "Amperemètre";
        realImage = new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i] = Main.getImagesContainer().getHashMapImage().get(nom.toLowerCase() + " (" + (i + 1) + ").png");//new Image("composantes/images/" + nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);
        this.setFitHeight(100);
        this.setFitWidth(100);
    }

}