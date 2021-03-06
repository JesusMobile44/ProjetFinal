package composantes;

import autre.ImagesContainer;
import controllers.SandboxController;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import main.Main;

public class Composante extends ImageView {
    Image[] tabVariante;
    String[] tabNomVariante;
    int direction;
    private double amperage;
    double volt;
    double resistance;
    double capacite;
    String description;
    Tooltip tooltip;
    String nom;
    private Composante[] tabAutour;
    boolean enPlace;
    int row = 0;
    int col = 0;
    private String sensCourant;

    Composante() {
        this.direction = 0;
        this.amperage = 0;
        this.volt = 0;
        this.resistance = 0;
        this.tabAutour = new Composante[4];
        this.enPlace = false;
        this.sensCourant = "∅";

        this.setOnMouseClicked(event -> {
            if (SandboxController.xPressed) {
                SandboxController.supprimer(this);
            }
            SandboxController.textDescription.setText(this.getDescription());
            if (this instanceof ComposanteVide)
                SandboxController.goBack();
            else
                SandboxController.changerMenuComposante(this);

        });

        this.setOnMouseEntered(event -> {
            if (Main.numeroMode == 1) {
                Label label = new Label(this.getDescription());
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
                if (source.isEnPlace() && target.isEnPlace() && !SandboxController.cPressed) {

                    if (Main.numeroMode == 1) {
                        SandboxController.echangerComposantes(posSource, posTarget, source, target);
                    }

                    source.enPlace = true;
                    event.setDropCompleted(true);

                } else if (source.isEnPlace() && target.isEnPlace() && SandboxController.cPressed) {
                    SandboxController.copierComposante(source, target);
                } else if (target.isEnPlace()) {
                    if (Main.numeroMode == 1) {
                        SandboxController.remettreComposante(source);
                        SandboxController.placerComposantes(source, target);
                    }
                    source.enPlace = true;
                    event.setDropCompleted(true);
                }
            }


        });
        this.setOnDragDone(event -> SandboxController.updateCircuit());
    }

    void initializeImage() {
        for (int i = 0; i < tabNomVariante.length; i++) {
            tabVariante[i] = ImagesContainer.getHashMapImage().get(nom.toLowerCase() + " (" + (i + 1) + ").png");
        }
        this.setImage(tabVariante[0]);
        this.setFitHeight(100);
        this.setFitWidth(100);
    }

    public static void bindTooltip(final Node node, final Tooltip tooltip){
        node.setOnMouseMoved(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                // +15 moves the tooltip 15 pixels below the mouse cursor;
                // if you don't change the y coordinate of the tooltip, you
                // will see constant screen flicker
                tooltip.show(node, event.getScreenX(), event.getScreenY() + 15);
            }
        });
        node.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                tooltip.hide();
            }
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

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
    }

    private String getDescription() {
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

    private boolean isEnPlace() {
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