package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Ohmetre  extends Composante{
    public Ohmetre() {
        tabNomVariante=new String[2];
        tabNomVariante[0]="NS";
        tabNomVariante[1]="OE";
        tabVariante = new Image[tabNomVariante.length];
        description="Description Ohmètre";
        tooltip=new Tooltip("Tooltip Ohmètre");
        nom="Ohmètre";
        realImage=new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i]=new Image("composantes/images/" + nom.toLowerCase() + " (" +(i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);
    }
}
