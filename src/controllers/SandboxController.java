package controllers;

import composantes.Composante;
import composantes.ComposanteVide;
import composantes.*;
import concepts.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.scene.text.TextAlignment;
import main.Main;
import composantes.ComposanteSave;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.IntStream;


public class SandboxController {

    public static GridPane gridPaneSandBox = new GridPane();
    public static Button backButtonStatic = new Button();
    private static FlowPane rootScrollPane = new FlowPane();
    public static DecimalFormat df = new DecimalFormat("#.##");
    public static Circuit circuit1 = new Circuit();
    public static Text textDescription = new Text();
    public static boolean menuTouteComposantes = true;
    public static boolean cPressed = false;
    public static boolean xPressed = false;

    @FXML
    private SplitPane mySplitPane;

    @FXML
    private HBox myHBox;

    @FXML
    private ScrollPane scroll;

    @FXML
    private ScrollPane myScrollPane;

    @FXML
    private Button backButton;

    private Circuit circuit = new Circuit();

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
        //initializeGridPane(gridPaneSandBox);

        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                creerComposanteVide(i, j);

        goBack();
        //rootScrollPane.getChildren().add(new Moteur());
        //rootScrollPane.getChildren().add(new HautParleur());
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
        //rootScrollPane.getChildren().add(new Interrupteur());
        rootScrollPane.getChildren().add(new MiseAterre());
        rootScrollPane.getChildren().add(new Ohmetre());
        rootScrollPane.getChildren().add(new Resisteur());
        rootScrollPane.getChildren().add(new Voltmetre());
        rootScrollPane.getChildren().add(new Switch());
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
            case "AMPEREMÈTRE":
                rootScrollPane.getChildren().add(0, new Amperemetre());
                Amperemetre amperemetre1 = new Amperemetre();
                amperemetre1.setImage(amperemetre1.getTabVariante()[1]);
                amperemetre1.setDirection(1);
                rootScrollPane.getChildren().add(1, amperemetre1);
                updateCircuit();
                break;
            case "AMPOULE":
                rootScrollPane.getChildren().add(0, new Ampoule());
                Ampoule ampoule1 = new Ampoule();
                ampoule1.setImage(ampoule1.getTabVariante()[1]);
                ampoule1.setDirection(1);
                rootScrollPane.getChildren().add(1, ampoule1);
                updateCircuit();
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
                updateCircuit();
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
                updateCircuit();
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
                updateCircuit();
                break;
            case "FUSIBLE":
                rootScrollPane.getChildren().add(0, new Fusible());
                Fusible fusible = new Fusible();
                fusible.setImage(fusible.getTabVariante()[1]);
                fusible.setDirection(1);
                rootScrollPane.getChildren().add(1, fusible);
                updateCircuit();
                break;
            case "HAUT-PARLEUR":
                rootScrollPane.getChildren().add(0, new HautParleur());
                HautParleur hautParleur = new HautParleur();
                hautParleur.setImage(hautParleur.getTabVariante()[1]);
                hautParleur.setDirection(1);
                rootScrollPane.getChildren().add(1, hautParleur);
                updateCircuit();
                break;
            case "INTERRUPTEUR":
                rootScrollPane.getChildren().add(0, new Interrupteur());
                Interrupteur interrupteur = new Interrupteur();
                interrupteur.setImage(interrupteur.getTabVariante()[1]);
                interrupteur.setDirection(1);
                rootScrollPane.getChildren().add(1, interrupteur);
                updateCircuit();
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
                updateCircuit();
                break;
            case "MOTEUR":
                //rootScrollPane.getChildren().add(0, new Fil());
                break;
            case "OHMÈTRE":
                rootScrollPane.getChildren().add(0, new Ohmetre());
                Ohmetre ohmetre = new Ohmetre();
                ohmetre.setImage(ohmetre.getTabVariante()[1]);
                ohmetre.setDirection(1);
                rootScrollPane.getChildren().add(1, ohmetre);
                updateCircuit();
                break;
            case "RESISTEUR":
                rootScrollPane.getChildren().add(0, new Resisteur());
                Resisteur resisteur = new Resisteur();
                resisteur.setImage(resisteur.getTabVariante()[1]);
                resisteur.setDirection(1);
                rootScrollPane.getChildren().add(1, resisteur);
                updateCircuit();
                break;
            case "VOLTMÈTRE":
                rootScrollPane.getChildren().add(0, new Voltmetre());
                Voltmetre voltmetre = new Voltmetre();
                voltmetre.setImage(voltmetre.getTabVariante()[1]);
                voltmetre.setDirection(1);
                rootScrollPane.getChildren().add(1, voltmetre);
                updateCircuit();
                break;
            case "SWITCH":
                rootScrollPane.getChildren().add(0, new Switch());
                Switch aSwitch = new Switch();
                aSwitch.setImage(aSwitch.getTabVariante()[1]);
                aSwitch.setDirection(1);
                rootScrollPane.getChildren().add(1, aSwitch);
                updateCircuit();
                break;
        }
    }

    public void setAventure() {
        Main.changerDeMode(2);
    }

    public void setGuide() {
        Main.changerDeMode(3);
    }

    public void setMenu() {
        Main.changerDeMode(0);
    }

    public void clear() {
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
        if(!source.getNom().toUpperCase().equals("VIDE")){
            source.getTooltip().setText(source.getNom() + " (" + source.getCol() + "," + source.getRow() + ")\nIntensité: " + df.format(source.getAmperage()) + "\nTension: " + df.format(source.getVolt()) + "\nRésistance: " + df.format(source.getResistance()));
            MenuItem itemSupprimer = new MenuItem("Supprimer");
            Menu menuVariante = new Menu("Variantes");
            MenuItem itemVariante[] = new MenuItem[source.getTabNomVariante().length];

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
            ContextMenu contextMenu = new ContextMenu(menuVariante);

            if (source.getNom().toUpperCase().equals("SOURCE")) {
                MenuItem itemTension = new MenuItem("Modifier la tension");
                itemTension.setOnAction(event -> {
                    changerValeur(source, "la tension");
                    updateCircuit();
                });
                contextMenu.getItems().addAll(itemTension);
            }

            if (source.getNom().toUpperCase().equals("RESISTEUR")) {
                MenuItem itemResistance = new MenuItem("Modifier la résistance");
                itemResistance.setOnAction(event -> {
                    changerValeur(source, "la résistance");
                    updateCircuit();
                });
                contextMenu.getItems().add(itemResistance);
            }
            itemSupprimer.setOnAction(event -> {
                supprimer(source);
            });
            contextMenu.getItems().add(itemSupprimer);
            source.setOnContextMenuRequested(event -> contextMenu.show(source, event.getScreenX(), event.getScreenY()));
        }
        gridPaneSandBox.add(source, i, j);
    }
    public static void supprimer(Composante source){
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
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new Fil());
                } else {
                    goBack();
                }
                break;
            case "AMPEREMÈTRE":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new Amperemetre());
                } else {
                    goBack();
                }
                break;
            case "AMPOULE":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new Ampoule());
                } else {
                    goBack();
                }
                break;
            case "SOURCE":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new Source());
                } else {
                    goBack();
                }
                break;
            case "DIODE":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new Diode());
                } else {
                    goBack();
                }
                break;
            case "FUSIBLE":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new Fusible());
                } else {
                    goBack();
                }
                break;
            case "INTERRUPTEUR":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new Interrupteur());
                } else {
                    goBack();
                }
                break;
            case "MISE À TERRE":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new MiseAterre());
                } else {
                    goBack();
                }
                break;
            case "OHMÈTRE":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new Ohmetre());
                } else {
                    goBack();
                }
                break;
            case "RESISTEUR":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new Resisteur());
                } else {
                    goBack();
                }
                break;
            case "VOLTMÈTRE":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new Voltmetre());
                } else {
                    goBack();
                }
                break;
            case "SWITCH":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new Switch());
                } else {
                    goBack();
                }
                ;
                break;
            case "HAUT-PARLEUR":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new HautParleur());
                } else {
                    goBack();
                }
                break;
            case "MOTEUR":
                if (menuTouteComposantes == false) {
                    changerMenuComposante(new Moteur());
                } else {
                    goBack();
                }
                break;
        }
    }

    public static void copierComposante(Composante source, Composante target){
        Composante copie = null;
        switch (source.getNom().toUpperCase()) {
            case "FIL":
                copie = new Fil(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "AMPEREMÈTRE":
                copie = new Amperemetre(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "AMPOULE":
                copie = new Ampoule(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "SOURCE":
                copie = new Source(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "DIODE":
                copie = new Diode(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "FUSIBLE":
                copie = new Fusible(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "INTERRUPTEUR":
                copie = new Interrupteur(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "MISE À TERRE":
                copie = new MiseAterre(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "OHMÈTRE":
                copie = new Ohmetre(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "RESISTEUR":
                copie = new Resisteur(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "VOLTMÈTRE":
                copie = new Voltmetre(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "SWITCH":
                copie = new Switch(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "HAUT-PARLEUR":
                copie = new HautParleur(new ComposanteSave(source),target.getRow(),target.getCol());
                break;
            case "MOTEUR":
                copie = new Moteur();
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

        for (int i = 0; i < circuit1.getNoeuds().size(); i++) {
            ((Fil) circuit1.getNoeuds().get(i).getComposanteNoeud()).setNoeud(false);
        }

        circuit1 = new Circuit();
        if (circuit1.isEnSerie()) {
            creerSerie();
        }
        if (!circuit1.isEnSerie()) {
            Noeud initial = circuit1.getNoeuds().get(0);
            circuit1 = new Circuit();
            circuit1.setEnSerie(false);
            circuit1.setIncomplet(false);
            creerNoeuds(initial);
            if (!circuit1.isIncomplet()){
                creerBranches();
                creerMailles();
            }
        }
        remplirCircuit();

        if (!circuit1.isIncomplet())
            circuit1.calculVariables();
    }

    public static void creerComposanteVide(int i, int j) {
        ComposanteVide vide = new ComposanteVide();
        vide.setRow(j);
        vide.setCol(i);
        gridPaneSandBox.add(vide, i, j);
    }

    public static void creerMailles() {

        for (int i = 0; i < circuit1.getNoeuds().size(); i++) {
            circuit1.getNoeuds().get(i).updateBranchesAnalysees();
        }

        for (int i = 0; i < circuit1.getBranches().size(); i++) {

            boolean finished = false;
            boolean error = false;
            boolean mono = false;
            NouvelleMaille mailleTemporaire = new NouvelleMaille();
            Noeud noeudTemporaire = circuit1.getBranches().get(i).getNoeudsAdjacents().get(0);
            Branche brancheTemporaire = circuit1.getBranches().get(i);
            Branche brancheInitiale = circuit1.getBranches().get(i);
            mailleTemporaire.getNoeudsMaille().add(noeudTemporaire);
            mailleTemporaire.getBranchesMaille().add(circuit1.getBranches().get(i));
            int monoBranche = 0;

            //
            for (int k = 0; k < noeudTemporaire.getBranchesAdjacentes().size(); k++) {
                if (noeudTemporaire.getBranchesAdjacentes().get(k) == brancheTemporaire) {
                    monoBranche++;
                }
            }


            if (monoBranche >= 2) {
                mono = true;
            }

            if (!mono) {
                for (int j = 0; j < circuit1.getNoeuds().size(); j++) {
                    circuit1.getNoeuds().get(j).resetBranchesAnalysees();
                }
                //

                for (int j = 0; j < noeudTemporaire.getBranchesAdjacentes().size(); j++) {

                    if (noeudTemporaire.getBranchesAdjacentes().get(j) != brancheTemporaire) {

                        if (!noeudTemporaire.getBranchesAnalysees()[j]) {

                            brancheTemporaire = noeudTemporaire.getBranchesAdjacentes().get(j);
                            noeudTemporaire.getBranchesAnalysees()[j] = true;

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

                            if (!error) {
                                for (int k = 0; k < noeudTemporaire.getBranchesAdjacentes().size(); k++) {
                                    if (noeudTemporaire.getBranchesAdjacentes().get(k) == brancheInitiale) {
                                        finished = true;
                                    }
                                }
                            }


                            if (finished) {

                                circuit1.getMailles().add(new NouvelleMaille(
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
                circuit1.getMailles().add(new NouvelleMaille(
                        mailleTemporaire.getComposantesMaille(),
                        mailleTemporaire.getResisteurs(),
                        mailleTemporaire.getNoeudsMaille(),
                        mailleTemporaire.getBranchesMaille(),
                        mailleTemporaire.getSources()
                ));
            }
        }

        arrangerMailles();
        circuit1.setIncomplet(false);
        System.out.println("Mailles Complètes");
        System.out.println("");
    }

    public static void arrangerMailles() {
        boolean done = false;
        boolean done2;
        int j;
        int i = 0;

        while (!done) {
            j = 0;
            done2 = false;
            while (!done2) {

                NouvelleMaille maille1 = circuit1.getMailles().get(i);
                NouvelleMaille maille2 = circuit1.getMailles().get(j);

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

                        for (int k = 0; k < same.length; k++) {
                            if (!same[k]) {
                                allTheSame = false;
                            }
                        }

                        if (allTheSame) {
                            circuit1.getMailles().remove(j);
                            j--;
                        }

                    }
                }
                j++;
                if (j >= circuit1.getMailles().size()) {
                    done2 = true;
                }
            }

            i++;
            if (i >= circuit1.getMailles().size()) {
                done = true;
            }
        }

        for (int k = 0; k < circuit1.getMailles().size(); k++) {
            NouvelleMaille mailleTempo = circuit1.getMailles().get(k);
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

    public static void creerNoeuds(Noeud initial) {
        Noeud actuel = initial;
        circuit1.getNoeuds().add(initial);
        circuit1.getComposantes().add(initial.getComposanteNoeud());
        boolean error = false;

        for (int i = 0; i < circuit1.getNoeuds().size(); i++) {

            actuel = circuit1.getNoeuds().get(i);

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
                                        System.out.println("fil down");
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
                        if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)) == actuel.getComposanteNoeud()) {
                            System.out.println("Revenu au départ");

                            changerDirectionsAnalysees(actuel, dir);

                        } else {
                            if (((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).isNoeud()) {
                                System.out.println("Rencontre un noeud existant");
                                Noeud rencontre = null;

                                for (int l = 0; l < circuit1.getNoeuds().size(); l++) {
                                    if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)) == circuit1.getNoeuds().get(l).getComposanteNoeud()) {
                                        rencontre = circuit1.getNoeuds().get(l);
                                    }
                                }

                                changerDirectionsAnalysees(rencontre, dir);


                            } else {
                                System.out.println("Nouveau noeud!");
                                Noeud tempo = new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row));

                                changerDirectionsAnalysees(tempo, dir);

                                ((Fil) tempo.getComposanteNoeud()).setNoeud(true);
                                circuit1.getNoeuds().add(tempo);
                                circuit1.getComposantes().add(tempo.getComposanteNoeud());

                            }
                        }

                        actuel.getDirectionsAnalysees()[j] = true;

                    } else {
                        circuit1.setIncomplet(true);
                    }
                }
            }
        }
        System.out.println(" ");
    }

    public static void changerDirectionsAnalysees(Noeud noeud, String dir) {
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

    public static void creerBranches() {

        for (int i = 0; i < circuit1.getNoeuds().size(); i++) {
            for (int j = 0; j < circuit1.getNoeuds().get(i).getDirectionsAnalysees().length; j++) {
                circuit1.getNoeuds().get(i).getDirectionsAnalysees()[j] = false;
            }
        }

        boolean error = false;

        for (int i = 0; i < circuit1.getNoeuds().size(); i++) {

            Noeud actuel = circuit1.getNoeuds().get(i);

            for (int j = 0; j < actuel.getDirectionsAnalysees().length; j++) {

                int row = actuel.getComposanteNoeud().getRow();
                int col = actuel.getComposanteNoeud().getCol();
                String dir = null;
                ArrayList<Source> sourcesSeules = new ArrayList<>();

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

                        if (debug != null) {

                            if (!finished && !error) {
                                brancheTemporaire.getComposantesBranche().add((Composante) getNodeFromGridPane(gridPaneSandBox, col, row));

                                if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getNom().toUpperCase().equals("SOURCE")) {
                                    brancheTemporaire.getSources().add(((Source) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                }
                                if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getNom().toUpperCase().equals("RESISTEUR")) {
                                    brancheTemporaire.getResisteurs().add(((Resisteur) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                }
                            }


                            switch (dir) {
                                case "up":
                                    switch (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getTabNomVariante()[((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getDirection()]) {
                                        case "NS":
                                            if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getNom().toUpperCase().equals("SOURCE")) {
                                                ((Source) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeudDirectionnel(actuel);
                                            }
                                            dir = "up";
                                            row--;
                                            break;
                                        case "SN":
                                            if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getNom().toUpperCase().equals("SOURCE")) {
                                                sourcesSeules.add((Source) getNodeFromGridPane(gridPaneSandBox, col, row));
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
                                            if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getNom().toUpperCase().equals("SOURCE")) {
                                                sourcesSeules.add((Source) getNodeFromGridPane(gridPaneSandBox, col, row));
                                            }
                                            dir = "right";
                                            col++;
                                            break;
                                        case "EO":
                                            if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getNom().toUpperCase().equals("SOURCE")) {
                                                ((Source) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeudDirectionnel(actuel);
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
                                            if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getNom().toUpperCase().equals("SOURCE")) {
                                                sourcesSeules.add((Source) getNodeFromGridPane(gridPaneSandBox, col, row));
                                            }
                                            dir = "down";
                                            row++;
                                            break;
                                        case "SN":
                                            if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getNom().toUpperCase().equals("SOURCE")) {
                                                ((Source) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeudDirectionnel(actuel);
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
                                            System.out.println("fil down");
                                            break;
                                        case "OE":
                                            if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getNom().toUpperCase().equals("SOURCE")) {
                                                ((Source) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeudDirectionnel(actuel);
                                            }
                                            dir = "left";
                                            col--;
                                            break;
                                        case "EO":
                                            if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getNom().toUpperCase().equals("SOURCE")) {
                                                sourcesSeules.add((Source) getNodeFromGridPane(gridPaneSandBox, col, row));
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

                        }else {
                            error=true;
                        }
                    }

                    if (!error) {

                        System.out.println("Branche créée!");
                        System.out.println(" ");

                        Noeud tempo = null;

                        for (int k = 0; k < circuit1.getNoeuds().size(); k++) {
                            if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)) == circuit1.getNoeuds().get(k).getComposanteNoeud()) {
                                tempo = circuit1.getNoeuds().get(k);
                                circuit1.getNoeuds().get(k).getBranchesAdjacentes().add(brancheTemporaire);
                                brancheTemporaire.getNoeudsAdjacents().add(circuit1.getNoeuds().get(k));
                                for (Source source : sourcesSeules) {
                                    source.setNoeudDirectionnel(tempo);
                                }
                            }
                        }

                        sourcesSeules.clear();
                        changerDirectionsAnalysees(tempo, dir);
                        circuit1.getBranches().add(brancheTemporaire);
                        actuel.getDirectionsAnalysees()[j] = true;
                    }

                }
            }
        }
/*
        for (int i=0; i<circuit1.getBranches().size(); i++){
            if (circuit1.getBranches().get(i).getComposantesBranche().size()==0){
                circuit1.getBranches().remove(i);
                i=0;
            }
        }
        */
        System.out.println(" ");
    }

    public static void creerSerie() {

        boolean debutFound = false;
        Composante composanteInitiale = null;
        Branche brancheTemporaire = new Branche();

        //Au début, s'il y a pas de noeuds, le circuit cherche un source pour commencer
        if (circuit1.isEnSerie()) {
            for (int i = 0; i < 20 && !debutFound; i++)
                for (int j = 0; j < 20 && !debutFound; j++)
                    if (((Composante) getNodeFromGridPane(gridPaneSandBox, i, j)).getNom().toUpperCase().equals("SOURCE")) {
                        debutFound = true;
                        composanteInitiale = (Composante) getNodeFromGridPane(gridPaneSandBox, i, j);
                        brancheTemporaire.getComposantesBranche().add(composanteInitiale);
                        brancheTemporaire.getSources().add((Source) composanteInitiale);
                    }
        }


        if (debutFound) {
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
                    if (((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)) != composanteInitiale) {
                        Composante debug = ((Composante) (getNodeFromGridPane(gridPaneSandBox, col, row)));

                        if (debug !=null) {
                            brancheTemporaire.getComposantesBranche().add((Composante) (getNodeFromGridPane(gridPaneSandBox, col, row)));

                            //Check si cest une source
                            if (((Composante) (getNodeFromGridPane(gridPaneSandBox, col, row))).getNom().toUpperCase().equals("SOURCE")) {
                                brancheTemporaire.getSources().add(((Source) (getNodeFromGridPane(gridPaneSandBox, col, row))));
                            }

                            //Check si c'est un résisteur
                            if (((Composante) (getNodeFromGridPane(gridPaneSandBox, col, row))).getNom().toUpperCase().equals("RESISTEUR")) {
                                brancheTemporaire.getResisteurs().add(((Resisteur) (getNodeFromGridPane(gridPaneSandBox, col, row))));
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
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        case "NSO":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        case "SOE":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        case "NSEO":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
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
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        case "SOE":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        case "NEO":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        case "NSEO":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
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
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        case "NSO":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        case "NEO":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        case "NSEO":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
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
                                            break;
                                        case "EO":
                                            dir = "left";
                                            col--;
                                            break;
                                        case "NSE":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        case "SOE":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        case "NEO":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        case "NSEO":
                                            circuit1.setEnSerie(false);
                                            ((Fil) getNodeFromGridPane(gridPaneSandBox, col, row)).setNoeud(true);
                                            circuit1.getNoeuds().add(new Noeud((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)));
                                            finished = true;
                                            break;
                                        default:
                                            error = true;
                                            break;
                                    }
                                    break;
                            }
                        }else {
                            error = true;
                        }
                    } else{
                        finished = true;
                    }
            }
            if (!error) {
                circuit1.getBranches().add(brancheTemporaire);
                circuit1.setIncomplet(false);
                System.out.println("Added");
            }
        }
    }

    public static void remplirCircuit() {
        for (int i = 0; i < circuit1.getBranches().size(); i++) {
            for (int j = 0; j < circuit1.getBranches().get(i).getComposantesBranche().size(); j++) {
                circuit1.getComposantes().add(circuit1.getBranches().get(i).getComposantesBranche().get(j));
                if (circuit1.getBranches().get(i).getComposantesBranche().get(j).getNom().toUpperCase().equals("SOURCE")) {
                    circuit1.getSources().add((Source) circuit1.getBranches().get(i).getComposantesBranche().get(j));
                }
                if (circuit1.getBranches().get(i).getComposantesBranche().get(j).getNom().toUpperCase().equals("RESISTEUR")) {
                    circuit1.getResisteurs().add((Resisteur) circuit1.getBranches().get(i).getComposantesBranche().get(j));
                }
            }
        }
    }

    public static void changerValeur(Composante composante, String string) {
        TextInputDialog alerteValeur = new TextInputDialog("Entrez ici");
        alerteValeur.setTitle("Valeur");
        alerteValeur.setHeaderText("Entrez la valeur de " + string);
        String valeur = alerteValeur.showAndWait().get();
        try {
            if (string.equals("l'intensité"))
                composante.setAmperage(Double.parseDouble(valeur));
            else if (string.equals("la tension"))
                composante.setVolt(Double.parseDouble(valeur));
            else
                composante.setResistance(Double.parseDouble(valeur));
            composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + df.format(composante.getAmperage()) + "\nTension: " + df.format(composante.getVolt()) + "\nRésistance: " + df.format(composante.getResistance()));
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
            File fichier = fc.showOpenDialog(Main.getStage());
            ObjectInputStream entree = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(fichier)));
            try {
                ComposanteSave[][] gridPaneSave = (ComposanteSave[][]) entree.readObject();
                clear();
                for (int i = 0; i < 20; i++)
                    for (int j = 0; j < 20; j++) {
                        switch (gridPaneSave[i][j].getNom().toUpperCase()) {
                            case "FIL":
                                placerComposantes(new Fil(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "AMPEREMÈTRE":
                                placerComposantes(new Amperemetre(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "AMPOULE":
                                placerComposantes(new Ampoule(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "SOURCE":
                                placerComposantes(new Source(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "DIODE":
                                placerComposantes(new Diode(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "FUSIBLE":
                                placerComposantes(new Fusible(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "INTERRUPTEUR":
                                placerComposantes(new Interrupteur(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "MISE À TERRE":
                                placerComposantes(new MiseAterre(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "OHMÈTRE":
                                placerComposantes(new Ohmetre(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "RESISTEUR":
                                placerComposantes(new Resisteur(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "VOLTMÈTRE":
                                placerComposantes(new Voltmetre(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "SWITCH":
                                placerComposantes(new Switch(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "HAUT-PARLEUR":
                                placerComposantes(new HautParleur(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "MOTEUR":
                                //placerComposantes(new Moteur(gridPaneSave[i][j], i, j),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                            case "VIDE":
                                placerComposantes(new ComposanteVide(),(Composante) getNodeFromGridPane(gridPaneSandBox, i,j));
                                break;
                        }
                    }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}