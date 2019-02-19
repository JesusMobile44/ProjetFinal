package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class Fil extends Composante {
    public Fil() {
        tabNomVariante=new String[11];
        tabNomVariante[0]="NS";
        tabNomVariante[1]="NO";
        tabNomVariante[2]="NE";
        tabNomVariante[3]="SE";
        tabNomVariante[4]="SO";
        tabNomVariante[5]="EO";
        tabNomVariante[6]="NSE";
        tabNomVariante[7]="NSO";
        tabNomVariante[8]="SEO";
        tabNomVariante[9]="NEO";
        tabNomVariante[10]="NSEO";
        description="Description Fil";
        tooltip=new Tooltip("Tooltip Fil");
        nom="Fil";
        realImage=new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i]=new Image("file:images/" + nom.toLowerCase() + " (" + (i + 1) + ")" + ".jpg");
        }
    }
}
