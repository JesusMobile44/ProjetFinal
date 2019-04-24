package controllers;

import guide.Page;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import main.Main;

import java.util.ArrayList;

public class GuideController {
    ArrayList<Page> pages = new ArrayList();
    int page;
    @FXML
    Label nom, description;
    @FXML
    Button pageL;
    @FXML
    ImageView image1, image2, formule;

    public ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
        image1.fitWidthProperty().bind(Main.getStage().widthProperty());
        image1.setFitHeight(image1.getFitWidth() / 6);
        image2.fitWidthProperty().bind(Main.getStage().widthProperty());
        image2.setFitHeight(image2.getFitWidth() / 6);
        formule.fitWidthProperty().bind(Main.getStage().widthProperty());
        formule.setFitHeight(formule.getFitWidth() / 12);
        nom.setFont(new Font(image1.getFitWidth() / 24));
        description.setFont(new Font(image1.getFitWidth() / 64));
        description.setMaxSize(Main.getStage().getWidth() / 1.5, image1.getFitWidth());
        description.setPrefSize(Main.getStage().getWidth() / 1.5, image1.getFitWidth() / 10);
    };

    public void initialize() {
        bind();
        pages.add(new Page("Ampèremètre", "Appareil servant à mesurer le courant dans une branche de circuit;\n" +
                "il doit être branché en série dans la branche en question;\n" +
                "afin de ne pas influencer le circuit, sa résistance doit être très petite."));
        pages.add(new Page("Ohmmètre", "Appareil servant à mesurer la résistance d'un résisteur ou la résistance équivalente de plusieur résisteur;\n" +
                "il comporte une source interne d'électromotance et doit être branché seul aux résisteur à mesurer"));
        pages.add(new Page("Voltmètre","Appareil servant à mesurer la différence de potentiel entre deux points d'un circuit;\n" +
                "il doit être brancher en parallèle entre les deux points en question;\n" +
                "afin de ne pas influencer le circuit sa résistance doit être très grande."));
        pages.add(new Page("Ampoule","Composante électrique composé d'un filament métallique enfermé dans une ampoule de verre.\n" +
                "Lorsque l'électricité passe dans le filament électrique, il est porté à incandescence."));
        pages.add(new Page("Condensateur","Dispositif servant à emmagasiner des charges électriques formé de deux armatures conductrices séparées par un isolant;\n" +
                "lorsqu'on dit qu'un condensateur porte une charge q, cela veut dire qu'une des armature\n" +
                "porte une charge positive q et l'autre une charge négative -q"));
        pages.add(new Page("Diode","Un diode est un dipôle qui ne laisse passer le courant que dans un sens.\n" +
                "C'est un dipôle non-linéaire et polarisé (ou non-symétrique).\n" +
                "Le sens de branchement d'une diode a donc une importance sur le fonctionnement du circuit électronique dans lequel elle est placée."));
        pages.add(new Page("Fil","Composante servant au transport de l'électricité, afin de transmettre de l'énergie et de l'information;\n" +
                "afin de ne pas influencer le circuit sa résistance doit être très faible."));
        pages.add(new Page("Fusible","Un coupe-circuit à fusible est un organe de sécurité dont le rôle est d'ouvrir un circuit électrique lorsque\n" +
                "le courant électrique dans celui-ci atteint une valeur d'intensité donnée pendant un certain temps."));
        pages.add(new Page("Haut-Parleur","Un haut-parleur est un transducteur électroacoustique destiné à produire des sons à partir d'un signal électrique.\n" +
                "Il est possible de sélectionner la musique en faisant clic-droit sur la composante et en sélectionnant Modifier la musique"));
        pages.add(new Page("Interrupteur","Un interrupteur est un organe permettant d'interrompre ou d'autoriser le passage d'un flux électrique.\n" +
                "Il est possible d'alterner entre l'état Ouvert et Fermer en faisant un clique droit sur la composante et en sélectionnant Switch"));
        pages.add(new Page("Mise à terre","Lien conducteur entre un point du circuit et la terre que l'on considère comme un conducteur idéal;\n" +
                "par définition, le potentiel électrique d'un point mis à terre est de zéro"));
        pages.add(new Page("Résisteur","Une résistance, ou resistor, est un composant électronique ou électrique dont la principale caractéristique est d'opposer\n" +
                "une plus ou moins grande résistance (mesurée en ohms) à la circulation du courant électrique.\n" +
                "Un résisteur est nécéssaire au fonctionnement d'un circuit."));
        pages.add(new Page("Switch","Une Switch est un organe permettant le passage d'autoriser le passage d'un flux électrique\n" +
                "dans une de deux direction données. Il est possible d'alterner entre les deux directions en faisant\n" +
                "un clique droit sur la composante et en sélectionnant Switch"));


        pages.add(new Page("(Branchés en) Série","Éléments de circuit placés sur la même branche;\n" +
                "le même courant traverse chaque éléments"));
        pages.add(new Page("(Branchés en) Parallèle","Éléments de circuit placés sur des branches parallèles;\n" +
                "les branches partent du même noeud et aboutissent au même noeud."));
        pages.add(new Page("Loi des mailles de Kirchhoff","Sur une maille, la somme des variations de potentiel est égale à 0\n" +
                "(les hausses et les baisses de potentiel sont équivalentes);\n" +
                "conséquence du fait que la force électrique est conservative."));
        pages.add(new Page("Loi des noeuds de Kirchhoff","Tout le courant qui pénètre dans un noeud doit en ressortir;\n" +
                "conséquence du principe de conservation de la charge électrique;"));
        pages.add(new Page("Résistance équivalente","Résistance d'un résisteur qui pourrait remplacer une association de résisteurs\n" +
                "sans modifier le courant débiter par la source"));
        pages.add(new Page("Tension","Dans le contexte de l'étude des circuits électriques, la tension aux bornes d'un élément de circuit\n" +
                "correspond à la différence de poteniel entre ses bornes."));
        pages.add(new Page("Intensité (courant électrique)","le courant à un endroit donné dans un circuit est la quantité de charges qui passe par cet endroit,\n" +
                "divisée par le temps requis; par convention le courant est dans le sens du déplacement des charges positives\n" +
                "et dans le sens contraire des charges négatives."));
        pages.add(new Page("Loi d'Ohm","Relation de proportionnalité entre la tension appliquée aux bornes d'un résisteur et le courant qui le traverse;\n" +
                "s'applique uniquement aux résisteur dont la résistance est constante."));
        page = 1;
        loadPage(page);
    }

    public void next() {
        page++;
        if (page == pages.size() + 1)
            page = 1;
        loadPage(page);
    }

    public void previous() {
        page--;
        if (page == 0)
            page = pages.size();
        loadPage(page);
    }

    public void loadPage(int page) {
        nom.setText(pages.get(page - 1).getNom());
        description.setText(pages.get(page - 1).getDescription());
        pageL.setText("Page " + page);
        formule.setImage(pages.get(page - 1).getFormule());
        image1.setImage(pages.get(page - 1).getImage1());
        image2.setImage(pages.get(page - 1).getImage2());
    }

    public void choisirPage() {
        ChoiceDialog<String> alerte = new ChoiceDialog<String>("Ampèremètre","Ohmmètre","Voltmètre", "Ampoule", "Condensateur", "Diode", "Fil", "Fusible",
                "Haut-Parleur","Interruptueur", "Mise à terre", "Moteur", "Résisteur", "Source", "Switch",
                "(Branchés en) Série","(Branchés en) Parallèle","Loi des mailles de Kirchhoff","Loi des noeuds de Kirchhoff",
                "Résistance équivalente","Tension","Intensité","Loi d'Ohm");
        alerte.setTitle("Sélection de page");
        alerte.setHeaderText("Veuillez choisir la page");
        alerte.setContentText("Votre choix: ");
        try {
            switch (alerte.showAndWait().get().toUpperCase()) {
                case "AMPÈREMÈTRE":
                    page = 1;
                    break;
                case "OHMMÈTRE":
                    page = 2;
                    break;
                case "VOLTMÈTRE":
                    page = 3;
                    break;
                case "AMPOULE":
                    page = 4;
                    break;
                case "CONDENSATEUR":
                    page = 5;
                    break;
                case "DIODE":
                    page = 6;
                    break;
                case "FIL":
                    page = 7;
                    break;
                case "FUSIBLE":
                    page = 8;
                    break;
                case "HAUT-PARLEUR":
                    page = 9;
                    break;
                case "INTERRUPTEUR":
                    page = 10;
                    break;
                case "MISE À TERRE":
                    page = 11;
                    break;
                case "RÉSISTEUR":
                    page = 12;
                    break;
                case "SWITCH":
                    page = 13;
                    break;
                case "(BRANCHÉS EN) SÉRIE":
                    page = 14;
                    break;
                case "(BRANCHÉS EN) PARALLÈLE":
                    page = 15;
                    break;
                case "LOI DES MAILLES DE KIRCHHOFF":
                    page = 16;
                    break;
                case "LOI DES NOEUDS DE KIRCHHOFF":
                    page = 17;
                    break;
                case "RÉSISTANCE ÉQUIVALENTE":
                    page = 18;
                    break;
                case "TENSION":
                    page = 19;
                    break;
                case "INTENSITÉ":
                    page = 20;
                    break;
                case "LOI D'OHM":
                    page = 21;
                    break;
            }
            loadPage(page);
        } catch (Exception e) {
        }
    }

    public void bind() {
        image1.setPreserveRatio(true);
        image1.fitWidthProperty().bind(Main.getStage().widthProperty());
        image1.setFitHeight(image1.getFitWidth() / 6);
        image2.setPreserveRatio(true);
        image2.fitWidthProperty().bind(Main.getStage().widthProperty());
        image2.setFitHeight(image2.getFitWidth() / 6);
        formule.setPreserveRatio(true);
        formule.fitWidthProperty().bind(Main.getStage().widthProperty());
        formule.setFitHeight(formule.getFitWidth() / 9);

        description.setFont(new Font(image1.getFitWidth() / 48));
        description.setMaxSize(Main.getStage().getWidth() / 1.5, image1.getFitWidth() / 12);
        description.setPrefSize(Main.getStage().getWidth() / 1.5, image1.getFitWidth() / 12);

        Main.getStage().widthProperty().addListener(stageSizeListener);
        Main.getStage().heightProperty().addListener(stageSizeListener);

        Main.getStage().setMinHeight(650);
        Main.getStage().setMinWidth(650);
    }

    public void unbind() {
        Main.getStage().maxHeightProperty().unbind();
        Main.getStage().minHeightProperty().unbind();
        Main.getStage().setMinHeight(650);
        Main.getStage().setMinWidth(650);
        Main.getStage().maxHeightProperty().setValue(Double.MAX_VALUE);
    }

    public void setMenu() {
        unbind();
        Main.changerDeMode(0);
    }

    public void setSandbox() {
        unbind();
        Main.changerDeMode(1);
    }

}
