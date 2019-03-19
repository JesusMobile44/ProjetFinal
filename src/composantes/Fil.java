package composantes;

import controllers.SandboxController;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import main.Main;


public class Fil extends Composante {

    private boolean noeud;

    public Fil() {
        tabNomVariante = new String[11];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "NO";
        tabNomVariante[2] = "NE";
        tabNomVariante[3] = "SE";
        tabNomVariante[4] = "SO";
        tabNomVariante[5] = "OE";
        tabNomVariante[6] = "NSE";
        tabNomVariante[7] = "NSO";
        tabNomVariante[8] = "SOE";
        tabNomVariante[9] = "NEO";
        tabNomVariante[10] = "NSEO";
        tabVariante = new Image[tabNomVariante.length];
        description = "Description Fil";
        tooltip = new Tooltip("Tooltip Fil");
        Tooltip.install(this,tooltip);
        nom = "Fil";
        realImage = new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i] = Main.getImagesContainer().getHashMapImage().get(nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);
        this.setFitHeight(100);
        this.setFitWidth(100);
        this.noeud = false;


    }

    public boolean isNoeud() {
        return noeud;
    }

    public void setNoeud(boolean noeud) {
        this.noeud = noeud;
    }
}
