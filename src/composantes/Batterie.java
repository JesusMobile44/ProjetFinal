package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Batterie  extends ComposanteActivable{
    public Batterie() {
        tabNomVariante=new String[4];
        tabNomVariante[0]="NS";
        tabNomVariante[1]="OE";
        tabNomVariante[2]="SN";
        tabNomVariante[3]="EO";
        tabVariante = new Image[tabNomVariante.length];
        description="Description Source";
        tooltip=new Tooltip("Tooltip Source");
        nom="Source";
        realImage=new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i]=new Image("composantes/images/" + nom.toLowerCase() + " (" +(i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);
    }
}
