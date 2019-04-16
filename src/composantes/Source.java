package composantes;

import concepts.Noeud;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class Source extends ComposanteActivable {

    private Noeud noeudDirectionnel;

    public Source() {
        tabNomVariante = new String[4];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";
        tabNomVariante[2] = "SN";
        tabNomVariante[3] = "EO";
        tabVariante = new Image[tabNomVariante.length];
        description = "Une pile, batterie ou acccumulateur est un dispositif électrochimique qui convertit l'énergie chimique en énergie électrique.\n" +
                "Elle fournit une intensité au circuit électrique.";
        tooltip = new Tooltip("Fournit l'intensité au circuit");
        Tooltip.install(this,tooltip);
        nom = "Source";
        realImage = new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i] = Main.getImagesContainer().getHashMapImage().get(nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);

        this.setFitHeight(100);
        this.setFitWidth(100);
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
