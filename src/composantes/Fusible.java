package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class Fusible extends Composante {
    public Fusible() {
        tabNomVariante = new String[2];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";
        tabVariante = new Image[tabNomVariante.length];
        description = "Un fusible est un organe de sécurité dont le rôle est d'ouvrir \n" +
                "le circuit électrique lorsque le courant électrique dans celui-ci atteint un valeur d'intensité donnée.";
        tooltip = new Tooltip("Ferme le circuit si trop de courant passe au travers");
        Tooltip.install(this,tooltip);
        nom = "Fusible";
        realImage = new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i] = Main.getImagesContainer().getHashMapImage().get(nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);

        this.setFitHeight(100);
        this.setFitWidth(100);
    }
}
