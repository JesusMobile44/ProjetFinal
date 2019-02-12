package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Scene[] scenes = new Scene[4];

    private static Stage stage= new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage)throws Exception{
        Scene menu = new Scene(FXMLLoader.load(getClass().getResource("../vues/menuVue.fxml")));
        Scene sandBox = new Scene(FXMLLoader.load(getClass().getResource("../vues/sandboxVue.fxml")));
        Scene aventure = new Scene(FXMLLoader.load(getClass().getResource("../vues/aventureVue.fxml")));
        Scene guide = new Scene(FXMLLoader.load(getClass().getResource("../vues/guideVue.fxml")));
        getScenes()[0] = menu;
        getScenes()[1] = sandBox;
        getScenes()[2] = aventure;
        getScenes()[3] = guide;

        primaryStage = getStage();    //fuckmyass==true; xd
        primaryStage.setTitle("Minecraft Redstone");

        primaryStage.setScene(getScenes()[0]);

        primaryStage.setMaximized(true);
        primaryStage.show();
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
}
