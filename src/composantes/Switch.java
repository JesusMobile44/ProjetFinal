package composantes;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import main.Main;

public class Switch extends Composante{

    public Switch(){
        initialize();
    }

    public Switch(ComposanteSave composanteSave, int i, int j) {
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
        initializeImage();
    }

}
