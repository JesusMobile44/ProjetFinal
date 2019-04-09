package composantes;

import controllers.SandboxController;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import main.Main;

public class Composante extends ImageView {
    protected Image[] tabVariante;
    protected String[] tabNomVariante;
    protected int direction;
    protected double amperage;
    protected double volt;
    protected double resistance;
    protected String description;
    protected Tooltip tooltip;
    protected String nom;
    protected Composante[] tabAutour;
    protected Image realImage;
    protected boolean enPlace;
    protected int row = 0;
    protected int col = 0;
    protected String sensCourant;

    public Composante() {
        this.direction = 0;
        this.amperage = 0;
        this.volt = 0;
        this.resistance = 0;
        this.tabAutour = new Composante[4];
        this.enPlace = false;
        this.sensCourant = "∅";

        this.setOnMouseClicked(event -> {
            SandboxController.textDescription.setText(this.getDescription());
            SandboxController.changerMenuComposante(this);

        });

        this.setOnMouseEntered(event -> {
            switch (Main.numeroMode) {
                case 1:
                    Label label = new Label(this.getDescription());
                    break;
            }
        });

        this.setOnDragDetected(event -> {
            Dragboard dragboard = this.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent contenu = new ClipboardContent();
            contenu.putImage(this.getImage());
            dragboard.setContent(contenu);
        });
        this.setOnDragOver(event ->
                event.acceptTransferModes(TransferMode.MOVE)
        );
        this.setOnDragDropped(event -> {

            Composante source = (Composante) event.getGestureSource();
            Composante target = (Composante) event.getGestureTarget();

            int[] posSource = {source.getRow(), source.getCol()};
            int[] posTarget = {target.getRow(), target.getCol()};


            if (source != target) {
                if (source.isEnPlace() && target.isEnPlace()) {

                    switch (Main.numeroMode) {
                        case 1:
                            SandboxController.echangerComposantes(posSource, posTarget, source, target);
                            break;
                    }

                    source.enPlace = true;
                    event.setDropCompleted(true);

                } else if (target.isEnPlace()) {
                    switch (Main.numeroMode) {
                        case 1:
                            SandboxController.remettreComposante(source);
                            SandboxController.placerComposantes(source, target);
                            break;
                    }

                    source.enPlace = true;
                    event.setDropCompleted(true);
                }
            }


        });
        this.setOnDragDone(event -> {
            SandboxController.updateCircuit();
        });
    }

    public Image[] getTabVariante() {
        return tabVariante;
    }

    public void setTabVariante(Image[] tabVariante) {
        this.tabVariante = tabVariante;
    }

    public String[] getTabNomVariante() {
        return tabNomVariante;
    }

    public void setTabNomVariante(String[] tabNomVariante) {
        this.tabNomVariante = tabNomVariante;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getAmperage() {
        return amperage;
    }

    public void setAmperage(double amperage) {
        this.amperage = amperage;
    }

    public double getVolt() {
        return volt;
    }

    public void setVolt(double volt) {
        this.volt = volt;
    }

    public double getResistance() {
        return resistance;
    }

    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Composante[] getTabAutour() {
        return tabAutour;
    }

    public void setTabAutour(Composante[] tabAutour) {
        this.tabAutour = tabAutour;
    }

    public Image getRealImage() {
        return realImage;
    }

    public void setRealImage(Image realImage) {
        this.realImage = realImage;
    }

    public boolean isEnPlace() {
        return enPlace;
    }

    public void setEnPlace(boolean enPlace) {
        this.enPlace = enPlace;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getSensCourant() {
        return sensCourant;
    }

    public void setSensCourant(String sensCourant) {
        this.sensCourant = sensCourant;
    }
}