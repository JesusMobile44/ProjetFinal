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
        image1.setFitHeight(image2.getFitWidth() / 6);
        image2.fitWidthProperty().bind(Main.getStage().widthProperty());
        image2.setFitHeight(image2.getFitWidth() / 6);
        formule.fitWidthProperty().bind(Main.getStage().widthProperty());
        formule.setFitHeight(formule.getFitWidth() / 12);
        nom.setFont(new Font(image1.getFitWidth() / 24));
        description.setFont(new Font(image1.getFitWidth() / 52));
        description.setMaxSize(Main.getStage().getWidth() / 1.5, image1.getFitWidth());
        description.setPrefSize(Main.getStage().getWidth() / 1.5, image1.getFitWidth() / 10);
    };

    public void initialize() {
        bind();
        pages.add(new Page("Ampèremètre", "Ceci est une ampèremètre"));
        pages.add(new Page("Ampoule", "Ceci est une ampoule"));
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
        ChoiceDialog<String> alerte = new ChoiceDialog<String>("Ampèremètre", "Ampoule", "Condensateur", "Diode", "Fil", "Fusible", "Haut-Parleur", "Interruptueur", "Mise à terre", "Moteur", "Ohmmètre", "Résisteur", "Source", "Switch", "Voltmètre");
        alerte.setTitle("Sélection de page");
        alerte.setHeaderText("Veuillez choisir la page");
        alerte.setContentText("Votre choix: ");
        try {
            switch (alerte.showAndWait().get().toUpperCase()) {
                case "AMPÈREMÈTRE":
                    page = 1;
                    break;
                case "AMPOULE":
                    page = 2;
                    break;
                case "CONDENSATEUR":
                    break;
                case "DIODE":
                    break;
                case "FIL":
                    break;
                case "FUSIBLE":
                    break;
                case "HAUT-PARLEUR":
                    break;
                case "INTERRUPTEUR":
                    break;
                case "MISE À TERRE":
                    break;
                case "MOTEUR":
                    //rootScrollPane.getChildren().add(0, new Fil());
                    break;
                case "OHMÈTRE":
                    break;
                case "RESISTEUR":
                    break;
                case "SWITCH":
                    break;
                case "VOLTMÈTRE":
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
    }

    public void unbind() {
        Main.getStage().maxHeightProperty().unbind();
        Main.getStage().minHeightProperty().unbind();
        Main.getStage().minHeightProperty().setValue(100);
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
