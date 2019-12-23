package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import menu.MenuController;
import menu.MenuPane;

public class Gui {

    private Stage primaryStage;

    private static Gui instance;

    private Gui() {
    }

    public static Gui createInstance(Stage primaryStage) {
        instance = new Gui();
        instance.primaryStage = primaryStage;
        return getInstance();
    }

    public static Gui getInstance() {
        if (instance == null) {
            throw new IllegalStateException("instance not created");
        }
        return instance;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
