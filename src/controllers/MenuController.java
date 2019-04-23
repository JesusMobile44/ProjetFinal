package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.Main;

public class MenuController {

    public void setSandbox(){
        Main.changerDeMode(1);
    }

    public void setExit(){ System.exit(0);}

    public void dragOver(Button button){button.setOpacity(50);}

    public void setGuide(){
        Main.changerDeMode(3);
    }

    public void setMenu(){
        Main.changerDeMode(0);
    }
}
