package composantes;

import autre.ImagesContainer;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Ampoule extends ComposanteActivable {
    public Ampoule() {
        initialize();
    }

    public Ampoule(ComposanteSave composanteSave, int i, int j) {
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
        tabVariante = new Image[tabNomVariante.length+2];
        description = "La lampe incandescente est constituée d'un filament électrique enfermé dans une ampoule en verre.\nLorsque l'électricité passe dans le filament électrique, il est porté à incandescence.";
        tooltip = new Tooltip("Produit de la lumière");
        Tooltip.install(this,tooltip);
        nom = "Ampoule";
        for (int i = 0; i < tabNomVariante.length+2; i++) {
            tabVariante[i] = ImagesContainer.getHashMapImage().get(nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);

        this.setFitHeight(100);
        this.setFitWidth(100);
    }
}
