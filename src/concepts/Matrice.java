package concepts;

public class Matrice {

    private int m, n;
    private double[][] matrice;

    public Matrice(int m, int n, double[][] matrice) {
        this.m = m;
        this.n = n;
        this.matrice = matrice;
    }

    public Matrice triangleSuperieur() {
        for (int i = 1; i < this.getM(); i++) {
            if (this.getMatrice()[i - 1][i - 1] == 0) {
                int found = 0;
                for (int j = i; j < this.getM(); j++) {
                    if (this.getMatrice()[j][i - 1] != 0 && found == 0)
                        found = j;
                }
                if (found == 0)
                    return this;
                else {
                    double[] temp = this.getMatrice()[i - 1];
                    this.getMatrice()[i - 1] = this.getMatrice()[found];
                    this.getMatrice()[found] = temp;
                }
            }
            for (int j = i; j < this.getM(); j++) {
                double multiplicateur = this.getMatrice()[j][i - 1] / this.getMatrice()[i - 1][i - 1];
                for (int k = i - 1; k < this.getN(); k++)
                    this.getMatrice()[j][k] = this.getMatrice()[j][k] - multiplicateur * this.getMatrice()[i - 1][k];
            }
        }
        return this;
    }

    public double[] calculPivot() {
        double[] pivot = new double[this.getM()];
        for (int i = 0; i < pivot.length; i++) {
            pivot[i] = 0;
        }
        for (int i = this.getM() - 1; i > -1; i--) {
            double temp = this.matrice[i][this.getN() - 1];
            for (int j = this.getN() - 2; j > i; j--)
                temp -= this.matrice[i][j] * pivot[j];
            pivot[i] = temp / this.getMatrice()[i][i];
        }
        return pivot;
    }

    private int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    private int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double[][] getMatrice() {
        return matrice;
    }

    public void setMatrice(double[][] matrice) {
        this.matrice = matrice;
    }

}
