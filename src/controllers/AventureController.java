package controllers;

import composantes.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import main.Main;

import java.text.DecimalFormat;

public class AventureController {
    public static GridPane gridPaneAventure = new GridPane();
    public static FlowPane rootDescription = new FlowPane();
    private static FlowPane rootScrollPane = new FlowPane();
    public static DecimalFormat df = new DecimalFormat("#.##");

    @FXML
    private SplitPane mySplitPane;

    @FXML
    private FlowPane affichageDescription;

    @FXML
    private ScrollPane scroll;

    @FXML
    private ScrollPane myScrollPane;



    public void initialize() {
        scroll.setContent(gridPaneAventure);

        gridPaneAventure.setPrefSize(376, 414);
        //initializeGridPane(gridPaneSandBox);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                ComposanteVide vide = new ComposanteVide();
                vide.fitHeightProperty().set(100);
                vide.fitWidthProperty().set(100);
                vide.setRow(j);
                vide.setCol(i);
                gridPaneAventure.add(vide, i, j);
            }
        }


        rootScrollPane.getChildren().add(new Fil());
        rootScrollPane.getChildren().add(new Amperemetre());
        rootScrollPane.getChildren().add(new Ampoule());
        rootScrollPane.getChildren().add(new Source());
        rootScrollPane.getChildren().add(new Diode());
        rootScrollPane.getChildren().add(new Fusible());
        rootScrollPane.getChildren().add(new Interrupteur());
        rootScrollPane.getChildren().add(new MiseAterre());
        rootScrollPane.getChildren().add(new Ohmetre());
        rootScrollPane.getChildren().add(new Resisteur());
        rootScrollPane.getChildren().add(new Voltmetre());
        rootScrollPane.getChildren().add(new Switch());
        //rootScrollPane.getChildren().add(new Moteur());
        //rootScrollPane.getChildren().add(new HautParleur());
        rootScrollPane.setPadding(new Insets(16));
        rootScrollPane.setVgap(16);
        rootScrollPane.setHgap(16);


        myScrollPane.setContent(rootScrollPane);
    }
    public void verificationStage(){

    }
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
