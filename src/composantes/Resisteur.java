package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Resisteur extends Composante {
    public Resisteur() {
        initialize();
    }

    public Resisteur(ComposanteSave composanteSave, int i, int j) {
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
        tabVariante = new Image[tabNomVariante.length];
        description = "Une résistance ou resistor est un composant électronique ou électrique\n" +
                "dont la principale caractéristique est d'opposer une plus ou moins grande \n" +
                "résistance (mesurée en ohms) à la circulation du courant électrique.";
        tooltip = new Tooltip("Oppose un résistance à la circulation");
        Tooltip.install(this,tooltip);
        nom = "Résisteur";
        initializeImage();
        this.setResistance(100);
    }
}
