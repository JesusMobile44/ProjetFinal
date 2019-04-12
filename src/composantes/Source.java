package composantes;

import concepts.Noeud;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class Source extends ComposanteActivable {

    private Noeud noeudDirectionnel;

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
        description = "Description Source";
        tooltip = new Tooltip("Tooltip Source");
        Tooltip.install(this,tooltip);
        nom = "Source";
        initializeImage();
        this.setVolt(10);
        this.setAmperage(1);
        noeudDirectionnel=null;
    }

    public Noeud getNoeudDirectionnel() {
        return noeudDirectionnel;
    }

    public void setNoeudDirectionnel(Noeud noeudDirectionnel) {
        this.noeudDirectionnel = noeudDirectionnel;
    }
}
