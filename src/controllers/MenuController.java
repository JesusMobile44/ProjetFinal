package controllers;

import javafx.stage.Stage;
import main.Main;

public class MenuController {

    public void startJeu(){
        Stage stage = Main.getStage();
        stage.setScene(Main.getScenes()[1]);
        stage.setResizable(true);

        stage.hide();
        stage.show();
    }
}
