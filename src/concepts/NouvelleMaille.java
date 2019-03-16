package concepts;

import composantes.Composante;
import composantes.Resisteur;
import composantes.Source;

import java.util.ArrayList;

public class NouvelleMaille {
    private ArrayList<Composante> composantesMaille = new ArrayList<>();
    private ArrayList<Resisteur> resisteurs = new ArrayList<>();
    private ArrayList<Source> sources = new ArrayList<>();

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
}
