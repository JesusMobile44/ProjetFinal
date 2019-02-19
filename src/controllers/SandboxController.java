package controllers;

import composantes.Composante;
import composantes.ComposanteVide;
import composantes.Fil;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import main.Main;


public class SandboxController {

    @FXML
    private GridPane myGridPane;

    @FXML
    public void initialize(){
        for (int i=0; i<myGridPane.getRowConstraints().size(); i++){
            for (int j=0; j<myGridPane.getColumnConstraints().size(); j++){
                ComposanteVide vide = new ComposanteVide();
                vide.setImage(vide.getTabVariante()[0]);
                myGridPane.add(vide,i,j);
            }
        }
    }

    public void test(){
        System.out.println("gggg");
        Fil fil = new Fil();
        fil.setImage(fil.getTabVariante()[0]);
        myGridPane.add(fil,0,0);
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
