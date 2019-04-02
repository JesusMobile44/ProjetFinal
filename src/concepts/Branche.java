package concepts;

import composantes.Composante;
import composantes.Resisteur;
import composantes.Source;

import java.util.ArrayList;

public class Branche {
    private ArrayList<Composante> composantesBranche = new ArrayList<>();
    private ArrayList<Resisteur> resisteurs = new ArrayList<>();
    private ArrayList<Source> sources = new ArrayList<>();
    private ArrayList<Noeud> noeudsAdjacents = new ArrayList<>();
    private double intensité;
    private boolean intensiteTrouvee;
    private Noeud noeudDirectionnel;
    private double diviseur;

    public ArrayList<Composante> getComposantesBranche() {
        return composantesBranche;
    }

    public void setComposantesBranche(ArrayList<Composante> composantesBranche) {
        this.composantesBranche = composantesBranche;
        this.intensiteTrouvee = false;
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

    public double getIntensité() {
        return intensité;
    }

    public void setIntensité(double intensité) {
        this.intensité = intensité;
    }

    public ArrayList<Noeud> getNoeudsAdjacents() {
        return noeudsAdjacents;
    }

    public void setNoeudsAdjacents(ArrayList<Noeud> noeudsAdjacents) {
        this.noeudsAdjacents = noeudsAdjacents;
    }

    public boolean isIntensiteTrouvee() {
        return intensiteTrouvee;
    }

    public void setIntensiteTrouvee(boolean intensiteTrouvee) {
        this.intensiteTrouvee = intensiteTrouvee;
    }

    public Noeud getNoeudDirectionnel() {
        return noeudDirectionnel;
    }

    public void setNoeudDirectionnel(Noeud noeudDirectionnel) {
        this.noeudDirectionnel = noeudDirectionnel;
    }

    public double getDiviseur() {
        return diviseur;
    }

    public void setDiviseur(double diviseur) {
        this.diviseur = diviseur;
    }
}
