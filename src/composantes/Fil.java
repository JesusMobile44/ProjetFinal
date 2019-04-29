package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;


public class Fil extends Composante {

    private boolean noeud;

    public Fil() {
        initialize();
    }

    public Fil(ComposanteSave composanteSave, int i, int j) {
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
        description = "Un fil électrique est la composante électrique servant au transport de l'électricité";
        tooltip = new Tooltip("Transporte l'électricité");
        tooltip.setStyle("-fx-font-size: 20");
        Tooltip.install(this,tooltip);
        nom = "Fil";
        initializeImage();
        this.noeud = false;
    }

    public boolean isNoeud() {
        return noeud;
    }

    public void setNoeud(boolean noeud) {
        this.noeud = noeud;
    }
}
