package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Voltmetre extends Composante {
    public Voltmetre() {
        initialize();
    }

    public Voltmetre(ComposanteSave composanteSave, int i, int j) {
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
        description = "Un Voltmètre est un appareil qui permet de mesurer la tension entre deux points.\n" +
                "Il doit être branché en parallèle.";
        tooltip = new Tooltip("Mesure la tension");
        Tooltip.install(this,tooltip);
        nom = "Voltmètre";
        initializeImage();
    }
}
