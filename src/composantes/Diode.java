package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Diode extends Composante {
    public Diode() {
        initialize();
    }

    public Diode(ComposanteSave composanteSave, int i, int j) {
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
        description = "Une diode est un dîpole qui ne laisse passer le courant électrique que dans un sens.";
        tooltip = new Tooltip("Laisse passer le courant dans un seul sens");
        Tooltip.install(this,tooltip);
        nom = "Diode";
        initializeImage();
    }
}
