package composantes;

import composantes.ComposanteActivable;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class HautParleur extends ComposanteActivable {
    public HautParleur() {
        initialize();
    }

    public HautParleur(ComposanteSave composanteSave, int i, int j) {
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
        tabNomVariante = new String[4];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";

        tabVariante = new Image[tabNomVariante.length];
        description = "Description Haut-Parleur";
        tooltip = new Tooltip("Tooltip Haut-Parleur");
        Tooltip.install(this,tooltip);
        nom = "Haut-Parleur";
        initializeImage();
    }
}
