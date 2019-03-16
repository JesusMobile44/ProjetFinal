package controllers;

import composantes.Composante;
import composantes.ComposanteVide;
import composantes.*;
import concepts.Branche;
import concepts.Circuit;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import main.Main;

import java.text.DecimalFormat;


public class SandboxController {

    public static GridPane gridPaneSandBox = new GridPane();
    public static FlowPane rootDescription = new FlowPane();
    private static FlowPane rootScrollPane = new FlowPane();
    public static DecimalFormat df = new DecimalFormat("#.##");
    public static Circuit circuit1= new Circuit();

    @FXML
    private SplitPane mySplitPane;

    @FXML
    private FlowPane affichageDescription;

    @FXML
    private ScrollPane scroll;

    @FXML
    private ScrollPane myScrollPane;

    private Circuit circuit = new Circuit();


    @FXML
    public void initialize() {
        scroll.setContent(gridPaneSandBox);

        gridPaneSandBox.setPrefSize(376, 414);

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
        //rootScrollPane.getChildren().add(new Moteur());
        //rootScrollPane.getChildren().add(new HautParleur());
        rootScrollPane.setPadding(new Insets(16));
        rootScrollPane.setVgap(16);
        rootScrollPane.setHgap(16);


        myScrollPane.setContent(rootScrollPane);

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
                updateCircuit();
            });

        }
        ContextMenu contextMenu = new ContextMenu(menuVariante);

        if (source.getNom().toUpperCase().equals("SOURCE")) {
            MenuItem itemIntensite = new MenuItem("Modifier l'intensité");
            MenuItem itemTension = new MenuItem("Modifier la tension");
            itemIntensite.setOnAction(event -> {
                changerValeur(source, "l'intensité");
                updateCircuit();
            });
            itemTension.setOnAction(event -> {
                changerValeur(source, "la tension");
                updateCircuit();
            });
            contextMenu.getItems().addAll(itemIntensite, itemTension);
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
            gridPaneSandBox.getChildren().remove(source);
            ComposanteVide vide = new ComposanteVide();
            vide.fitHeightProperty().set(100);
            vide.fitWidthProperty().set(100);
            vide.setRow(source.getRow());
            vide.setCol(source.getCol());
            gridPaneSandBox.add(vide, source.getCol(), source.getRow());
        });
        contextMenu.getItems().add(itemSupprimer);
        source.setOnContextMenuRequested(event -> contextMenu.show(source, event.getScreenX(), event.getScreenY()));
        gridPaneSandBox.add(source, i, j);
    }

    public static void remettreComposante(String nom) {

        switch (nom.toUpperCase()) {
            case "AMPEREMÈTRE":
                rootScrollPane.getChildren().add(1, new Amperemetre());
                break;
            case "AMPOULE":
                rootScrollPane.getChildren().add(2, new Ampoule());
                break;
            case "SOURCE":
                rootScrollPane.getChildren().add(3, new Source());
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
                rootScrollPane.getChildren().add(9, new Resisteur());
                break;
            case "VOLTMÈTRE":
                rootScrollPane.getChildren().add(10, new Voltmetre());

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

    public static void updateCircuit(){
        circuit1 = new Circuit();
        if (circuit1.isEnSerie()){
            creerSerie();
        }
        if (!circuit1.isEnSerie()){
            circuit1 = new Circuit();
            circuit1.setEnSerie(false);
            creerBranches();
            creerMailles();
            creerNoeuds();
        }
        remplirCircuit();

        if (!circuit1.isIncomplet())
            circuit1.calculVariables();
    }


    public static void creerMailles(){

    }

    public static void creerNoeuds(){

    }

    public static void creerBranches(){

    }

    public static void creerSerie(){

        boolean debutFound = false;
        Composante composanteInitiale = null;
        Branche brancheTemporaire = new Branche();

        //Au début, s'il y a pas de noeuds, le circuit cherche un source pour commencer
        if (circuit1.isEnSerie()){
            for (int i = 0; i < 20 && !debutFound; i++)
                for (int j = 0; j < 20 && !debutFound; j++)
                    if (((Composante) getNodeFromGridPane(gridPaneSandBox, i, j)).getNom().toUpperCase().equals("SOURCE")) {
                        debutFound = true;
                        composanteInitiale = (Composante) getNodeFromGridPane(gridPaneSandBox, i, j);
                        brancheTemporaire.getComposantesBranche().add(composanteInitiale);
                        brancheTemporaire.getSources().add((Source)composanteInitiale);
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

                    brancheTemporaire.getComposantesBranche().add((Composante) (getNodeFromGridPane(gridPaneSandBox, col, row)));

                    //Check si cest une source
                    if (((Composante) (getNodeFromGridPane(gridPaneSandBox, col, row))).getNom().toUpperCase().equals("SOURCE")){
                        brancheTemporaire.getSources().add(((Source) (getNodeFromGridPane(gridPaneSandBox, col, row))));
                    }

                    //Check si c'est un résisteur
                    if (((Composante) (getNodeFromGridPane(gridPaneSandBox, col, row))).getNom().toUpperCase().equals("RESISTEUR")){
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
                                    finished=true;
                                    break;
                                case "NSO":
                                    circuit1.setEnSerie(false);
                                    finished=true;
                                    break;
                                case "SOE":
                                    circuit1.setEnSerie(false);
                                    finished=true;
                                    break;
                                case "NSEO":
                                    circuit1.setEnSerie(false);
                                    finished=true;
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
                                    finished=true;
                                    break;
                                case "SOE":
                                    circuit1.setEnSerie(false);
                                    finished=true;
                                    break;
                                case "NEO":
                                    circuit1.setEnSerie(false);
                                    finished=true;
                                    break;
                                case "NSEO":
                                    circuit1.setEnSerie(false);
                                    finished=true;
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
                                    finished=true;
                                    break;
                                case "NSO":
                                    circuit1.setEnSerie(false);
                                    finished=true;
                                    break;
                                case "NEO":
                                    circuit1.setEnSerie(false);
                                    finished=true;
                                    break;
                                case "NSEO":
                                    circuit1.setEnSerie(false);
                                    finished=true;
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
                                    finished=true;
                                    break;
                                case "SOE":
                                    circuit1.setEnSerie(false);
                                    finished=true;
                                    break;
                                case "NEO":
                                    circuit1.setEnSerie(false);
                                    finished=true;
                                    break;
                                case "NSEO":
                                    circuit1.setEnSerie(false);
                                    finished=true;
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

            //if (!error)
            //    mailleInitiale.calculer(gridPaneSandBox);

            if (!error){
                circuit1.getBranches().add(brancheTemporaire);
                circuit1.setIncomplet(false);
                System.out.println("Added");
            }

            else {
                System.out.println("ERROR");
            }

        } else
            System.out.println("ERROR: PAS DE SOURCE");
    }

    public static void remplirCircuit(){
        for (int i=0; i<circuit1.getBranches().size(); i++){
            for (int j=0; j<circuit1.getBranches().get(i).getComposantesBranche().size(); j++){
                circuit1.getComposantes().add(circuit1.getBranches().get(i).getComposantesBranche().get(j));
                if (circuit1.getBranches().get(i).getComposantesBranche().get(j).getNom().toUpperCase().equals("SOURCE")){
                    circuit1.getSources().add((Source)circuit1.getBranches().get(i).getComposantesBranche().get(j));
                }
                if (circuit1.getBranches().get(i).getComposantesBranche().get(j).getNom().toUpperCase().equals("RESISTEUR")){
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
}