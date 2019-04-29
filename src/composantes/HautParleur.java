package composantes;

import composantes.ComposanteActivable;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class HautParleur extends ComposanteActivable {
    private MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/autre/musiques/default.mp3").toURI().toString()));

    public HautParleur() {
        initialize();
    }

    public HautParleur(ComposanteSave composanteSave, int i, int j) {
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
        tabNomVariante = new String[4];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";

        tabVariante = new Image[tabNomVariante.length];
        description = "Un haut-parleur produit des sons à partir d'un signal électrique.";
        tooltip = new Tooltip("Produit du son");
        tooltip.setStyle("-fx-font-size: 20");
        Tooltip.install(this,tooltip);
        nom = "Haut-Parleur";
        initializeImage();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}
