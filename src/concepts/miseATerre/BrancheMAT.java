package concepts.miseATerre;

import composantes.Composante;
import concepts.Noeud;

import java.util.ArrayList;

public class BrancheMAT {

    private double potentiel = 0;
    private ArrayList<Composante> composantes = new ArrayList<>();
    private ArrayList<NoeudMAT> noeudMATSAdjacents = new ArrayList<>();
    private ArrayList<Noeud> noeuds = new ArrayList<>();
    private boolean potentielDonne;

    public BrancheMAT() {
        potentielDonne = false;
    }

    public double getPotentiel() {
        return potentiel;
    }

    public void setPotentiel(double potentiel) {
        this.potentiel = potentiel;
    }

    public ArrayList<Composante> getComposantes() {
        return composantes;
    }

    public void setComposantes(ArrayList<Composante> composantes) {
        this.composantes = composantes;
    }

    public ArrayList<NoeudMAT> getNoeudMATSAdjacents() {
        return noeudMATSAdjacents;
    }

    public void setNoeudMATSAdjacents(ArrayList<NoeudMAT> noeudMATSAdjacents) {
        this.noeudMATSAdjacents = noeudMATSAdjacents;
    }

    public ArrayList<Noeud> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(ArrayList<Noeud> noeuds) {
        this.noeuds = noeuds;
    }

    public boolean isPotentielDonne() {
        return potentielDonne;
    }

    public void setPotentielDonne(boolean potentielDonne) {
        this.potentielDonne = potentielDonne;
    }
}
