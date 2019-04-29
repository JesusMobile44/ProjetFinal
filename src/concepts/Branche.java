package concepts;

import composantes.Composante;
import composantes.Diode;
import composantes.Resisteur;
import composantes.Source;

import java.util.ArrayList;

public class Branche {
    private ArrayList<Composante> composantesBranche = new ArrayList<>();
    private ArrayList<Resisteur> resisteurs = new ArrayList<>();
    private ArrayList<Source> sources = new ArrayList<>();
    private ArrayList<Noeud> noeudsAdjacents = new ArrayList<>();
    private ArrayList<Diode> diodes = new ArrayList<>();
    private double intensite;
    private double capacite = 0;
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

    public double getIntensite() {
        return intensite;
    }

    public void setIntensite(double intensite) {
        this.intensite = intensite;
    }

    public double getCapacite() {
        return capacite;
    }

    public void setCapacite(double capacite) {
        this.capacite = capacite;
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

    public ArrayList<Diode> getDiodes() {
        return diodes;
    }

    public void setDiodes(ArrayList<Diode> diodes) {
        this.diodes = diodes;
    }
}
