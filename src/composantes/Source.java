package composantes;

import concepts.Noeud;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Source extends ComposanteActivable {

    private Noeud noeudDirectionnel;
    private boolean inverseEnSerie;

    public Source() {
        initialize();
    }

    public Source(ComposanteSave composanteSave, int i, int j) {
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
        description = "Une pile, batterie ou acccumulateur est un dispositif électrochimique\n" +
                "qui convertit l'énergie chimique en énergie électrique.\n" +
                "Elle fournit une intensité au circuit électrique.";
        tooltip = new Tooltip("Fournit l'intensité au circuit");
        tooltip.setStyle("-fx-font-size: 20");
        bindTooltip(this,tooltip);
        nom = "Source";
        initializeImage();
        this.setVolt(10);
        this.setAmperage(1);
        noeudDirectionnel=null;
        inverseEnSerie = false;
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
