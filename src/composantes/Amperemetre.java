package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Amperemetre  extends Composante{
    public Amperemetre() {
        tabNomVariante=new String[2];
        tabNomVariante[0]="NS";
        tabNomVariante[1]="OE";
        tabVariante = new Image[tabNomVariante.length];
        description="Description Ampèremètre";
        tooltip=new Tooltip("Tooltip Ampèremètre");
        nom="Amperemètre";
        realImage=new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i]=new Image("composantes/images/" + nom.toLowerCase() + " (" +(i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);

        this.setFitHeight(100);
        this.setFitWidth(100);
    }
}
