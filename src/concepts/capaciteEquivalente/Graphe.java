package concepts.capaciteEquivalente;

import concepts.Branche;
import concepts.Circuit;
import concepts.Noeud;

import java.util.ArrayList;

public class Graphe {
    ArrayList<Sommet> sommets;
    double capaciteEq = 0;
    boolean changement;

    public Graphe(Circuit circuit) {
        creerGraphe(circuit);
        boolean progress = true;
        while (sommets.get(0).getAretes().size() != 1 && progress) {
            changement = false;
            for (int i = 0; i < sommets.size(); i++) {
                verifierDoublon(i);
                verifierSommetsInutiles(i);
            }
            if (!changement)
                progress = false;
        }
        capaciteEq = sommets.get(0).getAretes().get(0).getCapacite();
        System.out.println();
    }

    void verifierDoublon(int i) {
        for (int j = 0; j < sommets.get(i).getSommets().size(); j++)
            for (int k = j + 1; k < sommets.get(i).getSommets().size(); k++)
                if (sommets.get(i).getSommets().get(j) == sommets.get(i).getSommets().get(k))
                    fusion(sommets.get(i), j, k);
    }

    void fusion(Sommet sommet, int j, int k) {
        double capacite = sommet.getAretes().get(j).getCapacite() + sommet.getAretes().get(k).getCapacite();
        Arete arete = new Arete(capacite);
        sommet.getSommets().get(j).getAretes().remove(sommet.getAretes().get(k));
        sommet.getSommets().get(j).getAretes().remove(sommet.getAretes().get(j));
        sommet.getSommets().get(j).getAretes().add(arete);
        sommet.getAretes().remove(k);
        sommet.getAretes().remove(j);
        sommet.getAretes().add(arete);
        sommet.getSommets().get(j).getSommets().remove(sommet);
        sommet.getSommets().add(sommet.getSommets().get(j));
        sommet.getSommets().remove(k);
        sommet.getSommets().remove(j);
        changement = true;
    }

    void verifierSommetsInutiles(int i) {
        if (sommets.get(i).getSommets().size() == 2)
            reduction(sommets.get(i));
    }

    void reduction(Sommet sommet) {
        double capacite;
        if (sommet.getAretes().get(0).getCapacite() != 0 && sommet.getAretes().get(1).getCapacite() != 0)
            capacite = 1 / (1 / sommet.getAretes().get(0).getCapacite() + 1 / sommet.getAretes().get(1).getCapacite());
        else if (sommet.getAretes().get(0).getCapacite() == 0)
            capacite = sommet.getAretes().get(0).getCapacite();
        else if (sommet.getAretes().get(1).getCapacite() == 0)
            capacite = sommet.getAretes().get(1).getCapacite();
        else
            capacite = 0;
        Arete arete = new Arete(capacite);
        sommet.getSommets().get(0).getSommets().add(sommet.getSommets().get(1));
        if (sommet.getSommets().get(0) != sommet.getSommets().get(1))
            sommet.getSommets().get(1).getSommets().add(sommet.getSommets().get(0));
        for (int i = sommet.getSommets().size() - 1; i > -1; i--) {
            sommet.getSommets().get(i).getAretes().remove(sommet.getAretes().get(i));
            sommet.getSommets().get(i).getSommets().remove(sommet);
        }
        sommet.getSommets().get(0).getAretes().add(arete);
        if (sommet.getSommets().get(0) != sommet.getSommets().get(1))
            sommet.getSommets().get(1).getAretes().add(arete);
        changement = true;
    }

    void creerGraphe(Circuit circuit) {
        ArrayList<Noeud> noeuds = circuit.getNoeuds();
        ArrayList<Branche> branches = circuit.getBranches();
        this.sommets = new ArrayList<>();
        ArrayList<Arete> aretesGraphe = new ArrayList<>();
        for (int i = 0; i < noeuds.size(); i++)
            this.sommets.add(new Sommet());
        for (int i = 0; i < branches.size(); i++)
            aretesGraphe.add(new Arete(branches.get(i).getCapacite()));
        for (int i = 0; i < noeuds.size(); i++)
            for (int j = 0; j < noeuds.get(i).getBranchesAdjacentes().size(); j++) {
                for (int k = 0; k < branches.size(); k++)
                    if (noeuds.get(i).getBranchesAdjacentes().get(j) == branches.get(k))
                        this.sommets.get(i).getAretes().add(aretesGraphe.get(k));
                for (int k = 0; k < noeuds.get(i).getBranchesAdjacentes().get(j).getNoeudsAdjacents().size(); k++)
                    for (int l = 0; l < noeuds.size(); l++)
                        if (noeuds.get(i).getBranchesAdjacentes().get(j).getNoeudsAdjacents().get(k) == noeuds.get(l) && noeuds.get(i).getBranchesAdjacentes().get(j).getNoeudsAdjacents().get(k) != noeuds.get(i))
                            this.sommets.get(i).getSommets().add(this.sommets.get(l));
            }
    }

    public ArrayList<Sommet> getSommets() {
        return sommets;
    }

    public void setSommets(ArrayList<Sommet> sommets) {
        this.sommets = sommets;
    }

    public double getCapaciteEq() {
        return capaciteEq;
    }

    public void setCapaciteEq(double capaciteEq) {
        this.capaciteEq = capaciteEq;
    }

    public boolean isChangement() {
        return changement;
    }

    public void setChangement(boolean changement) {
        this.changement = changement;
    }
}
