package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;


public class Fil extends Composante {

    private boolean noeud;
    private boolean noeudMAT;

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
        noeudMAT = false;
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
        description = "Un fil électrique est la composante électrique servant au transport de l'électricité\n" +
                "";
        tooltip = new Tooltip("Transporte l'électricité");
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

    public boolean isNoeudMAT() {
        return noeudMAT;
    }

    public void setNoeudMAT(boolean noeudMAT) {
        this.noeudMAT = noeudMAT;
    }
}
