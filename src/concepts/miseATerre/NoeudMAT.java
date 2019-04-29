package concepts.miseATerre;

import composantes.Composante;
import composantes.Source;

public class NoeudMAT {

    private BrancheMAT[] brancheMATSAdjacentes;
    private Composante composanteNoeudMAT;
    private double differenceDePotentiel;
    private String direction;
    private boolean[] directionsAnalysees;
    private String[] directions;
    private Direction[] tabDirections;

    public NoeudMAT(Composante composanteNoeudMAT) {
        this.composanteNoeudMAT = composanteNoeudMAT;
        this.differenceDePotentiel = composanteNoeudMAT.getVolt();
        this.direction = composanteNoeudMAT.getTabNomVariante()[composanteNoeudMAT.getDirection()];
        this.directionsAnalysees = new boolean[this.direction.length()];
        this.directions = new String[direction.length()];
        this.tabDirections = new Direction[2];
        for (int i=0; i<this.direction.length(); i++){
            this.directionsAnalysees[i] = false;
        }
        this.brancheMATSAdjacentes = new BrancheMAT[2];
        for (int i=0; i<this.direction.length(); i++){
            this.directions[i] = this.direction.substring(i,i+1);
        }

        if (composanteNoeudMAT instanceof Source){
            switch (composanteNoeudMAT.getSensCourant()){
                case "↓":
                    switch (direction){
                        case "NS":
                            this.tabDirections[0] = new Direction(-1);
                            this.tabDirections[1]= new Direction(1);
                            break;
                        case "SN":
                            this.tabDirections[0]= new Direction(1);
                            this.tabDirections[1]= new Direction(-1);
                            break;
                    }
                    break;
                case "→":
                    switch (direction){
                        case "OE":
                            this.tabDirections[0]= new Direction(-1);
                            this.tabDirections[1]= new Direction(1);
                            break;
                        case "EO":
                            this.tabDirections[0]= new Direction(1);
                            this.tabDirections[1]= new Direction(-1);
                            break;
                    }
                    break;
                case "←":
                    switch (direction){
                        case "OE":
                            this.tabDirections[0]= new Direction(1);
                            this.tabDirections[1]= new Direction(-1);
                            break;
                        case "EO":
                            this.tabDirections[0]= new Direction(-1);
                            this.tabDirections[1]= new Direction(1);
                            break;
                    }
                    break;
                case "↑":
                    switch (direction){
                        case "NS":
                            this.tabDirections[0]= new Direction(1);
                            this.tabDirections[1]= new Direction(-1);
                            break;
                        case "SN":
                            this.tabDirections[0]= new Direction(-1);
                            this.tabDirections[1]= new Direction(1);
                            break;
                    }
                    break;
                default:
                    this.tabDirections[0]= new Direction(1);
                    this.tabDirections[1]= new Direction(1);
                        break;
            }
        }
        else {
            this.tabDirections[0]= new Direction(1);
            this.tabDirections[1]= new Direction(-1);
        }
    }

    public Composante getComposanteNoeudMAT() {
        return composanteNoeudMAT;
    }

    public void setComposanteNoeudMAT(Composante composanteNoeudMAT) {
        this.composanteNoeudMAT = composanteNoeudMAT;
    }

    public double getDifferenceDePotentiel() {
        return differenceDePotentiel;
    }

    public void setDifferenceDePotentiel(double differenceDePotentiel) {
        this.differenceDePotentiel = differenceDePotentiel;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean[] getDirectionsAnalysees() {
        return directionsAnalysees;
    }

    public void setDirectionsAnalysees(boolean[] directionsAnalysees) {
        this.directionsAnalysees = directionsAnalysees;
    }

    public BrancheMAT[] getBrancheMATSAdjacentes() {
        return brancheMATSAdjacentes;
    }

    public void setBrancheMATSAdjacentes(BrancheMAT[] brancheMATSAdjacentes) {
        this.brancheMATSAdjacentes = brancheMATSAdjacentes;
    }

    public String[] getDirections() {
        return directions;
    }

    public void setDirections(String[] directions) {
        this.directions = directions;
    }

    public Direction[] getTabDirections() {
        return tabDirections;
    }

    public void setTabDirections(Direction[] tabDirections) {
        this.tabDirections = tabDirections;
    }
}
