package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Amperemetre extends Composante {
    public Amperemetre() {
        initialize();
    }

    public Amperemetre(ComposanteSave composanteSave, int i, int j) {
        initialize();
        direction = composanteSave.getDirection();
        resistance=composanteSave.getResistance();
        volt=composanteSave.getVolt();
        row = i;
        col = j;
        this.setImage(tabVariante[direction]);
        enPlace = true;
    }

    private void initialize(){
        tabNomVariante = new String[2];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";
        tabVariante = new Image[tabNomVariante.length];
        description = "Un ampèremètre est un appareil de mesure de l'intensité d'un courant électrique dans un circuit.\nL'unité de mesure de l'intensité est l'ampère, symbole : A";
        tooltip = new Tooltip("Mesure l'intensité électrique");
        tooltip.setStyle("-fx-font-size: 20");
        bindTooltip(this,tooltip);
        nom = "Ampèremètre";
        initializeImage();
    }
}