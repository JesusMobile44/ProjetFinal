package controllers;

import main.Main;

public class GuideController {
    public void setSandbox(){
        Main.changerDeMode(1);
    }

    public void setAventure(){
        Main.changerDeMode(2);
    }

    public void setGuide(){
        Main.changerDeMode(3);
    }

    public void setMenu(){
        Main.changerDeMode(0);
    }
}
