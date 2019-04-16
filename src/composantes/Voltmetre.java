package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class Voltmetre extends Composante {
    public Voltmetre() {
        tabNomVariante = new String[2];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";
        tabVariante = new Image[tabNomVariante.length];
        description = "Un Voltmètre est un appareil qui permet de mesurer la tension entre deux points.\n" +
                "Il doit être branché en parallèle.";
        tooltip = new Tooltip("Mesure la tension");
        Tooltip.install(this,tooltip);
        nom = "Voltmètre";
        realImage = new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i] = Main.getImagesContainer().getHashMapImage().get(nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);
        this.setFitHeight(100);
        this.setFitWidth(100);
    }
}
