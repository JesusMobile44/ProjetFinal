package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class Ohmetre extends Composante {
    public Ohmetre() {
        initialize();
    }

    public Ohmetre(ComposanteSave composanteSave, int i, int j) {
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
        description = "Description Ohmètre";
        tooltip = new Tooltip("Tooltip Ohmètre");
        Tooltip.install(this,tooltip);
        nom = "Ohmètre";
        initializeImage();
    }
}
