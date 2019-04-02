package concepts;

import composantes.Composante;
import composantes.Resisteur;
import composantes.Source;
import controllers.SandboxController;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Circuit {

    private ArrayList<Composante> composantes = new ArrayList<>();
    private ArrayList<NouvelleMaille> mailles = new ArrayList<>();
    private ArrayList<Noeud> noeuds = new ArrayList<>();
    private ArrayList<Branche> branches = new ArrayList<>();
    private ArrayList<Resisteur> resisteurs = new ArrayList<>();
    private ArrayList<Source> sources = new ArrayList<>();
    private float resistanceEquivalente;
    private boolean enSerie;
    private boolean incomplet;

    public Circuit() {
        this.enSerie = true;
        this.resistanceEquivalente = 0;
        this.incomplet = true;
    }

    public void calculVariables(){
        if (this.enSerie){
            calculSerie();
        }

        else {
            calculParallele();
        }
    }

    public void calculParallele(){

    }

    public void calculSerie(){
        for (int i = 0; i<this.getResisteurs().size(); i++){
            resistanceEquivalente += this.getResisteurs().get(i).getResistance();
        }
        float tensionTotaleSource = 0;
        for (int i = 0; i<this.getSources().size(); i++){
            tensionTotaleSource += this.getSources().get(i).getVolt();
        }

        this.getBranches().get(0).setIntensité((tensionTotaleSource)/(resistanceEquivalente));

        for (int i = 0; i<this.getComposantes().size(); i++){
            this.getComposantes().get(i).setAmperage(this.getBranches().get(0).getIntensité());
            if (this.getComposantes().get(i).getNom().equals("RESISTEUR")){
                this.getComposantes().get(i).setVolt(this.getComposantes().get(i).getResistance()*this.getComposantes().get(i).getAmperage());
            }
        }

        reloadTooltip();

    }

    public void reloadTooltip(){
        for (int i = 0; i < this.getComposantes().size(); i++){
            Composante composante = this.getComposantes().get(i);
            composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nTension: " + SandboxController.df.format(composante.getVolt()) + "\nRésistance: " + SandboxController.df.format(composante.getResistance()));
        }
    }

    public ArrayList<NouvelleMaille> getMailles() {
        return mailles;
    }

    public void setMailles(ArrayList<NouvelleMaille> mailles) {
        this.mailles = mailles;
    }

    public ArrayList<Noeud> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(ArrayList<Noeud> noeuds) {
        this.noeuds = noeuds;
    }

    public ArrayList<Branche> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<Branche> branches) {
        this.branches = branches;
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

    public ArrayList<Composante> getComposantes() {
        return composantes;
    }

    public void setComposantes(ArrayList<Composante> composantes) {
        this.composantes = composantes;
    }

    public boolean isEnSerie() {
        return enSerie;
    }

    public void setEnSerie(boolean enSerie) {
        this.enSerie = enSerie;
    }

    public float getResistanceEquivalente() {
        return resistanceEquivalente;
    }

    public void setResistanceEquivalente(float resistanceEquivalente) {
        this.resistanceEquivalente = resistanceEquivalente;
    }

    public boolean isIncomplet() {
        return incomplet;
    }

    public void setIncomplet(boolean incomplet) {
        this.incomplet = incomplet;
    }
}
