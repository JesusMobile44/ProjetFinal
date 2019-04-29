package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Fusible extends Composante {
    public Fusible() {
        initialize();
    }

    public Fusible(ComposanteSave composanteSave, int i, int j) {
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
        description = "Un fusible est un organe de sécurité dont le rôle est d'ouvrir \n" +
                "le circuit électrique lorsque le courant électrique dans celui-ci atteint un valeur d'intensité donnée.";
        tooltip = new Tooltip("Ferme le circuit si trop de courant passe au travers");
        tooltip.setStyle("-fx-font-size: 20");
        Tooltip.install(this,tooltip);
        nom = "Fusible";
        initializeImage();
    }
}
