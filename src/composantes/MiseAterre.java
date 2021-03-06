package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class MiseAterre extends Composante {
    public MiseAterre() {
        initialize();
    }

    public MiseAterre(ComposanteSave composanteSave, int i, int j) {
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
        tabNomVariante[2] = "SN";
        tabNomVariante[3] = "EO";
        tabVariante = new Image[tabNomVariante.length];
        description = "Une mise à terre sert à évacuer les courants électriques\n" +
                "vers la terre de manière sécuritaire.";
        tooltip = new Tooltip("Évacue les courants");
        tooltip.setStyle("-fx-font-size: 20");
        bindTooltip(this,tooltip);
        nom = "Mise à Terre";
        initializeImage();
    }
}
