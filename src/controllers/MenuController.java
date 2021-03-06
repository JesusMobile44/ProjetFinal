package controllers;

import autre.ImagesContainer;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import main.Main;

public class MenuController {
    @FXML
    ImageView sandbox, guide, zephImage;

    @FXML
    Label zephLabel, nomsLabel, sandboxLabel, guideLabel;

    @FXML
    Button button;

    public ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
        sandbox.fitWidthProperty().bind(Main.getStage().widthProperty());
        sandbox.setFitHeight(sandbox.getFitWidth() / 5);
        guide.fitWidthProperty().bind(Main.getStage().widthProperty());
        guide.setFitHeight(guide.getFitWidth() / 5);
    };

    public void initialize(){
        bind();
        transitions(sandbox, 3.5);
        transitions(guide, 3.5);
        transitions(zephImage, 2.5);
        transitions(zephLabel, 3.5);
        transitions(nomsLabel, 5);
        transitions(sandboxLabel, 3.5);
        transitions(guideLabel, 3.5);
        transitions(button, 5);
    }

    public void transitions(Node node, double dur){
        FadeTransition fade = new FadeTransition(Duration.seconds(dur), node);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public void bind() {
        sandbox.setPreserveRatio(true);
        sandbox.fitWidthProperty().bind(Main.getStage().widthProperty());
        sandbox.setFitHeight(sandbox.getFitWidth() / 6);
        guide.setPreserveRatio(true);
        guide.fitWidthProperty().bind(Main.getStage().widthProperty());
        guide.setFitHeight(guide.getFitWidth() / 6);

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
    }

    public void setSandbox(){
        unbind();
        Main.changerDeMode(1);
    }
    public void mouseEnterSandbox(){sandbox.setOpacity(0.5);}
    public void mouseExitSandbox(){sandbox.setOpacity(1);}

    public void setGuide(){
        unbind();
        Main.getStage().maxHeightProperty().bind(Main.getStage().widthProperty().multiply(1500/1920));
        Main.getStage().minHeightProperty().bind(Main.getStage().widthProperty().multiply(1920/1080));
        Main.changerDeMode(2);
    }
    public void mouseEnterGuide(){guide.setOpacity(0.5);}
    public void mouseExitGuide(){guide.setOpacity(1);}

    public void setExit(){ System.exit(0);}
}
