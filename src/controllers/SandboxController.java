package controllers;

import composantes.*;
import concepts.Branche;
import concepts.Circuit;
import concepts.Noeud;
import concepts.NouvelleMaille;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import main.Main;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;


public class SandboxController {

    public static GridPane gridPaneSandBox = new GridPane();
    private static Button backButtonStatic = new Button();
    private static FlowPane rootScrollPane = new FlowPane();
    public static DecimalFormat df = new DecimalFormat("#.##");
    private static ArrayList<Circuit> circuits = new ArrayList<>();
    public static Text textDescription = new Text();
    private static boolean menuTouteComposantes = true;
    private static ArrayList<Composante> composantes = new ArrayList<>();
    public static boolean cPressed = false;
    public static boolean xPressed = false;

    @FXML
    private HBox myHBox;

    @FXML
    private ScrollPane scroll;

    @FXML
    private ScrollPane myScrollPane;

    @FXML
    private Button backButton;

    @FXML
    private SplitPane mySplitPane;

    @FXML
    private SplitPane mySecondSplitPane;

    @FXML
    public void initialize() {
        scroll.setContent(gridPaneSandBox);
        textDescription.setText("Cliquer sur une composante pour afficher sa description");
        textDescription.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        textDescription.setFill(Color.WHITE);
        textDescription.setTextAlignment(TextAlignment.CENTER);
        myHBox.getChildren().add(textDescription);
        myHBox.setPadding(new Insets(16));
        gridPaneSandBox.setPrefSize(376, 414);
        backButtonStatic = backButton;
        backButtonStatic.setDisable(true);
        backButtonStatic.setOpacity(0);
        backButtonStatic.setOnAction(event -> goBack());

        mySecondSplitPane.maxWidthProperty().bind(mySplitPane.widthProperty().multiply(0.30));
        myHBox.maxHeightProperty().bind(mySecondSplitPane.heightProperty().multiply(0.25));
        textDescription.wrappingWidthProperty().bind(mySecondSplitPane.widthProperty().multiply(0.90));


        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                creerComposanteVide(i, j);

        goBack();
        rootScrollPane.setPadding(new Insets(16));
        rootScrollPane.setVgap(16);
        rootScrollPane.setHgap(16);
        myScrollPane.setContent(rootScrollPane);
    }

    public static void goBack() {
        textDescription.setText("Cliquer sur une composante pour afficher sa description");
        backButtonStatic.setOpacity(0);
        backButtonStatic.setDisable(true);
        rootScrollPane.getChildren().clear();
        rootScrollPane.getChildren().add(new Fil());
        rootScrollPane.getChildren().add(new Amperemetre());
        rootScrollPane.getChildren().add(new Ampoule());
        rootScrollPane.getChildren().add(new Source());
        rootScrollPane.getChildren().add(new Diode());
        rootScrollPane.getChildren().add(new Fusible());
        rootScrollPane.getChildren().add(new HautParleur());
        rootScrollPane.getChildren().add(new Interrupteur());
        rootScrollPane.getChildren().add(new MiseAterre());
        rootScrollPane.getChildren().add(new Ohmetre());
        rootScrollPane.getChildren().add(new Resisteur());
        rootScrollPane.getChildren().add(new Voltmetre());
        rootScrollPane.getChildren().add(new Switch());
        rootScrollPane.getChildren().add(new Condensateur());
        menuTouteComposantes = true;
    }

    public static void changerMenuComposante(Composante composante) {
        String nom = composante.getNom();
        menuTouteComposantes = false;
        rootScrollPane.getChildren().clear();
        backButtonStatic.setDisable(false);
        backButtonStatic.setOpacity(100);
        Image back = new Image("autre/images/back (1).png");
        backButtonStatic.setGraphic(new ImageView(back));
        switch (nom.toUpperCase()) {
            case "AMPÈREMÈTRE":
                rootScrollPane.getChildren().add(0, new Amperemetre());
                Amperemetre amperemetre1 = new Amperemetre();
                amperemetre1.setImage(amperemetre1.getTabVariante()[1]);
                amperemetre1.setDirection(1);
                rootScrollPane.getChildren().add(1, amperemetre1);
                //updateCircuit();
                break;
            case "AMPOULE":
                rootScrollPane.getChildren().add(0, new Ampoule());
                Ampoule ampoule1 = new Ampoule();
                ampoule1.setImage(ampoule1.getTabVariante()[1]);
                ampoule1.setDirection(1);
                rootScrollPane.getChildren().add(1, ampoule1);
                //updateCircuit();
                break;
            case "SOURCE":
                rootScrollPane.getChildren().add(0, new Source());
                Source source = new Source();
                source.setImage(source.getTabVariante()[1]);
                source.setDirection(1);
                rootScrollPane.getChildren().add(1, source);
                Source source1 = new Source();
                source1.setImage(source.getTabVariante()[2]);
                source1.setDirection(2);
                rootScrollPane.getChildren().add(2, source1);
                Source source2 = new Source();
                source2.setImage(source.getTabVariante()[3]);
                source2.setDirection(3);
                rootScrollPane.getChildren().add(3, source2);
                //updateCircuit();
                break;
            case "DIODE":
                rootScrollPane.getChildren().add(0, new Diode());
                Diode diode = new Diode();
                diode.setImage(diode.getTabVariante()[1]);
                diode.setDirection(1);
                rootScrollPane.getChildren().add(1, diode);
                Diode diode1 = new Diode();
                diode1.setImage(diode.getTabVariante()[2]);
                diode1.setDirection(2);
                rootScrollPane.getChildren().add(1, diode1);
                Diode diode2 = new Diode();
                diode2.setImage(diode.getTabVariante()[3]);
                diode2.setDirection(3);
                rootScrollPane.getChildren().add(1, diode2);
                //updateCircuit();
                break;
            case "FIL":
                rootScrollPane.getChildren().add(0, new Fil());
                Fil fil = new Fil();
                fil.setImage(fil.getTabVariante()[1]);
                fil.setDirection(1);
                rootScrollPane.getChildren().add(1, fil);
                Fil fil1 = new Fil();
                fil1.setImage(fil1.getTabVariante()[2]);
                fil1.setDirection(2);
                rootScrollPane.getChildren().add(2, fil1);
                Fil fil2 = new Fil();
                fil2.setImage(fil2.getTabVariante()[3]);
                fil2.setDirection(3);
                rootScrollPane.getChildren().add(3, fil2);
                Fil fil3 = new Fil();
                fil3.setImage(fil3.getTabVariante()[4]);
                fil3.setDirection(4);
                rootScrollPane.getChildren().add(4, fil3);
                Fil fil4 = new Fil();
                fil4.setImage(fil4.getTabVariante()[5]);
                fil4.setDirection(5);
                rootScrollPane.getChildren().add(5, fil4);
                Fil fil5 = new Fil();
                fil5.setImage(fil5.getTabVariante()[6]);
                fil5.setDirection(6);
                rootScrollPane.getChildren().add(6, fil5);
                Fil fil6 = new Fil();
                fil6.setImage(fil6.getTabVariante()[7]);
                fil6.setDirection(7);
                rootScrollPane.getChildren().add(7, fil6);
                Fil fil7 = new Fil();
                fil7.setImage(fil7.getTabVariante()[8]);
                fil7.setDirection(8);
                rootScrollPane.getChildren().add(8, fil7);
                Fil fil8 = new Fil();
                fil8.setImage(fil8.getTabVariante()[9]);
                fil8.setDirection(9);
                rootScrollPane.getChildren().add(9, fil8);
                Fil fil9 = new Fil();
                fil9.setImage(fil9.getTabVariante()[10]);
                fil9.setDirection(10);
                rootScrollPane.getChildren().add(10, fil9);
                //updateCircuit();
                break;
            case "FUSIBLE":
                rootScrollPane.getChildren().add(0, new Fusible());
                Fusible fusible = new Fusible();
                fusible.setImage(fusible.getTabVariante()[1]);
                fusible.setDirection(1);
                rootScrollPane.getChildren().add(1, fusible);
                //updateCircuit();
                break;
            case "HAUT-PARLEUR":
                rootScrollPane.getChildren().add(0, new HautParleur());
                HautParleur hautParleur = new HautParleur();
                hautParleur.setImage(hautParleur.getTabVariante()[1]);
                hautParleur.setDirection(1);
                rootScrollPane.getChildren().add(1, hautParleur);
                //updateCircuit();
                break;
            case "INTERRUPTEUR":
                rootScrollPane.getChildren().add(0, new Interrupteur());
                Interrupteur interrupteur = new Interrupteur();
                interrupteur.setImage(interrupteur.getTabVariante()[2]);
                interrupteur.setDirection(2);
                rootScrollPane.getChildren().add(1, interrupteur);
                //updateCircuit();
                break;
            case "MISE À TERRE":
                rootScrollPane.getChildren().add(0, new MiseAterre());
                MiseAterre miseAterre = new MiseAterre();
                miseAterre.setImage(miseAterre.getTabVariante()[1]);
                miseAterre.setDirection(1);
                rootScrollPane.getChildren().add(1, miseAterre);
                MiseAterre miseAterre1 = new MiseAterre();
                miseAterre1.setImage(miseAterre1.getTabVariante()[2]);
                miseAterre1.setDirection(2);
                rootScrollPane.getChildren().add(2, miseAterre1);
                MiseAterre miseAterre2 = new MiseAterre();
                miseAterre2.setImage(miseAterre2.getTabVariante()[3]);
                miseAterre2.setDirection(3);
                rootScrollPane.getChildren().add(3, miseAterre2);
                //updateCircuit();
                break;
            case "CONDENSATEUR":
                rootScrollPane.getChildren().add(0, new Condensateur());
                Condensateur condensateur = new Condensateur();
                condensateur.setImage(condensateur.getTabVariante()[1]);
                condensateur.setDirection(1);
                rootScrollPane.getChildren().add(1, condensateur);
                //updateCircuit();
                break;
            case "OHMMÈTRE":
                rootScrollPane.getChildren().add(0, new Ohmetre());
                Ohmetre ohmetre = new Ohmetre();
                ohmetre.setImage(ohmetre.getTabVariante()[1]);
                ohmetre.setDirection(1);
                rootScrollPane.getChildren().add(1, ohmetre);
                //updateCircuit();
                break;
            case "RÉSISTEUR":
                rootScrollPane.getChildren().add(0, new Resisteur());
                Resisteur resisteur = new Resisteur();
                resisteur.setImage(resisteur.getTabVariante()[1]);
                resisteur.setDirection(1);
                rootScrollPane.getChildren().add(1, resisteur);
                //updateCircuit();
                break;
            case "VOLTMÈTRE":
                rootScrollPane.getChildren().add(0, new Voltmetre());
                Voltmetre voltmetre = new Voltmetre();
                voltmetre.setImage(voltmetre.getTabVariante()[1]);
                voltmetre.setDirection(1);
                rootScrollPane.getChildren().add(1, voltmetre);
                //updateCircuit();
                break;
            case "SWITCH":
                rootScrollPane.getChildren().add(0, new Switch());
                Switch switch1 = new Switch();
                switch1.setImage(switch1.getTabVariante()[2]);
                switch1.setDirection(2);
                rootScrollPane.getChildren().add(1, switch1);
                Switch switch2 = new Switch();
                switch2.setImage(switch2.getTabVariante()[4]);
                switch2.setDirection(4);
                rootScrollPane.getChildren().add(2, switch2);
                Switch switch3 = new Switch();
                switch3.setImage(switch3.getTabVariante()[6]);
                switch3.setDirection(6);
                rootScrollPane.getChildren().add(3, switch3);
                //updateCircuit();
                break;
        }
    }

    public void setMenu() {
        Main.changerDeMode(0);
    }

    public void setGuide() {
        Main.getStage().maxHeightProperty().bind(Main.getStage().widthProperty().multiply(1500 / 1920));
        Main.getStage().minHeightProperty().bind(Main.getStage().widthProperty().multiply(1920 / 1080));
        Main.changerDeMode(2);
    }

    @FXML
    private void clear() {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                gridPaneSandBox.getChildren().remove(getNodeFromGridPane(gridPaneSandBox, i, j));
                creerComposanteVide(i, j);
            }
        updateCircuit();

    }

    private static void clearLoading() {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                gridPaneSandBox.getChildren().remove(getNodeFromGridPane(gridPaneSandBox, i, j));
                creerComposanteVide(i, j);
            }
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

    public static void placerComposantes(Composante source, Composante target) {

        int i = target.getCol();
        int j = target.getRow();
        gridPaneSandBox.getChildren().remove(target);
        source.setCol(i);
        source.setRow(j);
        if (!source.getNom().toUpperCase().equals("VIDE")) {
            source.getTooltip().setText(source.getNom() + " (" + source.getCol() + "," + source.getRow() + ")\nIntensité: " + df.format(source.getAmperage()) + "\nTension: " + df.format(source.getVolt()) + "\nRésistance: " + df.format(source.getResistance()));
            ContextMenu contextMenu = new ContextMenu();
            MenuItem itemSupprimer = new MenuItem("Supprimer");
            if (!source.getNom().toUpperCase().equals("SWITCH") && !source.getNom().toUpperCase().equals("INTERRUPTEUR")) {
                Menu menuVariante = new Menu("Variantes");
                MenuItem[] itemVariante = new MenuItem[source.getTabNomVariante().length];

                for (int k = 0; k < itemVariante.length; k++) {
                    itemVariante[k] = new MenuItem(source.getTabNomVariante()[k]);
                    menuVariante.getItems().add(itemVariante[k]);
                    int temp = k;
                    itemVariante[k].setOnAction(event -> {
                        source.setImage(source.getTabVariante()[temp]);
                        source.setDirection(temp);
                        updateCircuit();
                    });
                }
                contextMenu.getItems().add(menuVariante);
            }

            switch (source.getNom().toUpperCase()) {
                case "SOURCE":
                    MenuItem itemTension = new MenuItem("Modifier la tension");
                    itemTension.setOnAction(event -> {
                        changerValeur(source, "la tension");
                        updateCircuit();
                    });
                    contextMenu.getItems().addAll(itemTension);
                    break;
                case "RÉSISTEUR":
                    MenuItem itemResistance = new MenuItem("Modifier la résistance");
                    itemResistance.setOnAction(event -> {
                        changerValeur(source, "la résistance");
                        updateCircuit();
                    });
                    contextMenu.getItems().add(itemResistance);
                    break;
                case "CONDENSATEUR":
                    MenuItem itemCapacite = new MenuItem("Modifier la capacité");
                    itemCapacite.setOnAction(event -> {
                        changerValeur(source, "la capacité");
                        updateCircuit();
                    });
                    contextMenu.getItems().add(itemCapacite);
                    break;
                case "HAUT-PARLEUR":
                    MenuItem itemMusique = new MenuItem("Modifier la musique");
                    itemMusique.setOnAction(event -> {
                        MediaPlayer mediaPlayer = ((HautParleur) source).getMediaPlayer();
                        FileChooser fc = new FileChooser();
                        fc.setInitialDirectory(new File("src/autre/musiques"));
                        fc.setTitle("Veuillez sélectionner un fichier");
                        ((HautParleur) source).setMediaPlayer(new MediaPlayer(new Media(fc.showOpenDialog(Main.getStage()).toURI().toString())));
                        if (((ComposanteActivable) source).isActive()) {
                            mediaPlayer.stop();
                            ((HautParleur) source).getMediaPlayer().play();
                        }
                        updateCircuit();
                    });
                    contextMenu.getItems().add(itemMusique);
                    break;
                case "INTERRUPTEUR":
                    MenuItem itemSwitch = new MenuItem("ON/OFF");
                    itemSwitch.setOnAction(event -> {
                        switch (source.getDirection()) {
                            case 0:
                                source.setDirection(1);
                                source.setImage(source.getTabVariante()[1]);
                                break;
                            case 1:
                                source.setDirection(0);
                                source.setImage(source.getTabVariante()[0]);
                                break;
                            case 2:
                                source.setDirection(3);
                                source.setImage(source.getTabVariante()[3]);
                                break;
                            case 3:
                                source.setDirection(2);
                                source.setImage(source.getTabVariante()[2]);
                                break;
                        }
                        updateCircuit();
                    });
                    contextMenu.getItems().add(itemSwitch);
                    break;
                case "SWITCH":
                    MenuItem itemONOFF = new MenuItem("Switch");
                    itemONOFF.setOnAction(event -> {
                        switch (source.getDirection()) {
                            case 0:
                                source.setDirection(1);
                                source.setImage(source.getTabVariante()[1]);
                                break;
                            case 1:
                                source.setDirection(0);
                                source.setImage(source.getTabVariante()[0]);
                                break;
                            case 2:
                                source.setDirection(3);
                                source.setImage(source.getTabVariante()[3]);
                                break;
                            case 3:
                                source.setDirection(2);
                                source.setImage(source.getTabVariante()[2]);
                                break;
                            case 4:
                                source.setDirection(5);
                                source.setImage(source.getTabVariante()[5]);
                                break;
                            case 5:
                                source.setDirection(4);
                                source.setImage(source.getTabVariante()[4]);
                                break;
                            case 6:
                                source.setDirection(7);
                                source.setImage(source.getTabVariante()[7]);
                                break;
                            case 7:
                                source.setDirection(6);
                                source.setImage(source.getTabVariante()[6]);
                                break;
                        }
                        updateCircuit();
                    });
                    contextMenu.getItems().add(itemONOFF);
                    break;
            }

            itemSupprimer.setOnAction(event -> {
                supprimer(source);
            });
            contextMenu.getItems().add(itemSupprimer);
            source.setOnContextMenuRequested(event -> contextMenu.show(source, event.getScreenX(), event.getScreenY()));
        }
        gridPaneSandBox.add(source, i, j);
    }

    public static void supprimer(Composante source) {
        gridPaneSandBox.getChildren().remove(source);
        ComposanteVide vide = new ComposanteVide();
        vide.fitHeightProperty().set(100);
        vide.fitWidthProperty().set(100);
        vide.setRow(source.getRow());
        vide.setCol(source.getCol());
        gridPaneSandBox.add(vide, source.getCol(), source.getRow());
        updateCircuit();
    }

    public static void remettreComposante(Composante composante) {
        String nom = composante.getNom().toUpperCase();
        switch (nom) {
            case "FIL":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new Fil());
                } else {
                    goBack();
                }
                break;
            case "AMPÈREMÈTRE":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new Amperemetre());
                } else {
                    goBack();
                }
                break;
            case "AMPOULE":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new Ampoule());
                } else {
                    goBack();
                }
                break;
            case "SOURCE":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new Source());
                } else {
                    goBack();
                }
                break;
            case "DIODE":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new Diode());
                } else {
                    goBack();
                }
                break;
            case "FUSIBLE":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new Fusible());
                } else {
                    goBack();
                }
                break;
            case "INTERRUPTEUR":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new Interrupteur());
                } else {
                    goBack();
                }
                break;
            case "MISE À TERRE":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new MiseAterre());
                } else {
                    goBack();
                }
                break;
            case "OHMMÈTRE":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new Ohmetre());
                } else {
                    goBack();
                }
                break;
            case "RÉSISTEUR":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new Resisteur());
                } else {
                    goBack();
                }
                break;
            case "VOLTMÈTRE":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new Voltmetre());
                } else {
                    goBack();
                }
                break;
            case "SWITCH":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new Switch());
                } else {
                    goBack();
                }
                break;
            case "HAUT-PARLEUR":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new HautParleur());
                } else {
                    goBack();
                }
                break;
            case "CONDENSATEUR":
                if (!menuTouteComposantes) {
                    changerMenuComposante(new Condensateur());
                } else {
                    goBack();
                }
                break;
        }
    }

    public static void copierComposante(Composante source, Composante target) {
        Composante copie = null;
        switch (source.getNom().toUpperCase()) {
            case "FIL":
                copie = new Fil(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "AMPÈREMÈTRE":
                copie = new Amperemetre(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "AMPOULE":
                copie = new Ampoule(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "SOURCE":
                copie = new Source(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "DIODE":
                copie = new Diode(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "FUSIBLE":
                copie = new Fusible(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "INTERRUPTEUR":
                copie = new Interrupteur(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "MISE À TERRE":
                copie = new MiseAterre(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "OHMMÈTRE":
                copie = new Ohmetre(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "RÉSISTEUR":
                copie = new Resisteur(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "VOLTMÈTRE":
                copie = new Voltmetre(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "SWITCH":
                copie = new Switch(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "HAUT-PARLEUR":
                copie = new HautParleur(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
            case "CONDENSATEUR":
                copie = new Condensateur(new ComposanteSave(source), target.getRow(), target.getCol());
                break;
        }
        gridPaneSandBox.getChildren().remove(target);
        gridPaneSandBox.add(copie, copie.getCol(), copie.getRow());
        //gridPaneSandBox.add(source,source.getRow(),source.getCol());
    }

    public static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public static void updateCircuit() {

        resetGridPane();

        circuits.clear();

        boolean debutFound = true;

        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {

                Composante debut = ((Composante) getNodeFromGridPane(gridPaneSandBox, i, j));

                if (debut.getNom().toUpperCase().equals("SOURCE")) {

                    for (int k = 0; k < circuits.size(); k++) {
                        for (int l = 0; l < circuits.get(k).getSources().size(); l++) {
                            if (debut.getRow() == circuits.get(k).getSources().get(l).getRow() && debut.getCol() == circuits.get(k).getSources().get(l).getCol()) {
                                debutFound = false;
                            }
                        }
                    }

                    if (debutFound) {
                        Circuit newCircuit = new Circuit();
                        newCircuit.getComposantes().add(debut);
                        newCircuit.getSources().add((Source) debut);
                        newCircuit.setEnSerie(true);
                        circuits.add(newCircuit);
                    }
                }
            }

        for (int z = 0; z < circuits.size(); z++) {


            boolean enDouble = false;

            for (Circuit circuit2 : circuits) {
                if (circuits.get(z) != circuit2) {
                    for (Source source1 : circuits.get(z).getSources()) {
                        for (Source source2 : circuit2.getSources()) {
                            if (source1.getRow() == source2.getRow() && source1.getCol() == source2.getCol()) {
                                circuits.remove(circuits.get(z));
                                enDouble = true;
                            }
                        }
                    }
                }
            }

            if (!enDouble) {

                for (int i = 0; i < circuits.get(z).getNoeuds().size(); i++) {
                    ((Fil) circuits.get(z).getNoeuds().get(i).getComposanteNoeud()).setNoeud(false);
                }

                if (circuits.get(z).isEnSerie()) {
                    creerSerie(z);
                }
                if (!circuits.get(z).isEnSerie()) {
                    for (int j = 0; j < circuits.get(z).getNoeuds().size(); j++) {
                        circuits.get(z).getNoeuds().get(j).resetBranchesAnalysees();
                    }
                    Noeud initial = circuits.get(z).getNoeuds().get(0);
                    Circuit circuit1 = new Circuit();
                    circuit1.setEnSerie(false);
                    circuit1.setIncomplet(false);

                    circuits.add(z, circuit1);
                    circuits.remove(z + 1);

                    creerNoeuds(initial, z);
                    if (!circuits.get(z).isIncomplet()) {
                        creerBranches(z);
                        creerMailles(z);
                    }
                }

                remplirCircuit(z);

                if (!circuits.get(z).isIncomplet()) {
                    circuits.get(z).calculVariables();
                    for (int i = 0; i < circuits.get(z).getComposantes().size(); i++) {
                        if (circuits.get(z).getComposantes().get(i).getNom().toUpperCase().equals("HAUT-PARLEUR") || circuits.get(z).getComposantes().get(i).getNom().toUpperCase().equals("AMPOULE")) {
                            boolean unique = true;
                            for (Composante composante : composantes)
                                if (circuits.get(z).getComposantes().get(i).getCol() == composante.getCol() && circuits.get(z).getComposantes().get(i).getRow() == composante.getRow())
                                    unique = false;
                            if (unique)
                                composantes.add(circuits.get(z).getComposantes().get(i));
                        }
                    }
                }


                for (int i = composantes.size() - 1; i > -1; i--) {
                    if (composantes.get(i).getNom().toUpperCase().equals("HAUT-PARLEUR")) {
                        if (!circuits.get(z).isIncomplet() && !((ComposanteActivable) composantes.get(i)).isActive() && composantes.get(i).getAmperage() > 0) {
                            ((HautParleur) composantes.get(i)).getMediaPlayer().play();
                            ((ComposanteActivable) composantes.get(i)).setActive(true);
                        } else if (circuits.get(z).isIncomplet() && ((ComposanteActivable) composantes.get(i)).isActive() || composantes.get(i).getAmperage() == 0) {
                            ((HautParleur) composantes.get(i)).getMediaPlayer().stop();
                            ((ComposanteActivable) composantes.get(i)).setActive(false);
                            composantes.remove(i);
                        }
                    } else if (composantes.get(i).getNom().toUpperCase().equals("AMPOULE")) {
                        if (!circuits.get(z).isIncomplet() && !((ComposanteActivable) composantes.get(i)).isActive() && composantes.get(i).getAmperage() > 0) {
                            switch (composantes.get(i).getTabNomVariante()[composantes.get(i).getDirection()]) {
                                case "NS":
                                    composantes.get(i).setImage(composantes.get(i).getTabVariante()[2]);
                                    break;
                                case "OE":
                                    composantes.get(i).setImage(composantes.get(i).getTabVariante()[3]);
                                    break;
                            }
                            ((ComposanteActivable) composantes.get(i)).setActive(true);
                        } else if (circuits.get(z).isIncomplet() && ((ComposanteActivable) composantes.get(i)).isActive() || composantes.get(i).getAmperage() == 0) {
                            switch (composantes.get(i).getTabNomVariante()[composantes.get(i).getDirection()]) {
                                case "NS":
                                    composantes.get(i).setImage(composantes.get(i).getTabVariante()[0]);
                                    break;
                                case "OE":
                                    composantes.get(i).setImage(composantes.get(i).getTabVariante()[1]);
                                    break;
                            }
                            ((ComposanteActivable) composantes.get(i)).setActive(false);
                            composantes.remove(i);
                        }
                    }
                }
            }
        }
    }

    private static void creerComposanteVide(int i, int j) {
        ComposanteVide vide = new ComposanteVide();
        vide.setRow(j);
        vide.setCol(i);
        gridPaneSandBox.add(vide, i, j);
    }

    private static void creerMailles(int numeroDeCircuit) {

        for (int i = 0; i < circuits.get(numeroDeCircuit).getNoeuds().size(); i++) {
            circuits.get(numeroDeCircuit).getNoeuds().get(i).updateBranchesAnalysees();
        }

        for (int i = 0; i < circuits.get(numeroDeCircuit).getBranches().size(); i++) {

            boolean finished = false;
            boolean error = false;
            boolean mono = false;
            NouvelleMaille mailleTemporaire = new NouvelleMaille();
            Noeud noeudTemporaire = circuits.get(numeroDeCircuit).getBranches().get(i).getNoeudsAdjacents().get(0);
            Branche brancheTemporaire = circuits.get(numeroDeCircuit).getBranches().get(i);
            Branche brancheInitiale = circuits.get(numeroDeCircuit).getBranches().get(i);
            mailleTemporaire.getNoeudsMaille().add(noeudTemporaire);
            mailleTemporaire.getBranchesMaille().add(circuits.get(numeroDeCircuit).getBranches().get(i));
            int monoBranche = 0;

            for (int k = 0; k < noeudTemporaire.getBranchesAdjacentes().size(); k++) {
                if (noeudTemporaire.getBranchesAdjacentes().get(k) == brancheTemporaire) {
                    monoBranche++;
                }
            }


            if (monoBranche >= 2) {
                mono = true;
            }

            if (!mono) {
                for (int j = 0; j < circuits.get(numeroDeCircuit).getNoeuds().size(); j++) {
                    circuits.get(numeroDeCircuit).getNoeuds().get(j).resetBranchesAnalysees();
                }
                //

                for (int j = 0; j < noeudTemporaire.getBranchesAdjacentes().size(); j++) {

                    if (noeudTemporaire.getBranchesAdjacentes().get(j) != brancheTemporaire) {

                        if (!noeudTemporaire.getBranchesAnalysees()[j]) {

                            brancheTemporaire = noeudTemporaire.getBranchesAdjacentes().get(j);
                            noeudTemporaire.getBranchesAnalysees()[j] = true;

                            //if (brancheTemporaire != null){
                                if (brancheTemporaire.getNoeudsAdjacents().get(0) == noeudTemporaire) {
                                    noeudTemporaire = brancheTemporaire.getNoeudsAdjacents().get(1);
                                } else {
                                    noeudTemporaire = brancheTemporaire.getNoeudsAdjacents().get(0);
                                }

                                mailleTemporaire.getBranchesMaille().add(brancheTemporaire);
                                mailleTemporaire.getNoeudsMaille().add(noeudTemporaire);

                                for (int k = 0; k < noeudTemporaire.getBranchesAdjacentes().size(); k++) {
                                    for (int l = 0; l < mailleTemporaire.getBranchesMaille().size(); l++) {
                                        if (noeudTemporaire.getBranchesAdjacentes().get(k) == mailleTemporaire.getBranchesMaille().get(l)
                                                && noeudTemporaire.getBranchesAdjacentes().get(k) != brancheTemporaire
                                                && noeudTemporaire.getBranchesAdjacentes().get(k) != brancheInitiale) {
                                            error = true;
                                        }
                                    }
                                }
                                /*
                            }else {
                                error = true;
                            }
                            */

                            if (!error) {
                                for (int k = 0; k < noeudTemporaire.getBranchesAdjacentes().size(); k++) {
                                    if (noeudTemporaire.getBranchesAdjacentes().get(k) == brancheInitiale) {
                                        finished = true;
                                    }
                                }
                            }


                            if (finished) {

                                circuits.get(numeroDeCircuit).getMailles().add(new NouvelleMaille(
                                        mailleTemporaire.getComposantesMaille(),
                                        mailleTemporaire.getResisteurs(),
                                        mailleTemporaire.getNoeudsMaille(),
                                        mailleTemporaire.getBranchesMaille(),
                                        mailleTemporaire.getSources()
                                ));

                                noeudTemporaire.resetBranchesAnalysees();
                                mailleTemporaire.getNoeudsMaille().remove(mailleTemporaire.getNoeudsMaille().size() - 1);
                                noeudTemporaire = mailleTemporaire.getNoeudsMaille().get(mailleTemporaire.getNoeudsMaille().size() - 1);
                                mailleTemporaire.getBranchesMaille().remove(mailleTemporaire.getBranchesMaille().size() - 1);
                                brancheTemporaire = mailleTemporaire.getBranchesMaille().get(mailleTemporaire.getBranchesMaille().size() - 1);
                                j = -1;
                                finished = false;
                                //}


                            } else if (error) {
                                mailleTemporaire.getNoeudsMaille().remove(mailleTemporaire.getNoeudsMaille().size() - 1);
                                noeudTemporaire = mailleTemporaire.getNoeudsMaille().get(mailleTemporaire.getNoeudsMaille().size() - 1);
                                mailleTemporaire.getBranchesMaille().remove(mailleTemporaire.getBranchesMaille().size() - 1);
                                brancheTemporaire = mailleTemporaire.getBranchesMaille().get(mailleTemporaire.getBranchesMaille().size() - 1);
                                j = -1;
                                error = false;
                            }


                        }
                    } else {
                        noeudTemporaire.getBranchesAnalysees()[j] = true;
                    }

                    boolean changer = false;
                    try {
                        boolean reculer = true;
                        boolean good = false;
                        while (!good) {
                            for (int k = 0; k < noeudTemporaire.getBranchesAnalysees().length; k++) {
                                if (!noeudTemporaire.getBranchesAnalysees()[k]) {
                                    reculer = false;
                                }
                            }
                            if (reculer) {
                                noeudTemporaire.resetBranchesAnalysees();
                                mailleTemporaire.getNoeudsMaille().remove(mailleTemporaire.getNoeudsMaille().size() - 1);
                                noeudTemporaire = mailleTemporaire.getNoeudsMaille().get(mailleTemporaire.getNoeudsMaille().size() - 1);
                                mailleTemporaire.getBranchesMaille().remove(mailleTemporaire.getBranchesMaille().size() - 1);
                                brancheTemporaire = mailleTemporaire.getBranchesMaille().get(mailleTemporaire.getBranchesMaille().size() - 1);
                                j = -1;
                            } else {
                                good = true;
                            }
                        }
                    } catch (Exception e) {
                        noeudTemporaire = new Noeud(new Fil());
                        changer = true;
                    }

                    if (changer) {
                        j = 1000;
                    }
                }
            } else {
                circuits.get(numeroDeCircuit).getMailles().add(new NouvelleMaille(
                        mailleTemporaire.getComposantesMaille(),
                        mailleTemporaire.getResisteurs(),
                        mailleTemporaire.getNoeudsMaille(),
                        mailleTemporaire.getBranchesMaille(),
                        mailleTemporaire.getSources()
                ));
            }
        }


        if (circuits.get(numeroDeCircuit).getMailles().size()>=2){
            arrangerMailles(numeroDeCircuit);
        }

        circuits.get(numeroDeCircuit).setIncomplet(false);
        System.out.println("Mailles Complètes");
    }

    private static void arrangerMailles(int numeroDeCircuit) {
        boolean done = false;
        boolean done2;
        int j;
        int i = 0;


            while (!done) {
                j = 0;
                done2 = false;
                while (!done2) {

                    NouvelleMaille maille1 = null;
                    NouvelleMaille maille2 = null;
                    maille1 = circuits.get(numeroDeCircuit).getMailles().get(i);
                    maille2 = circuits.get(numeroDeCircuit).getMailles().get(j);

                    boolean allTheSame = true;
                    boolean[] same = new boolean[maille1.getBranchesMaille().size()];
                    for (int k = 0; k < same.length; k++) {
                        same[k] = false;
                    }

                    if (i != j) {
                        if (maille1.getBranchesMaille().size() == maille2.getBranchesMaille().size()) {

                            for (int m = 0; m < maille1.getBranchesMaille().size(); m++) {
                                for (int k = 0; k < maille2.getBranchesMaille().size(); k++) {
                                    if (maille1.getBranchesMaille().get(m) == maille2.getBranchesMaille().get(k)) {
                                        same[m] = true;
                                    }
                                }
                            }

                            for (boolean b : same) {
                                if (!b) {
                                    allTheSame = false;
                                }
                            }

                            if (allTheSame) {
                                circuits.get(numeroDeCircuit).getMailles().remove(j);
                                j--;
                            }

                        }
                    }


                    j++;
                    if (j >= circuits.get(numeroDeCircuit).getMailles().size()) {
                        done2 = true;
                    }
                }

                i++;
                if (i >= circuits.get(numeroDeCircuit).getMailles().size()) {
                    done = true;
                }
            }


        for (int k = 0; k < circuits.get(numeroDeCircuit).getMailles().size(); k++) {
            NouvelleMaille mailleTempo = circuits.get(numeroDeCircuit).getMailles().get(k);
            for (int l = 0; l < mailleTempo.getBranchesMaille().size(); l++) {
                Branche brancheTempo = mailleTempo.getBranchesMaille().get(l);
                for (int m = 0; m < brancheTempo.getComposantesBranche().size(); m++) {
                    Composante composanteTempo = brancheTempo.getComposantesBranche().get(m);
                    mailleTempo.getComposantesMaille().add(composanteTempo);
                    if (composanteTempo.getNom().toUpperCase().equals("RESISTEUR")) {
                        mailleTempo.getResisteurs().add((Resisteur) composanteTempo);
                    }
                    if (composanteTempo.getNom().toUpperCase().equals("SOURCE")) {
                        mailleTempo.getSources().add((Source) composanteTempo);
                    }
                }
            }
        }
    }

    private static void creerNoeuds(Noeud initial, int numeroDeCircuit) {
        Noeud actuel;
        circuits.get(numeroDeCircuit).getNoeuds().add(initial);
        circuits.get(numeroDeCircuit).getComposantes().add(initial.getComposanteNoeud());
        boolean error = false;

        for (int i = 0; i < circuits.get(numeroDeCircuit).getNoeuds().size(); i++) {

            actuel = circuits.get(numeroDeCircuit).getNoeuds().get(i);

            for (int j = 0; j < actuel.getDirectionsAnalysees().length; j++) {

                int row = actuel.getComposanteNoeud().getRow();
                int col = actuel.getComposanteNoeud().getCol();
                String dir = null;

                if (!actuel.getDirectionsAnalysees()[j]) {

                    switch (actuel.getDirections()[j]) {
                        case "N":
                            dir = "up";
                            row--;
                            break;
                        case "E":
                            dir = "right";
                            col++;
                            break;
                        case "S":
                            dir = "down";
                            row++;
                            break;
                        case "O":
                            dir = "left";
                            col--;
                            break;
                    }
                    boolean finished = false;

                    while (!finished && !error) {


                        switch (dir) {
                            case "up":
                                switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                    case "NS":
                                        dir = "up";
                                        row--;
                                        break;
                                    case "SN":
                                        dir = "up";
                                        row--;
                                        break;
                                    case "SE":
                                        dir = "right";
                                        col++;
                                        break;
                                    case "SO":
                                        dir = "left";
                                        col--;
                                        break;
                                    case "NSE":
                                        finished = true;
                                        break;
                                    case "NSO":
                                        finished = true;
                                        break;
                                    case "SOE":
                                        finished = true;
                                        break;
                                    case "NSEO":
                                        finished = true;
                                        break;
                                    default:
                                        error = true;
                                        break;
                                }
                                break;
                            case "right":
                                switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                    case "NO":
                                        dir = "up";
                                        row--;
                                        break;
                                    case "SO":
                                        dir = "down";
                                        row++;
                                        break;
                                    case "OE":
                                        dir = "right";
                                        col++;
                                        break;
                                    case "EO":
                                        dir = "right";
                                        col++;
                                        break;
                                    case "NSO":
                                        finished = true;
                                        break;
                                    case "SOE":
                                        finished = true;
                                        break;
                                    case "NEO":
                                        finished = true;
                                        break;
                                    case "NSEO":
                                        finished = true;
                                        break;
                                    default:
                                        error = true;
                                        break;
                                }
                                break;
                            case "down":
                                switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                    case "NS":
                                        dir = "down";
                                        row++;
                                        break;
                                    case "SN":
                                        dir = "down";
                                        row++;
                                        break;
                                    case "NE":
                                        dir = "right";
                                        col++;
                                        break;
                                    case "NO":
                                        dir = "left";
                                        col--;
                                        break;
                                    case "NSE":
                                        finished = true;
                                        break;
                                    case "NSO":
                                        finished = true;
                                        break;
                                    case "NEO":
                                        finished = true;
                                        break;
                                    case "NSEO":
                                        finished = true;
                                        break;
                                    default:
                                        error = true;
                                        break;
                                }
                                break;
                            case "left":
                                switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                    case "NE":
                                        dir = "up";
                                        row--;
                                        break;
                                    case "SE":
                                        dir = "down";
                                        row++;
                                        break;
                                    case "OE":
                                        dir = "left";
                                        col--;
                                        break;
                                    case "EO":
                                        dir = "left";
                                        col--;
                                        break;
                                    case "NSE":
                                        finished = true;
                                        break;
                                    case "SOE":
                                        finished = true;
                                        break;
                                    case "NEO":
                                        finished = true;
                                        break;
                                    case "NSEO":
                                        finished = true;
                                        break;
                                    default:
                                        error = true;
                                        break;
                                }
                                break;
                        }
                    }

                    if (!error) {
                        if (getNodeFromGridPane(gridPaneSandBox, col, row) == actuel.getComposanteNoeud()) {
                            System.out.println("Revenu au départ");

                            changerDirectionsAnalysees(actuel, dir);

                        } else {
                            if (((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).isNoeud()) {
                                System.out.println("Rencontre un noeud existant");
                                Noeud rencontre = null;

                                for (int l = 0; l < circuits.get(numeroDeCircuit).getNoeuds().size(); l++) {
                                    if (getNodeFromGridPane(gridPaneSandBox, col, row) == circuits.get(numeroDeCircuit).getNoeuds().get(l).getComposanteNoeud()) {
                                        rencontre = circuits.get(numeroDeCircuit).getNoeuds().get(l);
                                    }
                                }

                                changerDirectionsAnalysees(rencontre, dir);

                            } else {
                                System.out.println("Nouveau noeud!");
                                Noeud tempo = new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row));

                                changerDirectionsAnalysees(tempo, dir);

                                ((Fil) tempo.getComposanteNoeud()).setNoeud(true);
                                circuits.get(numeroDeCircuit).getNoeuds().add(tempo);
                                circuits.get(numeroDeCircuit).getComposantes().add(tempo.getComposanteNoeud());

                            }
                        }

                        actuel.getDirectionsAnalysees()[j] = true;

                    } else {
                        //circuits.get(numeroDeCircuit).setIncomplet(true);
                        actuel.getDirectionsAnalysees()[j] = true;
                    }
                }
            }
        }
        System.out.println(" ");
    }

    private static void changerDirectionsAnalysees(Noeud noeud, String dir) {
        switch (dir) {
            case "up":
                for (int k = 0; k < noeud.getDirections().length; k++) {
                    if (noeud.getDirections()[k].equals("S")) {
                        noeud.getDirectionsAnalysees()[k] = true;
                    }
                }
                break;
            case "down":
                for (int k = 0; k < noeud.getDirections().length; k++) {
                    if (noeud.getDirections()[k].equals("N")) {
                        noeud.getDirectionsAnalysees()[k] = true;
                    }
                }
                break;
            case "left":
                for (int k = 0; k < noeud.getDirections().length; k++) {
                    if (noeud.getDirections()[k].equals("E")) {
                        noeud.getDirectionsAnalysees()[k] = true;
                    }
                }
                break;
            case "right":
                for (int k = 0; k < noeud.getDirections().length; k++) {
                    if (noeud.getDirections()[k].equals("O")) {
                        noeud.getDirectionsAnalysees()[k] = true;
                    }
                }
                break;
        }
    }

    private static void creerBranches(int numeroDeCircuit) {

        for (int i = 0; i < circuits.get(numeroDeCircuit).getNoeuds().size(); i++) {
            for (int j = 0; j < circuits.get(numeroDeCircuit).getNoeuds().get(i).getDirectionsAnalysees().length; j++) {
                circuits.get(numeroDeCircuit).getNoeuds().get(i).getDirectionsAnalysees()[j] = false;
            }
        }

        boolean error = false;

        for (int i = 0; i < circuits.get(numeroDeCircuit).getNoeuds().size(); i++) {

            Noeud actuel = circuits.get(numeroDeCircuit).getNoeuds().get(i);

            for (int j = 0; j < actuel.getDirectionsAnalysees().length; j++) {

                int row = actuel.getComposanteNoeud().getRow();
                int col = actuel.getComposanteNoeud().getCol();
                String dir = null;
                ArrayList<Source> sourcesSeules = new ArrayList<>();
                ArrayList<Diode> diodesSeules = new ArrayList<>();
                error = false;

                if (!actuel.getDirectionsAnalysees()[j]) {

                    switch (actuel.getDirections()[j]) {
                        case "N":
                            dir = "up";
                            row--;
                            break;
                        case "E":
                            dir = "right";
                            col++;
                            break;
                        case "S":
                            dir = "down";
                            row++;
                            break;
                        case "O":
                            dir = "left";
                            col--;
                            break;

                    }

                    boolean finished = false;
                    Branche brancheTemporaire = new Branche();


                    while (!finished && !error) {

                        Composante debug = ((Composante) getNodeFromGridPane(gridPaneSandBox, col, row));

                        //if (debug != null) {

                        if (!finished && !error) {
                            brancheTemporaire.getComposantesBranche().add((Composante) getNodeFromGridPane(gridPaneSandBox, col, row));

                            if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Source) {
                                brancheTemporaire.getSources().add(((Source) getNodeFromGridPane(gridPaneSandBox, col, row)));
                            }
                            if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Resisteur) {
                                brancheTemporaire.getResisteurs().add(((Resisteur) getNodeFromGridPane(gridPaneSandBox, col, row)));
                            }
                            if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Diode) {
                                brancheTemporaire.getDiodes().add(((Diode) getNodeFromGridPane(gridPaneSandBox, col, row)));
                            }
                            if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof MiseAterre && circuits.get(numeroDeCircuit).getMiseAterre()==null) {
                                circuits.get(numeroDeCircuit).setMiseAterre((MiseAterre) getNodeFromGridPane(gridPaneSandBox, col, row));
                            }else if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof MiseAterre && circuits.get(numeroDeCircuit).getMiseAterre()!=null){
                                circuits.get(numeroDeCircuit).setmATMultiples(true);
                            }
                            if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Condensateur){
                                brancheTemporaire.getCondensateurs().add(((Condensateur) getNodeFromGridPane(gridPaneSandBox, col, row)));
                            }
                        }


                        switch (dir) {
                            case "up":
                                switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                    case "NS":
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Source) {
                                            ((Source) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeudDirectionnel(actuel);
                                        }
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Diode) {
                                            ((Diode) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeudDirectionnel(actuel);
                                        }
                                        dir = "up";
                                        row--;
                                        break;
                                    case "SN":
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Source) {
                                            sourcesSeules.add((Source) getNodeFromGridPane(gridPaneSandBox, col, row));
                                        }
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Diode) {
                                            diodesSeules.add((Diode) getNodeFromGridPane(gridPaneSandBox, col, row));
                                        }
                                        dir = "up";
                                        row--;
                                        break;
                                    case "SE":
                                        dir = "right";
                                        col++;
                                        break;
                                    case "SO":
                                        dir = "left";
                                        col--;
                                        break;
                                    case "NSE":
                                        finished = true;
                                        break;
                                    case "NSO":
                                        finished = true;
                                        break;
                                    case "SOE":
                                        finished = true;
                                        break;
                                    case "NSEO":
                                        finished = true;
                                        break;
                                    default:
                                        error = true;
                                        break;
                                }
                                break;
                            case "right":
                                switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                    case "NO":
                                        dir = "up";
                                        row--;
                                        break;
                                    case "SO":
                                        dir = "down";
                                        row++;
                                        break;
                                    case "OE":
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Source) {
                                            ((Source) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeudDirectionnel(actuel);
                                        }
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Diode) {
                                            ((Diode) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeudDirectionnel(actuel);
                                        }
                                        dir = "right";
                                        col++;
                                        break;
                                    case "EO":
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Source) {
                                            sourcesSeules.add((Source) getNodeFromGridPane(gridPaneSandBox, col, row));
                                        }
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Diode) {
                                            diodesSeules.add((Diode) getNodeFromGridPane(gridPaneSandBox, col, row));
                                        }
                                        dir = "right";
                                        col++;
                                        break;
                                    case "NSO":
                                        finished = true;
                                        break;
                                    case "SOE":
                                        finished = true;
                                        break;
                                    case "NEO":
                                        finished = true;
                                        break;
                                    case "NSEO":
                                        finished = true;
                                        break;
                                    default:
                                        error = true;
                                        break;
                                }
                                break;
                            case "down":
                                switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                    case "NS":
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Source) {
                                            sourcesSeules.add((Source) getNodeFromGridPane(gridPaneSandBox, col, row));
                                        }
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Diode) {
                                            diodesSeules.add((Diode) getNodeFromGridPane(gridPaneSandBox, col, row));
                                        }
                                        dir = "down";
                                        row++;
                                        break;
                                    case "SN":
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Source) {
                                            ((Source) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeudDirectionnel(actuel);
                                        }
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Diode) {
                                            ((Diode) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeudDirectionnel(actuel);
                                        }
                                        dir = "down";
                                        row++;
                                        break;
                                    case "NE":
                                        dir = "right";
                                        col++;
                                        break;
                                    case "NO":
                                        dir = "left";
                                        col--;
                                        break;
                                    case "NSE":
                                        finished = true;
                                        break;
                                    case "NSO":
                                        finished = true;
                                        break;
                                    case "NEO":
                                        finished = true;
                                        break;
                                    case "NSEO":
                                        finished = true;
                                        break;
                                    default:
                                        error = true;
                                        break;
                                }
                                break;
                            case "left":
                                switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                    case "NE":
                                        dir = "up";
                                        row--;
                                        break;
                                    case "SE":
                                        dir = "down";
                                        row++;
                                        break;
                                    case "OE":
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Source) {
                                            sourcesSeules.add((Source) getNodeFromGridPane(gridPaneSandBox, col, row));
                                        }
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Diode) {
                                            diodesSeules.add((Diode) getNodeFromGridPane(gridPaneSandBox, col, row));
                                        }
                                        dir = "left";
                                        col--;
                                        break;
                                    case "EO":
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Source) {
                                            ((Source) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeudDirectionnel(actuel);
                                        }
                                        if ((getNodeFromGridPane(gridPaneSandBox, col, row)) instanceof Diode) {
                                            ((Diode) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeudDirectionnel(actuel);
                                        }
                                        dir = "left";
                                        col--;
                                        break;
                                    case "NSE":
                                        finished = true;
                                        break;
                                    case "SOE":
                                        finished = true;
                                        break;
                                    case "NEO":
                                        finished = true;
                                        break;
                                    case "NSEO":
                                        finished = true;
                                        break;
                                    default:
                                        error = true;
                                        break;
                                }

                                break;

                        }

                        if (finished) {
                            brancheTemporaire.getComposantesBranche().remove(brancheTemporaire.getComposantesBranche().size() - 1);
                            actuel.getBranchesAdjacentes().add(brancheTemporaire);
                            brancheTemporaire.getNoeudsAdjacents().add(actuel);
                        }
                    }

                    if (!error) {

                        System.out.println("Branche créée!");
                        System.out.println(" ");

                        Noeud tempo = null;

                        for (int k = 0; k < circuits.get(numeroDeCircuit).getNoeuds().size(); k++) {
                            if (getNodeFromGridPane(gridPaneSandBox, col, row) == circuits.get(numeroDeCircuit).getNoeuds().get(k).getComposanteNoeud()) {
                                tempo = circuits.get(numeroDeCircuit).getNoeuds().get(k);
                                circuits.get(numeroDeCircuit).getNoeuds().get(k).getBranchesAdjacentes().add(brancheTemporaire);
                                brancheTemporaire.getNoeudsAdjacents().add(circuits.get(numeroDeCircuit).getNoeuds().get(k));
                                for (Source source : sourcesSeules) {
                                    source.setNoeudDirectionnel(tempo);
                                }
                                for (Diode diode : diodesSeules) {
                                    diode.setNoeudDirectionnel(tempo);
                                }
                            }
                        }

                        sourcesSeules.clear();
                        changerDirectionsAnalysees(tempo, dir);
                        circuits.get(numeroDeCircuit).getBranches().add(brancheTemporaire);
                        actuel.getDirectionsAnalysees()[j] = true;
                    }
                }
            }
        }
        System.out.println(" ");
    }

    public static void arrangerBranchesAdjacentes(Circuit circuit) {
        for (int i = 0; i < circuit.getNoeuds().size(); i++) {
            ArrayList<Branche> listeTemp = new ArrayList<>();
            listeTemp.addAll(circuit.getNoeuds().get(i).getBranchesAdjacentes());
            circuit.getNoeuds().get(i).getBranchesAdjacentes().clear();

            for (int j = 0; j < circuit.getNoeuds().get(i).getDirections().length; j++) {
                String dir = circuit.getNoeuds().get(i).getDirections()[j];
                int row = circuit.getNoeuds().get(i).getComposanteNoeud().getRow();
                int col = circuit.getNoeuds().get(i).getComposanteNoeud().getCol();

                switch (dir) {
                    case "N":
                        row--;
                        break;
                    case "E":
                        col++;
                        break;
                    case "S":
                        row++;
                        break;
                    case "O":
                        col--;
                        break;
                }

                boolean finished = false;

                for (int k = 0; k < listeTemp.size() && !finished; k++) {
                    for (int l = 0; l < listeTemp.get(k).getComposantesBranche().size() && !finished; l++) {
                        Composante composante = ((Composante) SandboxController.getNodeFromGridPane(SandboxController.gridPaneSandBox, col, row));

                        if (composante == listeTemp.get(k).getComposantesBranche().get(l)) {
                            finished = true;
                            circuit.getNoeuds().get(i).getBranchesAdjacentes().add(listeTemp.get(k));
                        }
                    }
                }
                if (circuit.getNoeuds().get(i).getBranchesAdjacentes().size()==j){
                    circuit.getNoeuds().get(i).getBranchesAdjacentes().add(null);
                }
            }
        }
        System.out.println();
    }

    private static void creerSerie(int numeroDeCircuit) {

        Composante composanteInitiale = circuits.get(numeroDeCircuit).getSources().get(0);
        circuits.get(numeroDeCircuit).getSources().clear();
        circuits.get(numeroDeCircuit).getComposantes().clear();
        Branche brancheTemporaire = new Branche();

        brancheTemporaire.getComposantesBranche().add(composanteInitiale);
        brancheTemporaire.getSources().add((Source) composanteInitiale);


        int row = composanteInitiale.getRow();
        int col = composanteInitiale.getCol();
        String dir = null;
        boolean finished = false;
        boolean error = false;

        switch (composanteInitiale.getDirection()) {
            case 0:
                dir = "up";
                row--;
                break;
            case 1:
                dir = "right";
                col++;
                break;
            case 2:
                dir = "down";
                row++;
                break;
            case 3:
                dir = "left";
                col--;
                break;
        }


        while (!finished && !error) {
            if (getNodeFromGridPane(gridPaneSandBox, col, row) != composanteInitiale) {
                Composante debug = ((Composante) (getNodeFromGridPane(gridPaneSandBox, col, row)));

                if (debug != null) {
                    brancheTemporaire.getComposantesBranche().add((Composante) (getNodeFromGridPane(gridPaneSandBox, col, row)));

                    //Check si cest une source
                    if (((getNodeFromGridPane(gridPaneSandBox, col, row))) instanceof Source) {
                        brancheTemporaire.getSources().add(((Source) (getNodeFromGridPane(gridPaneSandBox, col, row))));
                    }

                    //Check si c'est un résisteur
                    if (((getNodeFromGridPane(gridPaneSandBox, col, row))) instanceof Resisteur) {
                        brancheTemporaire.getResisteurs().add(((Resisteur) (getNodeFromGridPane(gridPaneSandBox, col, row))));
                    }

                    if (((getNodeFromGridPane(gridPaneSandBox, col, row))) instanceof Diode) {
                        brancheTemporaire.getDiodes().add(((Diode) (getNodeFromGridPane(gridPaneSandBox, col, row))));
                    }

                    if (((getNodeFromGridPane(gridPaneSandBox, col, row))) instanceof Condensateur) {
                        brancheTemporaire.getCondensateurs().add(((Condensateur) (getNodeFromGridPane(gridPaneSandBox, col, row))));
                    }

                    //Cherche si la composante suivante est rattachée à la précédente et check la prochaine direction
                    switch (dir) {
                        case "up":
                            switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                case "NS":
                                    dir = "up";
                                    row--;
                                    break;
                                case "SN":
                                    dir = "up";
                                    row--;
                                    if (debug instanceof Source) {
                                        ((Source) debug).setInverseEnSerie(true);
                                    }
                                    if (debug instanceof Diode) {
                                        ((Diode) debug).setInverseEnSerie(true);
                                    }
                                    break;
                                case "SE":
                                    dir = "right";
                                    col++;
                                    break;
                                case "SO":
                                    dir = "left";
                                    col--;
                                    break;
                                case "NSE":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                case "NSO":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                case "SOE":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                case "NSEO":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                default:
                                    error = true;
                                    break;
                            }
                            break;
                        case "right":
                            switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                case "NO":
                                    dir = "up";
                                    row--;
                                    break;
                                case "SO":
                                    dir = "down";
                                    row++;
                                    break;
                                case "OE":
                                    dir = "right";
                                    col++;
                                    break;
                                case "EO":
                                    dir = "right";
                                    col++;
                                    if (debug instanceof Source) {
                                        ((Source) debug).setInverseEnSerie(true);
                                    }
                                    if (debug instanceof Diode) {
                                        ((Diode) debug).setInverseEnSerie(true);
                                    }
                                    break;
                                case "NSO":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                case "SOE":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                case "NEO":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                case "NSEO":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                default:
                                    error = true;
                                    break;
                            }
                            break;
                        case "down":
                            switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                case "NS":
                                    dir = "down";
                                    row++;
                                    if (debug instanceof Source) {
                                        ((Source) debug).setInverseEnSerie(true);
                                    }
                                    if (debug instanceof Diode) {
                                        ((Diode) debug).setInverseEnSerie(true);
                                    }
                                    break;
                                case "SN":
                                    dir = "down";
                                    row++;
                                    break;
                                case "NE":
                                    dir = "right";
                                    col++;
                                    break;
                                case "NO":
                                    dir = "left";
                                    col--;
                                    break;
                                case "NSE":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                case "NSO":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                case "NEO":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                case "NSEO":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                default:
                                    error = true;
                                    break;
                            }
                            break;
                        case "left":
                            switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                case "NE":
                                    dir = "up";
                                    row--;
                                    break;
                                case "SE":
                                    dir = "down";
                                    row++;
                                    System.out.println("fil down");
                                    break;
                                case "OE":
                                    dir = "left";
                                    col--;
                                    if (debug instanceof Source) {
                                        ((Source) debug).setInverseEnSerie(true);
                                    }
                                    if (debug instanceof Diode) {
                                        ((Diode) debug).setInverseEnSerie(true);
                                    }
                                    break;
                                case "EO":
                                    dir = "left";
                                    col--;
                                    break;
                                case "NSE":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                case "SOE":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                case "NEO":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                case "NSEO":
                                    circuits.get(numeroDeCircuit).setEnSerie(false);
                                    ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                    circuits.get(numeroDeCircuit).getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                    finished = true;
                                    break;
                                default:
                                    error = true;
                                    break;
                            }
                            break;
                    }
                } else {
                    error = true;
                }
            } else {
                finished = true;
            }
        }
        if (!error) {
            circuits.get(numeroDeCircuit).getBranches().add(brancheTemporaire);
            circuits.get(numeroDeCircuit).setIncomplet(false);
            System.out.println("Added");
        }
    }

    private static void remplirCircuit(int numeroDeCircuit) {
        for (int i = 0; i < circuits.get(numeroDeCircuit).getBranches().size(); i++) {
            for (int j = 0; j < circuits.get(numeroDeCircuit).getBranches().get(i).getComposantesBranche().size(); j++) {
                circuits.get(numeroDeCircuit).getComposantes().add(circuits.get(numeroDeCircuit).getBranches().get(i).getComposantesBranche().get(j));
                if (circuits.get(numeroDeCircuit).getBranches().get(i).getComposantesBranche().get(j) instanceof Source) {
                    circuits.get(numeroDeCircuit).getSources().add((Source) circuits.get(numeroDeCircuit).getBranches().get(i).getComposantesBranche().get(j));
                }
                if (circuits.get(numeroDeCircuit).getBranches().get(i).getComposantesBranche().get(j) instanceof Resisteur) {
                    circuits.get(numeroDeCircuit).getResisteurs().add((Resisteur) circuits.get(numeroDeCircuit).getBranches().get(i).getComposantesBranche().get(j));
                }
            }
        }
    }

    private static void changerValeur(Composante composante, String string) {
        TextInputDialog alerteValeur = new TextInputDialog("Entrez ici");
        alerteValeur.setTitle("Valeur");
        alerteValeur.setHeaderText("Entrez la valeur de " + string);
        String valeur = alerteValeur.showAndWait().get();
        try {
            if (string.equals("l'intensité"))
                composante.setAmperage(Double.parseDouble(valeur));
            else if (string.equals("la tension"))
                composante.setVolt(Double.parseDouble(valeur));
            else if (string.equals("la capacité"))
                composante.setCapacite(Double.parseDouble(valeur));
            else
                composante.setResistance(Double.parseDouble(valeur));
            composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + df.format(composante.getAmperage()) + " A" + "\nTension: " + df.format(composante.getVolt()) + " V" + "\nRésistance: " + df.format(composante.getResistance()) + " Ω");
        } catch (Exception e) {
            Alert alerte = new Alert(Alert.AlertType.INFORMATION);
            alerte.setTitle("ERREUR");
            alerte.setHeaderText("La valeur entrée est invalide, veuillez réessayer");
            alerte.showAndWait();
            changerValeur(composante, string);
        }
    }

    public void save() {
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Veuillez sélectionner un fichier");
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Zephyr", "*.zep"));
            fc.setInitialDirectory(new File("src/autre/saves"));
            File fichier = fc.showSaveDialog(Main.getStage());
            ComposanteSave[][] gridPaneSave = new ComposanteSave[20][20];
            for (int i = 0; i < 20; i++)
                for (int j = 0; j < 20; j++)
                    gridPaneSave[i][j] = new ComposanteSave((Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
            ObjectOutputStream sortie = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(fichier)));
            sortie.writeObject(gridPaneSave);
            sortie.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Veuillez sélectionner un fichier");
            fc.setInitialDirectory(new File("src/autre/saves"));
            File fichier = fc.showOpenDialog(Main.getStage());
            ObjectInputStream entree = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(fichier)));
            try {
                ComposanteSave[][] gridPaneSave = (ComposanteSave[][]) entree.readObject();
                clearLoading();
                for (int i = 0; i < 20; i++)
                    for (int j = 0; j < 20; j++) {
                        switch (gridPaneSave[i][j].getNom().toUpperCase()) {
                            case "FIL":
                                placerComposantes(new Fil(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "AMPÈREMÈTRE":
                                placerComposantes(new Amperemetre(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "AMPOULE":
                                placerComposantes(new Ampoule(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "SOURCE":
                                placerComposantes(new Source(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "DIODE":
                                placerComposantes(new Diode(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "FUSIBLE":
                                placerComposantes(new Fusible(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "INTERRUPTEUR":
                                placerComposantes(new Interrupteur(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "MISE À TERRE":
                                placerComposantes(new MiseAterre(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "OHMMÈTRE":
                                placerComposantes(new Ohmetre(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "RÉSISTEUR":
                                placerComposantes(new Resisteur(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "VOLTMÈTRE":
                                placerComposantes(new Voltmetre(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "SWITCH":
                                placerComposantes(new Switch(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "HAUT-PARLEUR":
                                placerComposantes(new HautParleur(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "CONDENSATEUR":
                                placerComposantes(new Condensateur(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                            case "VIDE":
                                placerComposantes(new ComposanteVide(), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                                break;
                        }
                    }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateCircuit();
    }

    public static void resetGridPane() {

        ComposanteSave[][] gridPaneSave = new ComposanteSave[20][20];
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                gridPaneSave[i][j] = new ComposanteSave((Composante) getNodeFromGridPane(gridPaneSandBox, i, j));

        clearLoading();
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                switch (gridPaneSave[i][j].getNom().toUpperCase()) {
                    case "FIL":
                        placerComposantes(new Fil(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "AMPÈREMÈTRE":
                        placerComposantes(new Amperemetre(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "AMPOULE":
                        placerComposantes(new Ampoule(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "SOURCE":
                        placerComposantes(new Source(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "DIODE":
                        placerComposantes(new Diode(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "FUSIBLE":
                        placerComposantes(new Fusible(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "INTERRUPTEUR":
                        placerComposantes(new Interrupteur(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "MISE À TERRE":
                        placerComposantes(new MiseAterre(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "OHMMÈTRE":
                        placerComposantes(new Ohmetre(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "RÉSISTEUR":
                        placerComposantes(new Resisteur(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "VOLTMÈTRE":
                        placerComposantes(new Voltmetre(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "SWITCH":
                        placerComposantes(new Switch(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "HAUT-PARLEUR":
                        placerComposantes(new HautParleur(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "CONDENSATEUR":
                        placerComposantes(new Condensateur(gridPaneSave[i][j], i, j), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                    case "VIDE":
                        placerComposantes(new ComposanteVide(), (Composante) getNodeFromGridPane(gridPaneSandBox, i, j));
                        break;
                }
            }
    }
}