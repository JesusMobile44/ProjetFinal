package concepts.miseATerre;

import composantes.Composante;

public class NoeudMAT {

    private Composante composanteNoeudMAT;
    private double differenceDePotentiel;

    public NoeudMAT(Composante composanteNoeudMAT) {
        this.composanteNoeudMAT = composanteNoeudMAT;
        this.differenceDePotentiel = composanteNoeudMAT.getVolt();
    }

    public Composante getComposanteNoeudMAT() {
        return composanteNoeudMAT;
    }

    public void setComposanteNoeudMAT(Composante composanteNoeudMAT) {
        this.composanteNoeudMAT = composanteNoeudMAT;
    }

    public double getDifferenceDePotentiel() {
        return differenceDePotentiel;
    }

    public void setDifferenceDePotentiel(double differenceDePotentiel) {
        this.differenceDePotentiel = differenceDePotentiel;
    }
}
