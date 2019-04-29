package concepts.miseATerre;


import composantes.*;
import concepts.Circuit;
import concepts.Noeud;
import controllers.SandboxController;

import java.util.ArrayList;
import java.util.Stack;

public class CircuitMAT {

    private ArrayList<BrancheMAT> branchesMAT = new ArrayList<>();
    private ArrayList<NoeudMAT> noeudMATS = new ArrayList<>();
    private BrancheMAT brancheInitiale;

    public CircuitMAT(Circuit circuit) {
        creerNoeudsMAT(circuit);
        creerBranchesMAT(circuit);
        updatePotentiel(circuit);
    }

    public void updatePotentiel(Circuit circuit){
        if (circuit.getMiseAterre() == null){
            this.brancheInitiale = branchesMAT.get(0);
            branchesMAT.get(0).setPotentielDonne(true);
        }
        else {
            for (BrancheMAT brancheMAT: this.getBranchesMAT()){
                for (Composante composante: brancheMAT.getComposantes()){
                    if (composante instanceof MiseAterre){
                        this.brancheInitiale = brancheMAT;
                        brancheMAT.setPotentielDonne(true);
                    }
                }
            }
        }

        boolean allPotentielsGiven = false;
        int j=0;

        while (!allPotentielsGiven){
            for (NoeudMAT noeudMAT : noeudMATS) {

                double potentiel = 0;
                boolean potentielBrancheAdjacente = false;

                for (BrancheMAT brancheMAT : noeudMAT.getTabDirections()[0].getBranches()) {
                    if (brancheMAT.isPotentielDonne()) {
                        potentiel = brancheMAT.getPotentiel();
                        potentielBrancheAdjacente = true;
                    }
                }
                if (potentielBrancheAdjacente) {
                    for (BrancheMAT brancheMAT : noeudMAT.getTabDirections()[1].getBranches()) {
                        brancheMAT.setPotentiel(potentiel+(noeudMAT.getDifferenceDePotentiel() * noeudMAT.getTabDirections()[1].getMultiplicateur()));
                        brancheMAT.setPotentielDonne(true);
                    }
                }

                potentiel = 0;
                potentielBrancheAdjacente = false;

                for (BrancheMAT brancheMAT : noeudMAT.getTabDirections()[1].getBranches()) {
                    if (brancheMAT.isPotentielDonne()) {
                        potentiel = brancheMAT.getPotentiel();
                        potentielBrancheAdjacente = true;
                    }
                }
                if (potentielBrancheAdjacente) {
                    for (BrancheMAT brancheMAT : noeudMAT.getTabDirections()[0].getBranches()) {
                        brancheMAT.setPotentiel(potentiel+(noeudMAT.getDifferenceDePotentiel() * noeudMAT.getTabDirections()[0].getMultiplicateur()));
                        brancheMAT.setPotentielDonne(true);
                    }
                }
            }

            allPotentielsGiven = true;
            for (BrancheMAT brancheMAT: branchesMAT){
                if (!brancheMAT.isPotentielDonne()){
                    allPotentielsGiven = false;
                }
            }

            if (j>(noeudMATS.size()*2))
                allPotentielsGiven = true;

            j++;
        }

        for (BrancheMAT brancheMAT: branchesMAT){
            for (Composante composante: brancheMAT.getComposantes()){
                if (brancheMAT.isPotentielDonne())
                    composante.setVolt(brancheMAT.getPotentiel());
            }
        }
        System.out.println("");
    }

    public void creerBranchesMAT(Circuit circuit){

        for (int i = 0; i < this.getNoeudMATS().size(); i++){
            for (int j = 0; j < this.getNoeudMATS().get(i).getDirectionsAnalysees().length; j++){

                for (Composante composante: circuit.getComposantes()){
                    if (composante instanceof Fil){
                        ((Fil) composante).setNoeudMAT(false);
                    }
                }

                Noeud noeudTemporaire = null;
                Stack<Noeud> noeudsRencontres = new Stack<>();
                BrancheMAT brancheTemp = new BrancheMAT();
                boolean finished = false;

                Composante temporaire = this.getNoeudMATS().get(i).getComposanteNoeudMAT();
                int row = temporaire.getRow();
                int col = temporaire.getCol();

                brancheTemp.getNoeudMATSAdjacents().add(this.getNoeudMATS().get(i));
                String dir = this.getNoeudMATS().get(i).getDirections()[j];


                if (!this.getNoeudMATS().get(i).getDirectionsAnalysees()[j]){

                    this.getNoeudMATS().get(i).getDirectionsAnalysees()[j] = true;

                    while (!finished){

                        switch (dir) {
                            case "N":
                                switch (temporaire.getTabNomVariante()[temporaire.getDirection()]) {
                                    case "NS":
                                        dir = "N";
                                        break;
                                    case "SN":
                                        dir = "N";
                                        break;
                                    case "SE":
                                        dir = "E";
                                        break;
                                    case "SO":
                                        dir = "O";
                                        break;
                                    case "NSE":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[1] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    case "NSO":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[1] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    case "SOE":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[0] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    case "NSEO":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[1] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    default:
                                        finished = true;
                                        break;
                                }
                                break;
                            case "E":
                                switch (temporaire.getTabNomVariante()[temporaire.getDirection()]) {
                                    case "NO":
                                        dir = "N";
                                        break;
                                    case "SO":
                                        dir = "S";
                                        break;
                                    case "OE":
                                        dir = "E";
                                        break;
                                    case "EO":
                                        dir = "E";
                                        break;
                                    case "NSO":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[2] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    case "SOE":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[1] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    case "NEO":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[2] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    case "NSEO":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[3] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    default:
                                        finished = true;
                                        break;
                                }
                                break;
                            case "S":
                                switch (temporaire.getTabNomVariante()[temporaire.getDirection()]) {
                                    case "NS":
                                        dir = "S";
                                        break;
                                    case "SN":
                                        dir = "S";
                                        break;
                                    case "NE":
                                        dir = "E";
                                        break;
                                    case "NO":
                                        dir = "O";
                                        break;
                                    case "NSE":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[0] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    case "NSO":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[0] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    case "NEO":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[0] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    case "NSEO":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[0] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    default:
                                        finished = true;
                                        break;
                                }
                                break;
                            case "O":
                                switch (temporaire.getTabNomVariante()[temporaire.getDirection()]) {
                                    case "NE":
                                        dir = "N";
                                        break;
                                    case "SE":
                                        dir = "S";
                                        break;
                                    case "OE":
                                        dir = "O";
                                        break;
                                    case "EO":
                                        dir = "O";
                                        break;
                                    case "NSE":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[2] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    case "SOE":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[2] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    case "NEO":
                                        if (((Fil)temporaire).isNoeudMAT()){
                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[1] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    case "NSEO":
                                        if (((Fil)temporaire).isNoeudMAT()){

                                        }else {
                                            noeudTemporaire = new Noeud(temporaire);
                                            noeudsRencontres.add(noeudTemporaire);
                                            noeudTemporaire.getDirectionsAnalysees()[2] = true;
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (!noeudTemporaire.getDirectionsAnalysees()[k]){
                                                    dir = noeudTemporaire.getDirections()[k];
                                                }
                                            }
                                            for (int k=0;k<noeudTemporaire.getDirections().length; k++){
                                                if (noeudTemporaire.getDirections()[k] == dir){
                                                    noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                                }
                                            }
                                            ((Fil) temporaire).setNoeudMAT(true);
                                        }
                                        break;
                                    default:
                                        finished = true;
                                        break;
                                }
                                break;
                        }

                            switch (dir){
                                case "N":
                                    row--;
                                    break;
                                case "S":
                                    row++;
                                    break;
                                case "E":
                                    col++;
                                    break;
                                case "O":
                                    col--;
                                    break;
                            }

                            if (!finished){
                                temporaire = (Composante) SandboxController.getNodeFromGridPane(SandboxController.gridPaneSandBox, col, row);
                            }

                        if (temporaire instanceof Source || temporaire instanceof Resisteur || temporaire instanceof ComposanteVide || temporaire instanceof MiseAterre || finished){
                            finished = true;
                            NoeudMAT noeudMATemp = null;

                            if (temporaire instanceof MiseAterre){
                                brancheTemp.getComposantes().add(temporaire);
                            }

                            for (NoeudMAT noeudMAT: this.getNoeudMATS()){
                                if (noeudMAT.getComposanteNoeudMAT() == temporaire){
                                    noeudMATemp = noeudMAT;
                                }
                            }

                            if (temporaire instanceof Source){

                                switch (dir){
                                    case "S":
                                        switch (noeudMATemp.getDirection()){
                                            case "NS":
                                                noeudMATemp.getTabDirections()[0].getBranches().add(brancheTemp);
                                                break;
                                            case "SN":
                                                noeudMATemp.getTabDirections()[1].getBranches().add(brancheTemp);
                                                break;
                                        }
                                        break;
                                    case "E":
                                        switch (noeudMATemp.getDirection()){
                                            case "OE":
                                                noeudMATemp.getTabDirections()[0].getBranches().add(brancheTemp);
                                                break;
                                            case "EO":
                                                noeudMATemp.getTabDirections()[1].getBranches().add(brancheTemp);
                                                break;
                                        }
                                        break;
                                    case "O":
                                        switch (noeudMATemp.getDirection()){
                                            case "OE":
                                                noeudMATemp.getTabDirections()[1].getBranches().add(brancheTemp);
                                                break;
                                            case "EO":
                                                noeudMATemp.getTabDirections()[0].getBranches().add(brancheTemp);
                                                break;
                                        }
                                        break;
                                    case "N":
                                        switch (noeudMATemp.getDirection()){
                                            case "NS":
                                                noeudMATemp.getTabDirections()[1].getBranches().add(brancheTemp);
                                                break;
                                            case "SN":
                                                noeudMATemp.getTabDirections()[0].getBranches().add(brancheTemp);
                                                break;
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }

                            else if (temporaire instanceof Resisteur){
                                switch (dir){
                                    case "S":
                                        switch (noeudMATemp.getComposanteNoeudMAT().getSensCourant()){
                                            case "↓":
                                                noeudMATemp.getTabDirections()[0].getBranches().add(brancheTemp);
                                                break;
                                            case "↑":
                                                noeudMATemp.getTabDirections()[1].getBranches().add(brancheTemp);
                                                break;
                                            default:
                                                break;
                                        }
                                        break;
                                    case "E":
                                        switch (noeudMATemp.getComposanteNoeudMAT().getSensCourant()){
                                            case "→":
                                                noeudMATemp.getTabDirections()[0].getBranches().add(brancheTemp);
                                                break;
                                            case "←":
                                                noeudMATemp.getTabDirections()[1].getBranches().add(brancheTemp);
                                                break;
                                            default:
                                                break;
                                        }
                                        break;
                                    case "O":
                                        switch (noeudMATemp.getComposanteNoeudMAT().getSensCourant()){
                                            case "→":
                                                noeudMATemp.getTabDirections()[1].getBranches().add(brancheTemp);
                                                break;
                                            case "←":
                                                noeudMATemp.getTabDirections()[0].getBranches().add(brancheTemp);
                                                break;
                                            default:
                                                break;
                                        }
                                        break;
                                    case "N":
                                        switch (noeudMATemp.getComposanteNoeudMAT().getSensCourant()){
                                            case "↓":
                                                noeudMATemp.getTabDirections()[1].getBranches().add(brancheTemp);
                                                break;
                                            case "↑":
                                                noeudMATemp.getTabDirections()[0].getBranches().add(brancheTemp);
                                                break;
                                            default:
                                                break;
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }

                        } else {
                            brancheTemp.getComposantes().add(temporaire);
                        }

                        if (finished && noeudTemporaire != null){

                            boolean noeudFini = true;
                            while (noeudFini) {

                                for (int k = 0; k < noeudTemporaire.getDirectionsAnalysees().length; k++) {
                                    if (!noeudTemporaire.getDirectionsAnalysees()[k]) {
                                        noeudFini = false;
                                        temporaire = noeudTemporaire.getComposante();
                                        dir = noeudTemporaire.getDirections()[k];
                                        noeudTemporaire.getDirectionsAnalysees()[k] = true;
                                        col = temporaire.getCol();
                                        row = temporaire.getRow();
                                        finished = false;
                                    }
                                }

                                if (noeudFini && noeudsRencontres.size() != 0) {
                                    noeudTemporaire = noeudsRencontres.pop();
                                }
                                else if (noeudFini && noeudsRencontres.size() == 0){
                                    finished = true;
                                    noeudFini = false;
                                }
                            }
                        }
                    }
                    this.getBranchesMAT().add(brancheTemp);
                }
            }
        }
        System.out.println("MAT Fini");
    }

    public void creerNoeudsMAT(Circuit circuit){
        for (Resisteur resisteur: circuit.getResisteurs()){
            noeudMATS.add(new NoeudMAT(resisteur));
        }

        for (Source source: circuit.getSources()){
            noeudMATS.add(new NoeudMAT(source));
        }
    }

    public ArrayList<BrancheMAT> getBranchesMAT() {
        return branchesMAT;
    }

    public void setBranchesMAT(ArrayList<BrancheMAT> branchesMAT) {
        this.branchesMAT = branchesMAT;
    }

    public ArrayList<NoeudMAT> getNoeudMATS() {
        return noeudMATS;
    }

    public void setNoeudMATS(ArrayList<NoeudMAT> noeudMATS) {
        this.noeudMATS = noeudMATS;
    }

    public BrancheMAT getBrancheInitiale() {
        return brancheInitiale;
    }

    public void setBrancheInitiale(BrancheMAT brancheInitiale) {
        this.brancheInitiale = brancheInitiale;
    }
}