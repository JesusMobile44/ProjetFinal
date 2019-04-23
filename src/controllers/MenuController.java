package controllers;

import main.Main;

public class MenuController {

    public void setSandbox(){
        Main.changerDeMode(1);
    }

    public void setGuide(){
        Main.getStage().maxHeightProperty().bind(Main.getStage().widthProperty().multiply(1500/1920));
        Main.getStage().minHeightProperty().bind(Main.getStage().widthProperty().multiply(1920/1080));
        Main.changerDeMode(2);
    }

}
