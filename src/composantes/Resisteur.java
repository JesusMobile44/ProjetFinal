package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class Resisteur extends Composante {
    public Resisteur() {
        initialize();
    }

    public Resisteur(ComposanteSave composanteSave, int i, int j) {
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
        description = "Description Résisteur";
        tooltip = new Tooltip("Tooltip Résisteur");
        Tooltip.install(this,tooltip);
        nom = "Resisteur";
        initializeImage();
        this.setResistance(100);
    }
}
