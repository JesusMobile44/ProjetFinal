package composantes;

import controllers.SandboxController;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Voltmetre extends Composante {

    private ArrayList<Composante> composantesAdjacentes = new ArrayList<>();

    public Voltmetre() {
        initialize();
    }

    public Voltmetre(ComposanteSave composanteSave, int i, int j) {
        initialize();
        direction = composanteSave.getDirection();
        resistance=composanteSave.getResistance();
        volt=composanteSave.getVolt();
        row = i;
        col = j;
        this.setImage(tabVariante[direction]);
        enPlace = true;
    }

    private void initialize(){
        tabNomVariante = new String[2];
        tabNomVariante[0] = "NS";
        tabNomVariante[1] = "OE";
        tabVariante = new Image[tabNomVariante.length];
        description = "Un Voltmètre est un appareil qui permet de mesurer la tension entre deux points.\n" +
                "Il doit être branché en parallèle.";
        tooltip = new Tooltip("Mesure la tension");
        tooltip.setStyle("-fx-font-size: 20");
        bindTooltip(this,tooltip);
        nom = "Voltmètre";
        initializeImage();
    }

    public void calculDiffDePotentiel(){

        boolean comp1Trouvee = false;
        boolean comp2Trouvee = false;
        Composante[] composantes = new Composante[2];

        if (this.direction == 0){
            Composante[] composantes1 = {(Composante) SandboxController.getNodeFromGridPane(SandboxController.gridPaneSandBox,this.getCol(), this.getRow()+1),
                    (Composante) SandboxController.getNodeFromGridPane(SandboxController.gridPaneSandBox,this.getCol(), this.getRow()-1)};

            String dir = composantes1[0].getTabNomVariante()[composantes1[0].getDirection()];
                for (int j=0; j<dir.length(); j++){
                    if (dir.charAt(j) == 'N'){
                        comp1Trouvee = true;
                    }
                }

            dir = composantes1[1].getTabNomVariante()[composantes1[1].getDirection()];
            for (int j=0; j<dir.length(); j++){
                if (dir.charAt(j) == 'S'){
                    comp2Trouvee = true;
                }
            }

            composantes = composantes1;
        }

        if (this.direction == 1){
            Composante[] composantes1 = {(Composante) SandboxController.getNodeFromGridPane(SandboxController.gridPaneSandBox,this.getCol()+1, this.getRow()),
                    (Composante) SandboxController.getNodeFromGridPane(SandboxController.gridPaneSandBox,this.getCol()-1, this.getRow())};

            String dir = composantes1[0].getTabNomVariante()[composantes1[0].getDirection()];
            for (int j=0; j<dir.length(); j++){
                if (dir.charAt(j) == 'O'){
                    comp1Trouvee = true;
                }
            }

            dir = composantes1[1].getTabNomVariante()[composantes1[1].getDirection()];
            for (int j=0; j<dir.length(); j++){
                if (dir.charAt(j) == 'E'){
                    comp2Trouvee = true;
                }
            }
            composantes = composantes1;
        }

        if (comp1Trouvee && comp2Trouvee
                && !(composantes[0] instanceof Source) && !(composantes[0] instanceof Resisteur)
                && !(composantes[1] instanceof Source) && !(composantes[1] instanceof Resisteur)){

            double potentiel1 = composantes[0].getVolt();
            double potentiel2 = composantes[1].getVolt();
            double diffPotentiel = potentiel1 - potentiel2;

            if (diffPotentiel<0){
                this.setVolt(diffPotentiel * -1);
            }else {
                this.setVolt(diffPotentiel);
            }
        }
    }

    public ArrayList<Composante> getComposantesAdjacentes() {
        return composantesAdjacentes;
    }

    public void setComposantesAdjacentes(ArrayList<Composante> composantesAdjacentes) {
        this.composantesAdjacentes = composantesAdjacentes;
    }
}
