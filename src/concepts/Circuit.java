package concepts;

import composantes.Composante;
import composantes.Resisteur;
import composantes.Source;
import controllers.SandboxController;

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
        updateNoeudsDirectionnels();

        double[][] matrice = new double[this.getBranches().size() - (this.getNoeuds().size() - 1) +this.getNoeuds().size() - 1][this.getBranches().size()+1];
        Noeud[] noeuds = new Noeud[this.getNoeuds().size()-1];
        NouvelleMaille[] mailles = new NouvelleMaille[this.getBranches().size() - (this.getNoeuds().size() - 1)];

        for (int i=0; i<matrice.length; i++){
            for (int j=0; j<matrice[i].length; j++){
                matrice[i][j] = 0;
            }
        }

        for (int i=0; i<noeuds.length; i++){
            noeuds[i] = this.getNoeuds().get(i);
        }

        boolean maillesOK = false;
        int[] numeroMailles = new int[mailles.length];

        while (!maillesOK){

            maillesOK = true;
            boolean[] brancheFound = new boolean[this.getBranches().size()];
            for (int i=0; i<brancheFound.length; i++){
                brancheFound[i] = false;
            }

            int number = 0;

            for (int i=0; i<numeroMailles.length; i++){

                boolean sameNumber = true;
                while (sameNumber){
                    sameNumber=false;

                    number = (int)(Math.random()*this.getMailles().size());
                    for (int j=0; j<i; j++){
                        if (number == numeroMailles[j]){
                            sameNumber = true;
                        }
                    }
                }
                numeroMailles[i] = number;
            }

            for (int i=0; i<mailles.length; i++){
                mailles[i] = this.getMailles().get(numeroMailles[i]);
            }

            for (int i=0; i<mailles.length; i++){
                for (int j=0; j<mailles[i].getBranchesMaille().size(); j++){
                    for (int k=0; k<this.getBranches().size(); k++){
                        if (mailles[i].getBranchesMaille().get(j) == this.getBranches().get(k)){
                            brancheFound[k] = true;
                        }
                    }
                }
            }

            for (int i=0; i<brancheFound.length; i++){
                if (!brancheFound[i]){
                    maillesOK = false;
                }
            }
        }

        for (int i=0; i<noeuds.length; i++){
            for (int j=0; j<matrice[i].length-1; j++){
                for (int k=0; k<noeuds[i].getBranchesAdjacentes().size(); k++){
                    if (noeuds[i].getBranchesAdjacentes().get(k) == this.getBranches().get(j)){
                        if (noeuds[i].getBranchesAdjacentes().get(k).getNoeudDirectionnel() == noeuds[i]){
                            matrice[i][j] = 1;
                        }else {
                            matrice[i][j] = -1;
                        }
                    }
                }
            }
        }

        for (int i=noeuds.length; i<matrice.length; i++){
            NouvelleMaille mailleTempo = mailles[i - noeuds.length];

            for (int j=0; j<mailleTempo.getBranchesMaille().size(); j++){
                Branche brancheTempo = mailleTempo.getBranchesMaille().get(j);
                Noeud noeudTempo = mailleTempo.getNoeudsMaille().get(j);
                int posBranche = 0;

                for (int k=0; k<this.getBranches().size(); k++){
                    if (brancheTempo == this.getBranches().get(k)){
                        posBranche = k;
                    }
                }

                for (int k = 0; k<brancheTempo.getResisteurs().size(); k++){
                    if (brancheTempo.getNoeudDirectionnel() == noeudTempo){
                        matrice[i][posBranche] -= brancheTempo.getResisteurs().get(k).getResistance();
                    }else {
                        matrice[i][posBranche] += brancheTempo.getResisteurs().get(k).getResistance();
                    }
                }

                for (int k = 0; k<brancheTempo.getSources().size(); k++){
                    if (brancheTempo.getSources().get(k).getNoeudDirectionnel() == noeudTempo){
                        matrice[i][matrice[i].length-1] -= brancheTempo.getSources().get(k).getVolt();
                    }else {
                        matrice[i][matrice[i].length-1] += brancheTempo.getSources().get(k).getVolt();
                    }
                }
            }
        }

        Matrice matrice1 = new Matrice(matrice.length,matrice[0].length,matrice);
        matrice1.triangleSuperieur();
        matrice=matrice1.getMatrice();
        System.out.println(matrice);
        double[] pivot = matrice1.calculPivot();
        System.out.println(pivot);

        for (int i=0; i<this.getBranches().size(); i++){
            this.getBranches().get(i).setIntensité(pivot[i]);
            if (this.getBranches().get(i).getIntensité()<0){
                this.getBranches().get(i).setIntensité(this.getBranches().get(i).getIntensité()*-1);
                if (this.getBranches().get(i).getNoeudDirectionnel()==this.getBranches().get(i).getNoeudsAdjacents().get(0)){
                    this.getBranches().get(i).setNoeudDirectionnel(this.getBranches().get(i).getNoeudsAdjacents().get(1));
                }
                else {
                    this.getBranches().get(i).setNoeudDirectionnel(this.getBranches().get(i).getNoeudsAdjacents().get(0));
                }
            }
            for (int j=0; j<this.getBranches().get(i).getComposantesBranche().size(); j++){
                this.getBranches().get(i).getComposantesBranche().get(j).setAmperage(this.getBranches().get(i).getIntensité());
            }
        }

        for (Resisteur resiteur: this.getResisteurs()) {
            resiteur.setVolt(resiteur.getAmperage() * resiteur.getResistance());
        }

        reloadTooltip();
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


    public void updateNoeudsDirectionnels(){
        for (int i=0; i<this.getBranches().size(); i++){
            this.getBranches().get(i).setNoeudDirectionnel(this.getBranches().get(i).getNoeudsAdjacents().get(0));
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
