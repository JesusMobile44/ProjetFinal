package concepts;

import composantes.Composante;
import composantes.Resisteur;
import controllers.SandboxController;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

class Maille {
    private ArrayList<Coordonnee> circuit;
    private ArrayList<Maille> mailles;
    private double resistanceEq;
    private double intensite;
    private double tension;

    public Maille(double intensite) {
        this.circuit = new ArrayList<>();
        this.mailles = new ArrayList<>();
        this.resistanceEq = 0;
        this.intensite = intensite;
        this.tension = 0;
    }

    public void calculer(GridPane gridPane) {
        calculResistanceEq(gridPane);
        calculVolt(gridPane);
        calculAmpere(gridPane);
        reloadTooltip(gridPane);
    }

    private void calculResistanceEq(GridPane gridPane) {
        //MAILLES SECONDAIRES AVANT MAILLE PRINCIPALE
        for (int i = 0; i < mailles.size(); i++)
            mailles.get(i).calculResistanceEq(gridPane);

        //TROUVER RESISTEUR
        ArrayList<Resisteur> resisteurs = new ArrayList<>();
        for (int i = 0; i < circuit.size(); i++)
            if (((Composante) SandboxController.getNodeFromGridPane(gridPane, circuit.get(i).getCol(), circuit.get(i).getRow())).getNom().toUpperCase().equals("RESISTEUR"))
                resisteurs.add(((Resisteur) SandboxController.getNodeFromGridPane(gridPane, circuit.get(i).getCol(), circuit.get(i).getRow())));

        //ADDITIONNER RESISTANCE
        for (int i = 0; i < resisteurs.size(); i++)
            resistanceEq += resisteurs.get(i).getResistance();

        //ADDITIONNER RESISTANCE EQ DES MAILLES
        double resistanceMailles = 0;
        for (int i = 0; i < mailles.size(); i++)
            resistanceMailles += 1 / mailles.get(i).getResistanceEq();
        if(resistanceMailles>0)
            resistanceEq += 1 / resistanceMailles;

        //CHANGER RESISTANCE DANS LES FILS
        for (int i = 0; i < circuit.size(); i++)
            if (!((Composante) SandboxController.getNodeFromGridPane(gridPane, circuit.get(i).getCol(), circuit.get(i).getRow())).getNom().toUpperCase().equals("RESISTEUR"))
                ((Composante) SandboxController.getNodeFromGridPane(gridPane, circuit.get(i).getCol(), circuit.get(i).getRow())).setResistance(resistanceEq);
        System.out.println(resistanceEq);
    }

    private void calculVolt(GridPane gridPane) {
        tension = resistanceEq * intensite;
        for (int i = 0; i < mailles.size(); i++)
            mailles.get(i).setTension(tension);
        for (int i = 0; i < circuit.size(); i++)
            ((Composante) SandboxController.getNodeFromGridPane(gridPane, circuit.get(i).getCol(), circuit.get(i).getRow())).setVolt(tension);
    }

    private void calculAmpere(GridPane gridPane) {
        intensite = tension / resistanceEq;
        for (int i = 0; i < mailles.size(); i++)
            mailles.get(i).calculAmpere(gridPane);
        for (int i = 0; i < circuit.size(); i++)
            ((Composante) SandboxController.getNodeFromGridPane(gridPane, circuit.get(i).getCol(), circuit.get(i).getRow())).setAmperage(intensite);
    }

    private void reloadTooltip(GridPane gridPane){
        for (int i = 0; i < circuit.size(); i++){
            Composante composante = ((Composante) SandboxController.getNodeFromGridPane(gridPane, circuit.get(i).getCol(), circuit.get(i).getRow()));
            composante.getTooltip().setText(composante.getNom() + " (" + composante.getCol() + "," + composante.getRow() + ")\nIntensité: " + SandboxController.df.format(composante.getAmperage()) + "\nTension: " + SandboxController.df.format(composante.getVolt()) + "\nRésistance: " + SandboxController.df.format(composante.getResistance()));
        }
    }

    public ArrayList<Coordonnee> getCircuit() {
        return circuit;
    }

    public void setCircuit(ArrayList<Coordonnee> circuit) {
        this.circuit = circuit;
    }

    public ArrayList<Maille> getMailles() {
        return mailles;
    }

    public void setMailles(ArrayList<Maille> mailles) {
        this.mailles = mailles;
    }

    private double getResistanceEq() {
        return resistanceEq;
    }

    public void setResistanceEq(double resistanceEq) {
        this.resistanceEq = resistanceEq;
    }

    public double getIntensite() {
        return intensite;
    }

    public void setIntensite(double intensite) {
        this.intensite = intensite;
    }

    public double getTension() {
        return tension;
    }

    private void setTension(double tension) {
        this.tension = tension;
    }
}
