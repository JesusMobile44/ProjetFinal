package controllers;

import composantes.Composante;
import composantes.ComposanteVide;
import composantes.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import main.Main;



public class SandboxController {

    public static GridPane gridPaneSandBox = new GridPane();
    public static FlowPane rootDescription = new FlowPane();
    private static FlowPane rootScrollPane = new FlowPane();

    @FXML
    private SplitPane mySplitPane;

    @FXML
    private SplitPane affichageDescription;

    @FXML
    private ScrollPane scroll;

    @FXML
    private ScrollPane myScrollPane;



    @FXML
    public void initialize(){
        scroll.setContent(gridPaneSandBox);

        gridPaneSandBox.setPrefSize(376, 414);
        //initializeGridPane(gridPaneSandBox);

        for (int i=0; i<20; i++){
            for (int j=0; j<20; j++){
                ComposanteVide vide = new ComposanteVide();
                vide.fitHeightProperty().set(100);
                vide.fitWidthProperty().set(100);
                vide.setRow(j);
                vide.setCol(i);
                gridPaneSandBox.add(vide, i, j);
            }
        }


        rootScrollPane.getChildren().add(new Fil());
        rootScrollPane.getChildren().add(new Amperemetre());
        rootScrollPane.getChildren().add(new Ampoule());
        rootScrollPane.getChildren().add(new Batterie());
        rootScrollPane.getChildren().add(new Diode());
        rootScrollPane.getChildren().add(new Fusible());
        rootScrollPane.getChildren().add(new Interrupteur());
        rootScrollPane.getChildren().add(new MiseAterre());
        rootScrollPane.getChildren().add(new Ohmetre());
        rootScrollPane.getChildren().add(new Resistance());
        rootScrollPane.getChildren().add(new Voltmetre());
        //rootScrollPane.getChildren().add(new Moteur());
        //rootScrollPane.getChildren().add(new HautParleur());
        rootScrollPane.setPadding(new Insets(16));
        rootScrollPane.setVgap(16);
        rootScrollPane.setHgap(16);



        myScrollPane.setContent(rootScrollPane);
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


    public static void echangerComposantes(int[] posSource, int[] posTarget, Composante source, Composante target) {
        source.setRow(posTarget[0]);
        source.setCol(posTarget[1]);

        target.setRow(posSource[0]);
        target.setCol(posSource[1]);

        gridPaneSandBox.getChildren().remove(target);
        gridPaneSandBox.getChildren().remove(source);
        gridPaneSandBox.add(source, posTarget[1], posTarget[0]);
        gridPaneSandBox.add(target, posSource[1], posSource[0]);
    }

    public static void placerComposantes(Composante source, Composante target){
        int i =target.getCol();
        int j=target.getRow();
        gridPaneSandBox.getChildren().remove(target);
        source.setCol(i);
        source.setRow(j);
        gridPaneSandBox.add(source, i, j);

    }

    public static void remettreComposante(String nom){

        switch (nom.toUpperCase()){


            case "AMPEREMÈTRE":
                rootScrollPane.getChildren().add(1, new Amperemetre());
                break;
            case "AMPOULE":
                rootScrollPane.getChildren().add(2, new Ampoule());
                break;
            case "BATTERIE":
                rootScrollPane.getChildren().add(3, new Batterie());
                break;
            case "DIODE":
                rootScrollPane.getChildren().add(4, new Diode());
                break;
            case "FIL":
                rootScrollPane.getChildren().add(0, new Fil());
                break;
            case "FUSIBLE":
                rootScrollPane.getChildren().add(5, new Fusible());
                break;
            case "HAUT-PARLEUR":
                break;
            case "INTERRUPTEUR":
                rootScrollPane.getChildren().add(6, new Interrupteur());
                break;
            case "MISE À TERRE":
                rootScrollPane.getChildren().add(7, new MiseAterre());
                break;
            case "MOTEUR":
                rootScrollPane.getChildren().add(0, new Fil());
                break;
            case "OHMÈTRE":
                rootScrollPane.getChildren().add(8, new Ohmetre());
                break;
            case "RESISTEUR":
                rootScrollPane.getChildren().add(9, new Resistance());
                break;
            case "VOLTMÈTRE":
                rootScrollPane.getChildren().add(10, new Voltmetre());

        }
    }




    /*
    private void creerMenuContext(){
        MenuItem itemSupprimer = new MenuItem("Supprimer");
        Menu menuVariante = new Menu("Variantes");
        MenuItem itemVariante[] = new MenuItem[tabNomVariante.length];
        for(int i=0;i<itemVariante.length;i++){
            itemVariante[i]= new MenuItem(tabNomVariante[i]);
            menuVariante.getItems().add(itemVariante[i]);
            int temp[]={i};
            itemVariante[i].setOnAction(event -> {composante.setImage(tabVariante[temp[0]]);});
        }
        ContextMenu contextMenu = new ContextMenu(itemSupprimer, menuVariante);
        composante.setOnContextMenuRequested(event -> contextMenu.show(composante, event.getScreenX(), event.getScreenY()));

        itemSupprimer.setOnAction(event -> {composante=new ComposanteVide();});
        return itemReinitialiser;
    }
    */


}


