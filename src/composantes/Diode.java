package composantes;

import concepts.Noeud;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Diode extends Composante {
    private Noeud noeudDirectionnel;
    private boolean inverseEnSerie;

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
        tabNomVariante[1] = "EO";
        tabNomVariante[2] = "SN";
        tabNomVariante[3] = "OE";
        tabVariante = new Image[tabNomVariante.length];
        description = "Une diode est un dîpole qui ne laisse passer le courant électrique que dans un sens.";
        tooltip = new Tooltip("Laisse passer le courant dans un seul sens");
        tooltip.setStyle("-fx-font-size: 20");
        Tooltip.install(this,tooltip);
        nom = "Diode";
        initializeImage();
    }

    public Noeud getNoeudDirectionnel() {
        return noeudDirectionnel;
    }

    public void setNoeudDirectionnel(Noeud noeudDirectionnel) {
        this.noeudDirectionnel = noeudDirectionnel;
    }

    public boolean isInverseEnSerie() {
        return inverseEnSerie;
    }

    public void setInverseEnSerie(boolean inverseEnSerie) {
        this.inverseEnSerie = inverseEnSerie;
    }
}
