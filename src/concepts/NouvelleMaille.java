package concepts;

import composantes.Composante;
import composantes.Resisteur;
import composantes.Source;

import java.util.ArrayList;

public class NouvelleMaille {
    private ArrayList<Composante> composantesMaille = new ArrayList<>();
    private ArrayList<Resisteur> resisteurs = new ArrayList<>();
    private ArrayList<Noeud> noeudsMaille = new ArrayList<>();
    private ArrayList<Branche> branchesMaille = new ArrayList<>();
    private ArrayList<Source> sources = new ArrayList<>();

    public NouvelleMaille(ArrayList<Composante> composantesMaille, ArrayList<Resisteur> resisteurs, ArrayList<Noeud> noeudsMaille, ArrayList<Branche> branchesMaille, ArrayList<Source> sources) {
        for (int i=0; i<composantesMaille.size();i++){
            this.composantesMaille.add(composantesMaille.get(i));
        }
        for (int i=0; i<resisteurs.size();i++){
            this.resisteurs.add(resisteurs.get(i));
        }
        for (int i=0; i<noeudsMaille.size();i++){
            this.noeudsMaille.add(noeudsMaille.get(i));
        }
        for (int i=0; i<branchesMaille.size();i++){
            this.branchesMaille.add(branchesMaille.get(i));
        }
        for (int i=0; i<sources.size();i++){
            this.sources.add(sources.get(i));
        }
    }

    public NouvelleMaille() {
    }

    public ArrayList<Composante> getComposantesMaille() {
        return composantesMaille;
    }

    public void setComposantesMaille(ArrayList<Composante> composantesMaille) {
        this.composantesMaille = composantesMaille;
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

    public ArrayList<Noeud> getNoeudsMaille() {
        return noeudsMaille;
    }

    public void setNoeudsMaille(ArrayList<Noeud> noeudsMaille) {
        this.noeudsMaille = noeudsMaille;
    }

    public ArrayList<Branche> getBranchesMaille() {
        return branchesMaille;
    }

    public void setBranchesMaille(ArrayList<Branche> branchesMaille) {
        this.branchesMaille = branchesMaille;
    }
}
