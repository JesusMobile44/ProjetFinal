package concepts;

import composantes.Composante;

public class Noeud {

    private String[] directions;
    private boolean[] directionsAnalysees;
    private boolean analyseComplete;
    private Composante composante;

    public Noeud(Composante composante1) {
        this.composante = composante1;
        this.analyseComplete = false;
        this.directionsAnalysees = new boolean[this.composante.getTabNomVariante()[this.composante.getDirection()].length()];
        this.directions = new String[this.composante.getTabNomVariante()[this.composante.getDirection()].length()];

        for (int i = 0; i<this.composante.getTabNomVariante()[this.composante.getDirection()].length(); i++){
            this.directions[i] = this.composante.getTabNomVariante()[this.composante.getDirection()].substring(i,i+1);
        }

        for (int i = 0; i<this.getDirections().length; i++){
            this.directionsAnalysees[i] = false;
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
}
