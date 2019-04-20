package concepts;

import composantes.Composante;

import java.util.ArrayList;

public class Noeud {

    private String[] directions;
    private boolean[] directionsAnalysees;
    private boolean analyseComplete;
    private Composante composante;
    private ArrayList<Branche> branchesAdjacentes = new ArrayList<>();
    private boolean[] branchesAnalysees;

    public Noeud(Composante composante1) {
        this.composante = composante1;
        this.analyseComplete = false;
        this.directionsAnalysees = new boolean[this.composante.getTabNomVariante()[this.composante.getDirection()].length()];
        this.directions = new String[this.composante.getTabNomVariante()[this.composante.getDirection()].length()];
        this.branchesAnalysees = new boolean[directions.length];

        for (int i = 0; i<this.composante.getTabNomVariante()[this.composante.getDirection()].length(); i++){
            this.directions[i] = this.composante.getTabNomVariante()[this.composante.getDirection()].substring(i,i+1);
        }

        for (int i = 0; i<this.getDirections().length; i++){
            this.directionsAnalysees[i] = false;
            this.branchesAnalysees[i] = false;
        }
    }

    public String[] getDirections() {
        return directions;
    }

    public void setDirections(String[] directions) {
        this.directions = directions;
    }

    public boolean[] getDirectionsAnalysees() {
        return directionsAnalysees;
    }

    public void setDirectionsAnalysees(boolean[] directionsAnalysees) {
        this.directionsAnalysees = directionsAnalysees;
    }

    public boolean isAnalyseComplete() {
        return analyseComplete;
    }

    public void setAnalyseComplete(boolean analyseComplete) {
        this.analyseComplete = analyseComplete;
    }

    public Composante getComposanteNoeud() {
        return composante;
    }

    public void setComposanteNoeud(Composante composante) {
        this.composante = composante;
    }

    public Composante getComposante() {
        return composante;
    }

    public void setComposante(Composante composante) {
        this.composante = composante;
    }

    public ArrayList<Branche> getBranchesAdjacentes() {
        return branchesAdjacentes;
    }

    public void setBranchesAdjacentes(ArrayList<Branche> branchesAdjacentes) {
        this.branchesAdjacentes = branchesAdjacentes;
    }

    public boolean[] getBranchesAnalysees() {
        return branchesAnalysees;
    }

    public void setBranchesAnalysees(boolean[] branchesAnalysees) {
        this.branchesAnalysees = branchesAnalysees;
    }

    public void updateBranchesAnalysees(){
        this.branchesAnalysees = new boolean[branchesAdjacentes.size()];

        resetBranchesAnalysees();
    }

    public void resetBranchesAnalysees(){
        for (int i = 0; i<this.getBranchesAnalysees().length; i++){
            this.branchesAnalysees[i] = false;
            this.directionsAnalysees[i] = false;
        }
    }
}
