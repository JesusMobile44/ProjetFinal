package concepts.miseATerre;


import java.util.ArrayList;

public class CircuitMAT {

    private ArrayList<BrancheMAT> branchesMAT = new ArrayList<>();
    private ArrayList<NoeudMAT> noeudMATS = new ArrayList<>();
    private BrancheMAT brancheInitiale;

    public CircuitMAT() {
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
