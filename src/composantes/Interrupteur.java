package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Interrupteur extends ComposanteActivable {
    public Interrupteur() {
        initialize();
    }

    public Interrupteur(ComposanteSave composanteSave, int i, int j) {
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
        tabNomVariante[1] = "OFF";
        tabNomVariante[2] = "OE";
        tabNomVariante[3] = "OFF";
        tabVariante = new Image[tabNomVariante.length];
        description = "Un interrupteur est un organe permettant d'interrompre ou d'autoriser le passage d'un flux électrique.";
        tooltip = new Tooltip("Peut alterner entre l'état Ouvert et Fermer");
        tooltip.setStyle("-fx-font-size: 20");
        bindTooltip(this,tooltip);
        nom = "Interrupteur";
        initializeImage();
    }
}
