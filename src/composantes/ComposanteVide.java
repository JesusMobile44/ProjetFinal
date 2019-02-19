package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class ComposanteVide extends Composante{
    public ComposanteVide() {
        tabNomVariante= new String[1];
        tabNomVariante[0]="vide";
        tabVariante = new Image[tabNomVariante.length];
        nom="vide";
            tabVariante[0]=new Image("composantes/images/vide.png");

    }


}
