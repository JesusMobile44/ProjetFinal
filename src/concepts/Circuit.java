package concepts;

import composantes.*;
import concepts.capaciteEquivalente.Graphe;
import controllers.SandboxController;

import java.util.ArrayList;

public class Circuit {

    private ArrayList<Composante> composantes = new ArrayList<>();
    private ArrayList<NouvelleMaille> mailles = new ArrayList<>();
    private ArrayList<Noeud> noeuds = new ArrayList<>();
    private ArrayList<Branche> branches = new ArrayList<>();
    private ArrayList<Resisteur> resisteurs = new ArrayList<>();
    private ArrayList<Source> sources = new ArrayList<>();
    private MiseAterre miseAterre = null;
    private double resistanceEquivalente;
    private boolean enSerie;
    private boolean incomplet;
    private boolean mATMultiples;

    public Circuit() {
        this.enSerie = true;
        this.resistanceEquivalente = 0;
        this.incomplet = true;
        this.mATMultiples = false;
    }

    public void calculVariables() {
        if (this.enSerie) {
            calculSerie();
        } else {
            boolean okDiode = false;
            while (!okDiode){
                okDiode = true;
                calculParallele();
                for (int i=0; i<this.getBranches().size(); i++){
                    for (int j=0; j<this.getBranches().get(i).getDiodes().size(); j++){
                        if (this.getBranches().get(i).getDiodes().get(j).getNoeudDirectionnel() == this.getBranches().get(i).getNoeudDirectionnel()){
                            retirerBrancheDiode(this.getBranches().get(i));
                            this.getBranches().remove(i);
                            okDiode = false;
                        }
                    }
                }
            }
        }

        if (this.enSerie && this.getBranches().get(0).getIntensite()==0){
        }else {
            determinationSens();
        }

        if (!this.mATMultiples){
            creationCircuitMAT(this.miseAterre);
        }

        //Graphe graphe = new Graphe(this);

        reloadTooltip();
    }

    private static void creationCircuitMAT(MiseAterre miseAterre){
        if (miseAterre == null){

        }


    }

    private static void retirerBrancheDiode(Branche branche){
        for (Composante composante:
             branche.getComposantesBranche()) {
            if (!(composante instanceof Source)){
                composante.setVolt(0);
            }
            composante.setAmperage(0);
            composante.setSensCourant("∅");
        }
    }

    private void calculParallele() {
        updateNoeudsDirectionnels();

        double[][] matrice = new double[this.getBranches().size() - (this.getNoeuds().size() - 1) + this.getNoeuds().size() - 1][this.getBranches().size() + 1];
        Noeud[] noeuds = new Noeud[this.getNoeuds().size() - 1];
        NouvelleMaille[] mailles = new NouvelleMaille[this.getBranches().size() - (this.getNoeuds().size() - 1)];

        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = 0;
            }
        }

        for (int i = 0; i < noeuds.length; i++) {
            noeuds[i] = this.getNoeuds().get(i);
        }

        boolean maillesOK = false;
        int[] numeroMailles = new int[mailles.length];

        while (!maillesOK) {

            maillesOK = true;
            boolean[] brancheFound = new boolean[this.getBranches().size()];
            for (int i = 0; i < brancheFound.length; i++) {
                brancheFound[i] = false;
            }

            int number = 0;

            for (int i = 0; i < numeroMailles.length; i++) {

                boolean sameNumber = true;
                while (sameNumber) {
                    sameNumber = false;

                    number = (int) (Math.random() * this.getMailles().size());
                    for (int j = 0; j < i; j++) {
                        if (number == numeroMailles[j]) {
                            sameNumber = true;
                        }
                    }
                }
                numeroMailles[i] = number;
            }

            for (int i = 0; i < mailles.length; i++) {
                mailles[i] = this.getMailles().get(numeroMailles[i]);
            }

            for (NouvelleMaille maille : mailles) {
                for (int j = 0; j < maille.getBranchesMaille().size(); j++) {
                    for (int k = 0; k < this.getBranches().size(); k++) {
                        if (maille.getBranchesMaille().get(j) == this.getBranches().get(k)) {
                            brancheFound[k] = true;
                        }
                    }
                }
            }

            for (boolean b : brancheFound) {
                if (!b) {
                    maillesOK = false;
                }
            }
        }

        for (int i = 0; i < noeuds.length; i++) {
            for (int j = 0; j < matrice[i].length - 1; j++) {
                for (int k = 0; k < noeuds[i].getBranchesAdjacentes().size(); k++) {
                    if (noeuds[i].getBranchesAdjacentes().get(k) == this.getBranches().get(j)) {
                        if (noeuds[i].getBranchesAdjacentes().get(k).getNoeudDirectionnel() == noeuds[i]) {
                            matrice[i][j] = 1;
                        } else {
                            matrice[i][j] = -1;
                        }
                    }
                }
            }
        }

        for (int i = noeuds.length; i < matrice.length; i++) {
            NouvelleMaille mailleTempo = mailles[i - noeuds.length];

            for (int j = 0; j < mailleTempo.getBranchesMaille().size(); j++) {
                Branche brancheTempo = mailleTempo.getBranchesMaille().get(j);
                Noeud noeudTempo = mailleTempo.getNoeudsMaille().get(j);
                int posBranche = 0;

                for (int k = 0; k < this.getBranches().size(); k++) {
                    if (brancheTempo == this.getBranches().get(k)) {
                        posBranche = k;
                    }
                }

                for (int k = 0; k < brancheTempo.getResisteurs().size(); k++) {
                    if (brancheTempo.getNoeudDirectionnel() == noeudTempo) {
                        matrice[i][posBranche] -= brancheTempo.getResisteurs().get(k).getResistance();
                    } else {
                        matrice[i][posBranche] += brancheTempo.getResisteurs().get(k).getResistance();
                    }
                }

                for (int k = 0; k < brancheTempo.getSources().size(); k++) {
                    if (brancheTempo.getSources().get(k).getNoeudDirectionnel() == noeudTempo) {
                        matrice[i][matrice[i].length - 1] -= brancheTempo.getSources().get(k).getVolt();
                    } else {
                        matrice[i][matrice[i].length - 1] += brancheTempo.getSources().get(k).getVolt();
                    }
                }
            }
        }

        Matrice matrice1 = new Matrice(matrice.length, matrice[0].length, matrice);
        matrice1.triangleSuperieur();
        matrice = matrice1.getMatrice();
        double[] pivot = matrice1.calculPivot();

        for (int i = 0; i < this.getBranches().size(); i++) {
            this.getBranches().get(i).setIntensite(pivot[i]);
            if (this.getBranches().get(i).getIntensite() < 0) {
                this.getBranches().get(i).setIntensite(this.getBranches().get(i).getIntensite() * -1);
                if (this.getBranches().get(i).getNoeudDirectionnel() == this.getBranches().get(i).getNoeudsAdjacents().get(0)) {
                    this.getBranches().get(i).setNoeudDirectionnel(this.getBranches().get(i).getNoeudsAdjacents().get(1));
                } else {
                    this.getBranches().get(i).setNoeudDirectionnel(this.getBranches().get(i).getNoeudsAdjacents().get(0));
                }
            }
            for (int j = 0; j < this.getBranches().get(i).getComposantesBranche().size(); j++) {
                this.getBranches().get(i).getComposantesBranche().get(j).setAmperage(this.getBranches().get(i).getIntensite());
            }
        }

        for (Resisteur resiteur : this.getResisteurs()) {
            resiteur.setVolt(resiteur.getAmperage() * resiteur.getResistance());
        }

        if (this.getSources().size() == 1){
            this.setResistanceEquivalente(this.getSources().get(0).getVolt() * this.getSources().get(0).getAmperage());
        }
    }

    private void calculSerie() {
        for (int i = 0; i < this.getResisteurs().size(); i++) {
            resistanceEquivalente += this.getResisteurs().get(i).getResistance();
        }
        float tensionTotaleSource = 0;
        for (int i = 0; i < this.getSources().size(); i++) {
            if (this.getSources().get(i).isInverseEnSerie()){
                tensionTotaleSource -= this.getSources().get(i).getVolt();
            }
            else {
                tensionTotaleSource += this.getSources().get(i).getVolt();
            }
        }

        this.getBranches().get(0).setIntensite((tensionTotaleSource) / (resistanceEquivalente));

        if (this.getBranches().get(0).getIntensite() <0){
            this.getBranches().get(0).setIntensite(this.getBranches().get(0).getIntensite()*-1);
            for (Diode diode:
                 this.getBranches().get(0).getDiodes()) {
                if (diode.isInverseEnSerie()){
                    diode.setInverseEnSerie(false);
                }else {
                    diode.setInverseEnSerie(true);
                }
            }
        }

        for (int i = 0; i < this.getComposantes().size(); i++) {
            this.getComposantes().get(i).setAmperage(this.getBranches().get(0).getIntensite());
            if (this.getComposantes().get(i).getNom().toUpperCase().equals("RESISTEUR")) {
                this.getComposantes().get(i).setVolt(this.getComposantes().get(i).getResistance() * this.getComposantes().get(i).getAmperage());
            }
        }

        boolean diodeInverse = true;

        if (this.getBranches().get(0).getDiodes().size() != 0){
            diodeInverse = false;
            for (Diode diode:
                    this.getBranches().get(0).getDiodes()) {

                if (diode.isInverseEnSerie()){
                    diodeInverse = true;
                }
            }
        }


        if (!diodeInverse){
            retirerBrancheDiode(this.getBranches().get(0));
            this.getBranches().get(0).setIntensite(0);
        }
    }

    private void determinationSens() {
        if (this.enSerie) {
            ArrayList<Source> sources = new ArrayList<>();
            ArrayList<Integer> sourcesPos = new ArrayList<>();
            ArrayList<Source> inverses = new ArrayList<>();
            ArrayList<Integer> inversesPos = new ArrayList<>();
            String dir = null;
            switch (composantes.get(0).getDirection()) {
                case 0:
                    dir = "up";
                    break;
                case 1:
                    dir = "right";
                    break;
                case 2:
                    dir = "down";
                    break;
                case 3:
                    dir = "left";
                    break;
            }
            for (int i = 0; i < composantes.size(); i++) {
                switch (dir) {
                    case "up":
                        switch (composantes.get(i).getTabNomVariante()[composantes.get(i).getDirection()]) {
                            case "NS":
                                if (composantes.get(i).getNom().toUpperCase().equals("SOURCE")) {
                                    sources.add((Source) composantes.get(i));
                                    sourcesPos.add(i);
                                }
                                dir = "up";
                                break;
                            case "SN":
                                if (composantes.get(i).getNom().toUpperCase().equals("SOURCE")) {
                                    inverses.add((Source) composantes.get(i));
                                    inversesPos.add(i);
                                }
                                dir = "up";
                                break;
                            case "SE":
                                dir = "right";
                                break;
                            case "SO":
                                dir = "left";
                                break;
                            default:
                                break;
                        }
                        break;
                    case "right":
                        switch (composantes.get(i).getTabNomVariante()[composantes.get(i).getDirection()]) {
                            case "NO":
                                dir = "up";
                                break;
                            case "SO":
                                dir = "down";
                                break;
                            case "OE":
                                if (composantes.get(i).getNom().toUpperCase().equals("SOURCE")) {
                                    sources.add((Source) composantes.get(i));
                                    sourcesPos.add(i);
                                }
                                dir = "right";
                                break;
                            case "EO":
                                if (composantes.get(i).getNom().toUpperCase().equals("SOURCE")) {
                                    inverses.add((Source) composantes.get(i));
                                    inversesPos.add(i);
                                }
                                dir = "right";
                                break;
                            default:
                                break;
                        }
                        break;
                    case "down":
                        switch (composantes.get(i).getTabNomVariante()[composantes.get(i).getDirection()]) {
                            case "NS":
                                if (composantes.get(i).getNom().toUpperCase().equals("SOURCE")) {
                                    inverses.add((Source) composantes.get(i));
                                    inversesPos.add(i);
                                }
                                dir = "down";
                                break;
                            case "SN":
                                if (composantes.get(i).getNom().toUpperCase().equals("SOURCE")) {
                                    sources.add((Source) composantes.get(i));
                                    sourcesPos.add(i);
                                }
                                dir = "down";
                                break;
                            case "NE":
                                dir = "right";
                                break;
                            case "NO":
                                dir = "left";
                                break;
                            default:
                                break;
                        }
                        break;
                    case "left":
                        switch (composantes.get(i).getTabNomVariante()[composantes.get(i).getDirection()]) {
                            case "NE":
                                dir = "up";
                                break;
                            case "SE":
                                dir = "down";
                                break;
                            case "OE":
                                if (composantes.get(i).getNom().toUpperCase().equals("SOURCE")) {
                                    inverses.add((Source) composantes.get(i));
                                    inversesPos.add(i);
                                }
                                dir = "left";
                                break;
                            case "EO":
                                if (composantes.get(i).getNom().toUpperCase().equals("SOURCE")) {
                                    sources.add((Source) composantes.get(i));
                                    sourcesPos.add(i);
                                }
                                dir = "left";
                                break;
                            default:
                                break;
                        }
                        break;
                }
            }
            double vSource = 0;
            double vInverse = 0;
            for (Source source : sources) vSource += source.getVolt();
            for (Source invers : inverses) vInverse += invers.getVolt();
            int start;
            boolean inverse;
            if (vSource > vInverse) {
                start = sourcesPos.get(0);
                inverse = false;
            } else {
                start = inversesPos.get(0);
                inverse = true;
            }
            switch (composantes.get(start).getDirection()) {
                case 0:
                    dir = "S";
                    break;
                case 1:
                    dir = "O";
                    break;
                case 2:
                    dir = "N";
                    break;
                case 3:
                    dir = "E";
                    break;
            }
            if (inverse) {
                for (int i = start; i > -1; i--) {
                    dir = switchSensCourantSerie(dir, composantes.get(i));
                }
                for (int i = composantes.size() - 1; i > start; i--) {
                    dir = switchSensCourantSerie(dir, composantes.get(i));
                }
            } else {
                for (int i = start; i < composantes.size(); i++) {
                    dir = switchSensCourantSerie(dir, composantes.get(i));
                }
                for (int i = 0; i < start; i++) {
                    dir = switchSensCourantSerie(dir, composantes.get(i));
                }
            }

        } else {
            for (Branche branch : branches) {
                String dir = null;
                for (int j = 0; j < branch.getNoeudDirectionnel().getBranchesAdjacentes().size() && dir == null; j++) {
                    if (branch.getNoeudDirectionnel().getBranchesAdjacentes().get(j) == branch) {
                        dir = branch.getNoeudDirectionnel().getDirections()[j];
                    }
                }
                for (int j = 0; j < branch.getComposantesBranche().size(); j++) {
                    switchSensCourantParallele(dir, branch.getNoeudDirectionnel());
                }
            }
        }
    }

    private void switchSensCourantParallele(String dir, Noeud initial){
        int row = initial.getComposanteNoeud().getRow();
        int col = initial.getComposanteNoeud().getCol();

        boolean finished = false;

        switch (dir) {
            case "N":
                dir = "S";
                row--;
                break;
            case "E":
                dir = "O";
                col++;
                break;
            case "S":
                dir = "N";
                row++;
                break;
            case "O":
                dir = "E";
                col--;
                break;
        }

            while (!finished) {

                Composante composante = ((Composante) SandboxController.getNodeFromGridPane(SandboxController.gridPaneSandBox, col, row));

                switch (dir.toUpperCase()) {
                    case "N":
                        switch (composante.getTabNomVariante()[composante.getDirection()]) {
                            case "NS":
                            case "SN":
                                composante.setSensCourant("↓");
                                dir = "N";
                                row++;
                                break;
                            case "NE":
                                composante.setSensCourant("→");
                                dir = "O";
                                col++;
                                break;
                            case "NO":
                                composante.setSensCourant("←");
                                dir = "E";
                                col--;
                                break;
                            default:
                                composante.setSensCourant("∅");
                                finished = true;
                                break;
                        }
                        break;
                    case "E":
                        switch (composante.getTabNomVariante()[composante.getDirection()]) {
                            case "NE":
                                composante.setSensCourant("↑");
                                dir = "S";
                                row--;
                                break;
                            case "SE":
                                composante.setSensCourant("↓");
                                dir = "N";
                                row++;
                                break;
                            case "OE":
                            case "EO":
                                composante.setSensCourant("←");
                                dir = "E";
                                col--;
                                break;
                            default:
                                composante.setSensCourant("∅");
                                finished = true;
                                break;
                        }
                        break;
                    case "S":
                        switch (composante.getTabNomVariante()[composante.getDirection()]) {
                            case "NS":
                            case "SN":
                                composante.setSensCourant("↑");
                                dir = "S";
                                row--;
                                break;
                            case "SE":
                                composante.setSensCourant("→");
                                dir = "O";
                                col++;
                                break;
                            case "SO":
                                composante.setSensCourant("←");
                                dir = "E";
                                col--;
                                break;
                            default:
                                composante.setSensCourant("∅");
                                finished = true;
                                break;
                        }
                        break;
                    case "O":
                        switch (composante.getTabNomVariante()[composante.getDirection()]) {
                            case "NO":
                                composante.setSensCourant("↑");
                                dir = "S";
                                row--;
                                break;
                            case "SO":
                                composante.setSensCourant("↓");
                                dir = "N";
                                row++;
                                break;
                            case "OE":
                            case "EO":
                                composante.setSensCourant("→");
                                dir = "O";
                                col++;
                                break;
                            default:
                                composante.setSensCourant("∅");
                                finished = true;
                                break;
                        }
                        break;
                }
            }

    }

    private void reloadTooltip() {
        for (int i = 0; i < this.getComposantes().size(); i++) {
            Composante composante = this.getComposantes().get(i);
            switch (composante.getNom().toUpperCase()) {
                case "FIL":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nSens du courant: " + composante.getSensCourant());
                    break;
                case "AMPÈREMÈTRE":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nSens du courant: " + composante.getSensCourant());
                    break;
                case "AMPOULE":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nSens du courant: " + composante.getSensCourant());
                    break;
                case "SOURCE":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nTension: " + SandboxController.df.format(composante.getVolt()) + "\nSens du courant: " + composante.getSensCourant());
                    if (this.getResistanceEquivalente() != 0){
                        composante.getTooltip().setText(composante.getTooltip().getText() + "\nRésistance équivalente : "+ SandboxController.df.format(this.getResistanceEquivalente()));
                    }
                    break;
                case "DIODE":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nSens du courant: " + composante.getSensCourant());
                    break;
                case "FUSIBLE":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nSens du courant: " + composante.getSensCourant());
                    break;
                case "INTERRUPTEUR":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nSens du courant: " + composante.getSensCourant());
                    break;
                case "MISE À TERRE":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + "\nSens du courant: " + composante.getSensCourant());
                    break;
                case "OHMMÈTRE":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nRésistance: " + SandboxController.df.format(composante.getVolt()) + "\nSens du courant: " + composante.getSensCourant());
                    break;
                case "RÉSISTEUR":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nTension: " + SandboxController.df.format(composante.getVolt()) + "\nRésistance: " + SandboxController.df.format(composante.getResistance()) + "\nSens du courant: " + composante.getSensCourant());
                    break;
                case "VOLTMÈTRE":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nDifférence de potentiel: " + SandboxController.df.format(composante.getVolt()) + "\nSens du courant: " + composante.getSensCourant());
                    break;
                case "SWITCH":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nSens du courant: " + composante.getSensCourant());
                    break;
                case "HAUT-PARLEUR":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nSens du courant: " + composante.getSensCourant());
                    break;
                case "MOTEUR":
                    composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nSens du courant: " + composante.getSensCourant());
                    break;
            }
            //composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nTension: " + SandboxController.df.format(composante.getVolt()) + "\nRésistance: " + SandboxController.df.format(composante.getResistance()));
        }
    }

    private void updateNoeudsDirectionnels() {
        for (int i = 0; i < this.getBranches().size(); i++) {
            this.getBranches().get(i).setNoeudDirectionnel(this.getBranches().get(i).getNoeudsAdjacents().get(0));
        }
    }

    private String switchSensCourantSerie(String dir, Composante composante) {
        switch (dir.toUpperCase()) {
            case "N":
                switch (composante.getTabNomVariante()[composante.getDirection()]) {
                    case "NS":
                    case "SN":
                        composante.setSensCourant("↓");
                        dir = "N";
                        break;
                    case "NE":
                        composante.setSensCourant("→");
                        dir = "O";
                        break;
                    case "NO":
                        composante.setSensCourant("←");
                        dir = "E";
                        break;
                    default:
                        composante.setSensCourant("∅");
                        break;
                }
                break;
            case "E":
                switch (composante.getTabNomVariante()[composante.getDirection()]) {
                    case "NE":
                        composante.setSensCourant("↑");
                        dir = "S";
                        break;
                    case "SE":
                        composante.setSensCourant("↓");
                        dir = "N";
                        break;
                    case "OE":
                    case "EO":
                        composante.setSensCourant("←");
                        dir = "E";
                        break;
                    default:
                        composante.setSensCourant("∅");
                        break;
                }
                break;
            case "S":
                switch (composante.getTabNomVariante()[composante.getDirection()]) {
                    case "NS":
                    case "SN":
                        composante.setSensCourant("↑");
                        dir = "S";
                        break;
                    case "SE":
                        composante.setSensCourant("→");
                        dir = "O";
                        break;
                    case "SO":
                        composante.setSensCourant("←");
                        dir = "E";
                        break;
                    default:
                        composante.setSensCourant("∅");
                        break;
                }
                break;
            case "O":
                switch (composante.getTabNomVariante()[composante.getDirection()]) {
                    case "NO":
                        composante.setSensCourant("↑");
                        dir = "S";
                        break;
                    case "SO":
                        composante.setSensCourant("↓");
                        dir = "N";
                        break;
                    case "OE":
                    case "EO":
                        composante.setSensCourant("→");
                        dir = "O";
                        break;
                    default:
                        composante.setSensCourant("∅");
                        break;
                }
                break;
        }
        return dir;
    }

    public ArrayList<NouvelleMaille> getMailles() {
        return mailles;
    }

    public void setMailles(ArrayList<NouvelleMaille> mailles) {
        this.mailles = mailles;
    }

    public ArrayList<Noeud> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(ArrayList<Noeud> noeuds) {
        this.noeuds = noeuds;
    }

    public ArrayList<Branche> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<Branche> branches) {
        this.branches = branches;
    }

    public ArrayList<Resisteur> getResisteurs() {
        return resisteurs;
    }

    public void setResisteurs(ArrayList<Resisteur> resisteurs) {
        this.resisteurs = resisteurs;
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    public void setSources(ArrayList<Source> sources) {
        this.sources = sources;
    }

    public ArrayList<Composante> getComposantes() {
        return composantes;
    }

    public void setComposantes(ArrayList<Composante> composantes) {
        this.composantes = composantes;
    }

    public boolean isEnSerie() {
        return enSerie;
    }

    public void setEnSerie(boolean enSerie) {
        this.enSerie = enSerie;
    }

    public double getResistanceEquivalente() {
        return resistanceEquivalente;
    }

    public void setResistanceEquivalente(double resistanceEquivalente) {
        this.resistanceEquivalente = resistanceEquivalente;
    }

    public boolean isIncomplet() {
        return incomplet;
    }

    public void setIncomplet(boolean incomplet) {
        this.incomplet = incomplet;
    }

    public MiseAterre getMiseAterre() {
        return miseAterre;
    }

    public void setMiseAterre(MiseAterre miseAterre) {
        this.miseAterre = miseAterre;
    }

    public boolean ismATMultiples() {
        return mATMultiples;
    }

    public void setmATMultiples(boolean mATMultiples) {
        this.mATMultiples = mATMultiples;
    }
}
