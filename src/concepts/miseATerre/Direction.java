package concepts.miseATerre;


import java.util.ArrayList;

public class Direction {

    private double multiplicateur;
    private ArrayList<BrancheMAT> branches = new ArrayList<>();
    private double valeurPotentiel;

    public Direction(double multiplicateur) {
        this.multiplicateur = multiplicateur;
    }

    public double getMultiplicateur() {
        return multiplicateur;
    }

    public void setMultiplicateur(double multiplicateur) {
        this.multiplicateur = multiplicateur;
    }

    public ArrayList<BrancheMAT> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<BrancheMAT> branches) {
        this.branches = branches;
    }

    public double getValeurPotentiel() {
        return valeurPotentiel;
    }

    public void setValeurPotentiel(double valeurPotentiel) {
        this.valeurPotentiel = valeurPotentiel;
    }
}
