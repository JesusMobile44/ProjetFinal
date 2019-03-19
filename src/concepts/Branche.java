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
    private float intensité;

    public ArrayList<Composante> getComposantesBranche() {
        return composantesBranche;
    }

    public void setComposantesBranche(ArrayList<Composante> composantesBranche) {
        this.composantesBranche = composantesBranche;
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

    public float getIntensité() {
        return intensité;
    }

    public void setIntensité(float intensité) {
        this.intensité = intensité;
    }

    public ArrayList<Noeud> getNoeudsAdjacents() {
        return noeudsAdjacents;
    }

    public void setNoeudsAdjacents(ArrayList<Noeud> noeudsAdjacents) {
        this.noeudsAdjacents = noeudsAdjacents;
    }
}
