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

    private static Scene[] scenes = new Scene[4];

    private static Stage stage= new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    private static ImagesContainer imagesContainer = new ImagesContainer();

    public void start(Stage primaryStage)throws Exception{

        Scene menu = new Scene(FXMLLoader.load(getClass().getResource("../vues/menuVue.fxml")));
        Scene sandBox = new Scene(FXMLLoader.load(getClass().getResource("../vues/sandboxVue.fxml")));
        Scene aventure = new Scene(FXMLLoader.load(getClass().getResource("../vues/aventureVue.fxml")));
        Scene guide = new Scene(FXMLLoader.load(getClass().getResource("../vues/guideVue.fxml")));
        menu.getStylesheets().add("modena_dark.css"); //Dark Theme: https://github.com/joffrey-bion/javafx-themes
        sandBox.getStylesheets().add("modena_dark.css"); //Dark Theme: https://github.com/joffrey-bion/javafx-themes
        aventure.getStylesheets().add("modena_dark.css"); //Dark Theme: https://github.com/joffrey-bion/javafx-themes
        guide.getStylesheets().add("modena_dark.css"); //Dark Theme: https://github.com/joffrey-bion/javafx-themes
        getScenes()[0] = menu;
        getScenes()[1] = sandBox;
        getScenes()[2] = aventure;
        getScenes()[3] = guide;

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
        primaryStage.setTitle("Minecraft Zéphirr");

        primaryStage.setScene(getScenes()[0]);

        primaryStage.getIcons().add(new Image("autre/images/zephyr.jpg"));

        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void changerDeMode(int mode){
        stage.setScene(Main.getScenes()[mode]);
        stage.setResizable(true);

        numeroMode = mode;

        stage.hide();
        stage.show();
    }


    private static Scene[] getScenes() {
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