package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

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
        description = "Un Ohmmètre est un instrument de mesure qui permet de mesurer la résistance électrique\n" +
                "d'un composant ou d'un circuit électrique";
        tooltip = new Tooltip("Mesure la résistance équivalente");
        Tooltip.install(this,tooltip);
        nom = "Ohmmètre";
        initializeImage();
    }
}
