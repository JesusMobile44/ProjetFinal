package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Main extends Application {

    public static int numeroMode;

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
        menu.getStylesheets().add("modena_dark.css"); //Dark Theme: https://github.com/joffrey-bion/javafx-themes
        sandBox.getStylesheets().add("modena_dark.css"); //Dark Theme: https://github.com/joffrey-bion/javafx-themes
        aventure.getStylesheets().add("modena_dark.css"); //Dark Theme: https://github.com/joffrey-bion/javafx-themes
        guide.getStylesheets().add("modena_dark.css"); //Dark Theme: https://github.com/joffrey-bion/javafx-themes
        getScenes()[0] = menu;
        getScenes()[1] = sandBox;
        getScenes()[2] = aventure;
        getScenes()[3] = guide;

        numeroMode = 0;

        primaryStage = getStage();
        primaryStage.setTitle("Minecraft Redstone");

        primaryStage.setScene(getScenes()[0]);


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