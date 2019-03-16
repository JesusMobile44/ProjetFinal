package concepts;

import composantes.Composante;

public class Noeud {

    private String[] directions;
    private boolean[] directionsAnalysees;
    private boolean analyseComplete;
    private Composante composante;

    public Noeud(String[] directions, boolean[] directionsAnalysees, Composante composante1) {
        this.directions = directions;
        this.directionsAnalysees = directionsAnalysees;
        this.analyseComplete = false;
        this.composante = composante1;
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
