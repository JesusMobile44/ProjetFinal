package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Condensateur extends Composante {
    public Condensateur() {
        initialize();
    }

    public Condensateur(ComposanteSave composanteSave, int i, int j) {
        initialize();
        direction = composanteSave.getDirection();
        capacite=composanteSave.getCapacite();
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
        description = "Dispositif servant à emmagasiner des charges électriques";
        tooltip = new Tooltip("Emmagasine des charges électriques");
        tooltip.setStyle("-fx-font-size: 20");
        bindTooltip(this,tooltip);
        nom = "Condensateur";
        initializeImage();
        this.setCapacite(100);
    }
}
