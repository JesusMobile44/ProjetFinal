package main;

import autre.ImagesContainer;
import controllers.SandboxController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class Main extends Application {

    public static int numeroMode;

    private static Scene[] scenes = new Scene[3];

    private static Stage stage= new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    private static ImagesContainer imagesContainer = new ImagesContainer();

    public void start(Stage primaryStage)throws Exception{
        getScenes()[0] = new Scene(FXMLLoader.load(getClass().getResource("../vues/menuVue.fxml")));
        getScenes()[1] = new Scene(FXMLLoader.load(getClass().getResource("../vues/sandboxVue.fxml")));
        getScenes()[2] = new Scene(FXMLLoader.load(getClass().getResource("../vues/guideVue.fxml")));

        getScenes()[0].getStylesheets().add("modena_dark.css"); //Dark Theme: https://github.com/joffrey-bion/javafx-themes
        getScenes()[1].getStylesheets().add("modena_dark.css"); //Dark Theme: https://github.com/joffrey-bion/javafx-themes
        getScenes()[2].getStylesheets().add("modena_dark.css"); //Dark Theme: https://github.com/joffrey-bion/javafx-themes

        getScenes()[1].setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.C)
                SandboxController.cPressed = true;
            if (event.getCode() == KeyCode.X)
                SandboxController.xPressed = true;
        });

        getScenes()[1].setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.C)
                SandboxController.cPressed = false;
            if (event.getCode() == KeyCode.X)
                SandboxController.xPressed = false;
        });

        numeroMode = 0;

        primaryStage = getStage();
        primaryStage.setTitle("Zéphirr");

        primaryStage.setScene(getScenes()[0]);
        primaryStage.setMaximized(true);
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(650);
        primaryStage.getIcons().add(new Image("autre/images/zephyr.jpg"));
        primaryStage.show();
    }

    public static void changerDeMode(int mode){
        stage.setScene(Main.getScenes()[mode]);
        stage.setResizable(true);

        numeroMode = mode;

        stage.hide();
        stage.show();
    }


    public static Scene[] getScenes() {
        return scenes;
    }

    public static void setScenes(Scene[] scenes) {
        Main.scenes = scenes;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setPrimaryStage(Stage stage) {
        Main.stage = stage;
    }

    public static ImagesContainer getImagesContainer() {
        return imagesContainer;
    }

    public static void setImagesContainer(ImagesContainer imagesContainer) {
        Main.imagesContainer = imagesContainer;
    }
}