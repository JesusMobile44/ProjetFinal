package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class Switch extends Composante{

    public Switch(){
        tabNomVariante = new String[8];
        tabNomVariante[0] = "OS";
        tabNomVariante[1] = "ON";
        tabNomVariante[2] = "NO";
        tabNomVariante[3] = "NE";
        tabNomVariante[4] = "EN";
        tabNomVariante[5] = "ES";
        tabNomVariante[6] = "SE";
        tabNomVariante[7] = "SO";

        tabVariante = new Image[tabNomVariante.length];
        description = "Description Switch";
        tooltip = new Tooltip("Tooltip Switch");
        Tooltip.install(this,tooltip);
        nom = "Switch";
        realImage = new Image("file:images/" + nom.toLowerCase() + ".jpg");
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i] = Main.getImagesContainer().getHashMapImage().get(nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);
        this.setFitHeight(100);
        this.setFitWidth(100);

    }

}
