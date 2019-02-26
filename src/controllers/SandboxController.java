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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import main.Main;



public class SandboxController {

    public static GridPane gridPaneSandBox = new GridPane();

    @FXML
    private SplitPane mySplitPane;

    @FXML
    private ScrollPane scroll;

    @FXML
    private ScrollPane myScrollPane;

    @FXML
    public void initialize(){
        scroll.setContent(gridPaneSandBox);

        gridPaneSandBox.setPrefSize(376, 414);
        //initializeGridPane(gridPaneSandBox);

        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                ComposanteVide vide = new ComposanteVide();
                vide.fitHeightProperty().set(100);
                vide.fitWidthProperty().set(100);
                vide.setRow(j);
                vide.setCol(i);
                gridPaneSandBox.add(vide, i, j);
            }
        }

        HBox rootScrollPane = new HBox();
        rootScrollPane.getChildren().add(new Fil());
        rootScrollPane.getChildren().add(new Amperemetre());
        rootScrollPane.getChildren().add(new Ampoule());
        rootScrollPane.getChildren().add(new Batterie());
        rootScrollPane.getChildren().add(new Diode());
        rootScrollPane.getChildren().add(new Fusible());
        rootScrollPane.getChildren().add(new Interrupteur());
        rootScrollPane.getChildren().add(new MiseAterre());
        //rootScrollPane.getChildren().add(new Moteur());
        rootScrollPane.getChildren().add(new Ohmetre());
        rootScrollPane.getChildren().add(new Resistance());
        rootScrollPane.getChildren().add(new Voltmetre());
        rootScrollPane.setPadding(new Insets(16));

        myScrollPane.setContent(rootScrollPane);
    }

    /*
    public void initializeGridPane(GridPane gridPane){
        final int numCols = 10 ;
        final int numRows = 10 ;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPrefWidth(20);
            gridPane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(20);
            gridPane.getRowConstraints().add(rowConst);
        }
    }
*/
    public static void echangerComposantes(int[] posSource, int[] posTarget, Composante source, Composante target) {
        source.setRow(posTarget[0]);
        source.setCol(posTarget[1]);

        target.setRow(posSource[0]);
        target.setCol(posSource[1]);

        gridPaneSandBox.add(source, posTarget[1], posTarget[0]);
        gridPaneSandBox.add(target, posSource[1], posSource[0]);
    }

    public static void placerComposantes(Composante source, Composante target){
        int i =target.getCol();
        int j=target.getRow();
        gridPaneSandBox.getChildren().remove(target);
        gridPaneSandBox.add(source, i, j);

    }
/*

    public void dragAndDrop(Composante imageView){

        imageView.setOnDragDetected(event -> {
            Dragboard dragboard = imageView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent contenu = new ClipboardContent();
            contenu.putImage(imageView.getImage());
            dragboard.setContent(contenu);
        });
        imageView.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.MOVE);
        });
        imageView.setOnDragDropped(event -> {
            if(imageView.getEnPlace){
                Composante imageViewSource = (Composante) event.getGestureSource();
                Composante imageViewTarget = (Composante) event.getGestureTarget();
                Image imageTempSource = imageViewSource.getImage();
                imageViewSource.setImage(imageViewTarget.getImage());
                imageViewTarget.setImage(imageTempSource);
                event.setDropCompleted(true);
            } else{
                Composante imageViewSource = (Composante) event.getGestureSource();
                Composante imageViewTarget = (Composante) event.getGestureTarget();
                imageViewTarget.setImage(imageViewSource.getImage());
                event.setDropCompleted(true);
            }
        });

    }
    */

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


    /*
                        <GridPane fx:id="myGridPane" alignment="CENTER" prefHeight="376.0" prefWidth="414.0">
                          <columnConstraints>
                            <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                            <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                              <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                  <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                  <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                  <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                  <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                  <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                  <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                          </columnConstraints>
                          <rowConstraints>
                            <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
                            <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
                            <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
                  <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
                  <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
                  <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
                  <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
                  <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
                  <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
                          </rowConstraints>
                        </GridPane>
*/
    /*
    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }*/
}


