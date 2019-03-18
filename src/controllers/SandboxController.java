package controllers;

import composantes.Composante;
import composantes.ComposanteVide;
import composantes.*;
import concepts.Coordonnée;
import concepts.Maille;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import main.Main;

import java.text.DecimalFormat;
import java.util.stream.IntStream;


public class SandboxController {

    public static GridPane gridPaneSandBox = new GridPane();
    public static  Button backButtonStatic = new Button();
    private static FlowPane rootScrollPane = new FlowPane();
    public static DecimalFormat df = new DecimalFormat("#.##");
    public static Text textDescription = new Text();


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



    @FXML
    public void initialize() {
        scroll.setContent(gridPaneSandBox);
        textDescription.setText("Cliquer sur une composante pour afficher sa description");
        myHBox.getChildren().add(textDescription);
        gridPaneSandBox.setPrefSize(376, 414);
        backButtonStatic = backButton;
        backButtonStatic.setDisable(true);
        backButtonStatic.setOpacity(0);
        backButtonStatic.setOnAction(event -> goBack());
        //initializeGridPane(gridPaneSandBox);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                ComposanteVide vide = new ComposanteVide();
                vide.fitHeightProperty().set(100);
                vide.fitWidthProperty().set(100);
                vide.setRow(j);
                vide.setCol(i);
                gridPaneSandBox.add(vide, i, j);
            }
        }


        goBack();
        //rootScrollPane.getChildren().add(new Moteur());
        //rootScrollPane.getChildren().add(new HautParleur());
        rootScrollPane.setPadding(new Insets(16));
        rootScrollPane.setVgap(16);
        rootScrollPane.setHgap(16);


        myScrollPane.setContent(rootScrollPane);
    }
    public static void goBack(){
        backButtonStatic.setOpacity(0);
        backButtonStatic.setDisable(true);
        rootScrollPane.getChildren().clear();
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
    }
    public static void changerMenuComposante(Composante composante){
        String nom  = composante.getNom();
        rootScrollPane.getChildren().clear();
        backButtonStatic.setDisable(false);
        backButtonStatic.setOpacity(100);
        Image back = new Image("composantes/images/back (1).png");
        backButtonStatic.setGraphic(new ImageView(back));
        switch (nom.toUpperCase()) {
            case "AMPEREMÈTRE":
                rootScrollPane.getChildren().add(0, new Amperemetre());
                Amperemetre amperemetre1 = new Amperemetre();
                amperemetre1.setImage(amperemetre1.getTabVariante()[1]);
                amperemetre1.setDirection(1);
                rootScrollPane.getChildren().add(1, amperemetre1);
                break;
            case "AMPOULE":
                rootScrollPane.getChildren().add(0, new Ampoule());
                Ampoule ampoule1 = new Ampoule();
                ampoule1.setImage(ampoule1.getTabVariante()[1]);
                ampoule1.setDirection(1);
                rootScrollPane.getChildren().add(1,ampoule1);
                break;
            case "SOURCE":
                rootScrollPane.getChildren().add(0, new Source());
                Source source = new Source();
                source.setImage(source.getTabVariante()[1]);
                source.setDirection(1);
                rootScrollPane.getChildren().add(1,source);
                Source source1 = new Source();
                source1.setImage(source.getTabVariante()[2]);
                source1.setDirection(2);
                rootScrollPane.getChildren().add(2,source1);
                Source source2 = new Source();
                source2.setImage(source.getTabVariante()[3]);
                source2.setDirection(3);
                rootScrollPane.getChildren().add(3,source2);
                break;
            case "DIODE":
                rootScrollPane.getChildren().add(0, new Diode());
                Diode diode = new Diode();
                diode.setImage(diode.getTabVariante()[1]);
                diode.setDirection(1);
                rootScrollPane.getChildren().add(1,diode);
                Diode diode1 = new Diode();
                diode1.setImage(diode.getTabVariante()[2]);
                diode1.setDirection(2);
                rootScrollPane.getChildren().add(1,diode1);
                Diode diode2 = new Diode();
                diode2.setImage(diode.getTabVariante()[3]);
                diode2.setDirection(3);
                rootScrollPane.getChildren().add(1,diode2);
                break;
            case "FIL":
                rootScrollPane.getChildren().add(0, new Fil());
                Fil fil = new Fil();
                fil.setImage(fil.getTabVariante()[1]);
                fil.setDirection(1);
                rootScrollPane.getChildren().add(1,fil);
                Fil fil1 = new Fil();
                fil1.setImage(fil.getTabVariante()[2]);
                fil1.setDirection(2);
                rootScrollPane.getChildren().add(2,fil1);
                Fil fil2 = new Fil();
                fil2.setImage(fil.getTabVariante()[3]);
                fil2.setDirection(3);
                rootScrollPane.getChildren().add(3,fil2);
                Fil fil3 = new Fil();
                fil3.setImage(fil.getTabVariante()[4]);
                fil.setDirection(4);
                rootScrollPane.getChildren().add(4,fil3);
                Fil fil4 = new Fil();
                fil4.setImage(fil.getTabVariante()[5]);
                fil4.setDirection(5);
                rootScrollPane.getChildren().add(5,fil4);
                Fil fil5 = new Fil();
                fil5.setImage(fil.getTabVariante()[6]);
                fil5.setDirection(6);
                rootScrollPane.getChildren().add(6,fil5);
                Fil fil6 = new Fil();
                fil6.setImage(fil.getTabVariante()[7]);
                fil6.setDirection(7);
                rootScrollPane.getChildren().add(7,fil6);
                Fil fil7 = new Fil();
                fil7.setImage(fil.getTabVariante()[8]);
                fil7.setDirection(8);
                rootScrollPane.getChildren().add(8,fil7);
                Fil fil8 = new Fil();
                fil8.setImage(fil.getTabVariante()[9]);
                fil8.setDirection(9);
                rootScrollPane.getChildren().add(9,fil8);
                Fil fil9 = new Fil();
                fil9.setImage(fil.getTabVariante()[10]);
                fil9.setDirection(10);
                rootScrollPane.getChildren().add(10,fil9);
                break;
            case "FUSIBLE":
                rootScrollPane.getChildren().add(0, new Fusible());
                Fusible fusible = new Fusible();
                fusible.setImage(fusible.getTabVariante()[1]);
                fusible.setDirection(1);
                rootScrollPane.getChildren().add(1,fusible);
                break;
            case "HAUT-PARLEUR":
                rootScrollPane.getChildren().add(0, new HautParleur());
                HautParleur hautParleur = new HautParleur();
                hautParleur.setImage(hautParleur.getTabVariante()[1]);
                hautParleur.setDirection(1);
                rootScrollPane.getChildren().add(1,hautParleur);
                break;
            case "INTERRUPTEUR":
                rootScrollPane.getChildren().add(0, new Interrupteur());
                Interrupteur interrupteur = new Interrupteur();
                interrupteur.setImage(interrupteur.getTabVariante()[1]);
                interrupteur.setDirection(1);
                rootScrollPane.getChildren().add(1,interrupteur);
                break;
            case "MISE À TERRE":
                rootScrollPane.getChildren().add(0, new MiseAterre());
                MiseAterre miseAterre = new MiseAterre();
                miseAterre.setImage(miseAterre.getTabVariante()[1]);
                miseAterre.setDirection(1);
                rootScrollPane.getChildren().add(1,miseAterre);
                MiseAterre miseAterre1 = new MiseAterre();
                miseAterre1.setImage(miseAterre1.getTabVariante()[2]);
                miseAterre1.setDirection(2);
                rootScrollPane.getChildren().add(2,miseAterre1);
                MiseAterre miseAterre2 = new MiseAterre();
                miseAterre2.setImage(miseAterre2.getTabVariante()[3]);
                miseAterre2.setDirection(3);
                rootScrollPane.getChildren().add(3,miseAterre2);
                break;
            case "MOTEUR":
                //rootScrollPane.getChildren().add(0, new Fil());
                break;
            case "OHMÈTRE":
                rootScrollPane.getChildren().add(0, new Ohmetre());
                Ohmetre ohmetre = new Ohmetre();
                ohmetre.setImage(ohmetre.getTabVariante()[1]);
                ohmetre.setDirection(1);
                rootScrollPane.getChildren().add(1,ohmetre);
                break;
            case "RESISTEUR":
                rootScrollPane.getChildren().add(0, new Resisteur());
                Resisteur resisteur = new Resisteur();
                resisteur.setImage(resisteur.getTabVariante()[1]);
                resisteur.setDirection(1);
                rootScrollPane.getChildren().add(1,resisteur);
                break;
            case "VOLTMÈTRE":
                rootScrollPane.getChildren().add(0, new Voltmetre());
                Voltmetre voltmetre = new Voltmetre();
                voltmetre.setImage(voltmetre.getTabVariante()[1]);
                voltmetre.setDirection(1);
                rootScrollPane.getChildren().add(1, voltmetre);
                break;
            case "SWITCH":
                rootScrollPane.getChildren().add(0,new Switch());
                Switch aSwitch = new Switch();
                aSwitch.setImage(aSwitch.getTabVariante()[1]);
                aSwitch.setDirection(1);
                rootScrollPane.getChildren().add(1,aSwitch);
                break;
        }
    }


    public void setSandbox() {
        Main.changerDeMode(1);
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
            });
        }
        ContextMenu contextMenu = new ContextMenu(menuVariante);
        if (source.getNom().toUpperCase().equals("SOURCE")) {
            MenuItem itemIntensite = new MenuItem("Modifier l'intensité");
            MenuItem itemTension = new MenuItem("Modifier la tension");
            itemIntensite.setOnAction(event -> changerValeur(source, "l'intensité"));
            itemTension.setOnAction(event -> changerValeur(source, "la tension"));
            contextMenu.getItems().addAll(itemIntensite, itemTension);
        }
        if (source.getNom().toUpperCase().equals("RESISTEUR")) {
            MenuItem itemResistance = new MenuItem("Modifier la résistance");
            itemResistance.setOnAction(event -> changerValeur(source, "la résistance"));
            contextMenu.getItems().add(itemResistance);
        }
        contextMenu.getItems().add(itemSupprimer);
        source.setOnContextMenuRequested(event -> contextMenu.show(source, event.getScreenX(), event.getScreenY()));
        gridPaneSandBox.add(source, i, j);
    }

    // Generic function to find the index of an element in an object array in Java
    public static<T> int find(T[] tableau, T target)
    {
        return IntStream.range(0, tableau.length).filter(i -> target.equals(tableau[i])).findFirst().orElse(-1);
    }

    public static void remettreComposante(Composante composante) {
        String nom = composante.getNom().toUpperCase();
        switch (nom) {
            case "FIL":
                Fil fil = new Fil();
                fil.setImage(fil.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(0, fil);
                break;
            case "AMPEREMÈTRE":
                Amperemetre amperemetre = new Amperemetre();
                amperemetre.setImage(amperemetre.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(1, amperemetre);
                break;
            case "AMPOULE":
                Ampoule ampoule = new Ampoule();
                ampoule.setImage(ampoule.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(2, ampoule);
                break;
            case "SOURCE":
                Source source = new Source();
                source.setImage(source.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(3, source);
                break;
            case "DIODE":
                Diode diode = new Diode();
                diode.setImage(diode.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(4, diode);
                break;
            case "FUSIBLE":
                Fusible fusible = new Fusible();
                fusible.setImage(fusible.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(5, fusible);
                break;
            case "INTERRUPTEUR":
                Interrupteur interrupteur = new Interrupteur();
                interrupteur.setImage(interrupteur.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(6, interrupteur);
                break;
            case "MISE À TERRE":
                MiseAterre miseAterre = new MiseAterre();
                miseAterre.setImage(miseAterre.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(7, miseAterre);
                break;
            case "OHMÈTRE":
                Ohmetre ohmetre = new Ohmetre();
                ohmetre.setImage(ohmetre.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(8, ohmetre);
                break;
            case "RESISTEUR":
                Resisteur resisteur = new Resisteur();
                resisteur.setImage(resisteur.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(9, resisteur);
                break;
            case "VOLTMÈTRE":
                Voltmetre voltmetre = new Voltmetre();
                voltmetre.setImage(voltmetre.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(10, voltmetre);
                break;
            case "SWITCH":
                Switch aSwitch = new Switch();
                aSwitch.setImage(aSwitch.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(11, aSwitch);
                break;
            case "HAUT-PARLEUR":
                HautParleur hautParleur = new HautParleur();
                hautParleur.setImage(hautParleur.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(12, hautParleur);
                break;
            case "MOTEUR":
                Moteur moteur = new Moteur();
                moteur.setImage(moteur.getTabVariante()[find(composante.getTabVariante(),composante.getImage())]);
                rootScrollPane.getChildren().add(13, moteur);
                break;
        }
    }

    public static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void verification() {
        boolean sourceFound = false;
        Source source = null;

        for (int i = 0; i < 20 && !sourceFound; i++)
            for (int j = 0; j < 20 && !sourceFound; j++)
                if (((Composante) getNodeFromGridPane(gridPaneSandBox, i, j)).getNom().toUpperCase().equals("SOURCE")) {
                    sourceFound = true;
                    source = (Source) getNodeFromGridPane(gridPaneSandBox, i, j);
                }
        Maille mailleInitiale = new Maille(source.getAmperage());
        if (sourceFound) {
            int row = source.getRow();
            int col = source.getCol();
            String dir = null;
            boolean finished = false;
            boolean error = false;
            switch (source.getDirection()) {
                case 0:
                    dir = "up";
                    row--;
                    System.out.println("source up " + dir + source.getDirection() + row + col);
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
                if (!((Composante) getNodeFromGridPane(gridPaneSandBox, col, row)).getNom().toUpperCase().equals("SOURCE")) {
                    mailleInitiale.getCircuit().add(new Coordonnée(row, col));
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
                                    System.out.println("fil left");
                                    break;
                                case "NSE":
                                    break;
                                case "NSO":
                                    break;
                                case "SOE":
                                    break;
                                case "NSEO":
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
                                    System.out.println("fil up");
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
                                    break;
                                case "SOE":
                                    break;
                                case "NEO":
                                    break;
                                case "NSEO":
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
                                    System.out.println("resisteur down");
                                    break;
                                case "SN":
                                    dir = "down";
                                    row++;
                                    break;
                                case "NE":
                                    dir = "right";
                                    col++;
                                    System.out.println("fil right");
                                    break;
                                case "NO":
                                    dir = "left";
                                    col--;
                                    break;
                                case "NSE":
                                    break;
                                case "NSO":
                                    break;
                                case "NEO":
                                    break;
                                case "NSEO":
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
                                    break;
                                case "SOE":
                                    break;
                                case "NEO":
                                    break;
                                case "NSEO":
                                    break;
                                default:
                                    error = true;
                                    break;
                            }
                            break;
                    }
                } else
                    finished = true;
            }
            if (!error)
                mailleInitiale.calculer(gridPaneSandBox);
            else
                System.out.println("ERROR");

        } else
            System.out.print("ERROR: PAS DE SOURCE");
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
}