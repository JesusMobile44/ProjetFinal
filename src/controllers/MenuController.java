package controllers;

import main.Main;

public class MenuController {

    public void startJeu(){
        Main.getStage().setScene(Main.getScenes()[1]);
    }
}
