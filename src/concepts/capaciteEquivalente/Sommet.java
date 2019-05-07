package concepts.capaciteEquivalente;

import java.util.ArrayList;

public class Sommet {
    private ArrayList<Sommet> sommets;
    private ArrayList<Arete> aretes;

    public Sommet(){
        this.sommets = new ArrayList<>();
        this.aretes = new ArrayList<>();
    }

    public ArrayList<Sommet> getSommets() {
        return sommets;
    }

    public void setSommets(ArrayList<Sommet> sommets) {
        this.sommets = sommets;
    }

    public ArrayList<Arete> getAretes() {
        return aretes;
    }

    public void setAretes(ArrayList<Arete> aretes) {
        this.aretes = aretes;
    }
}
