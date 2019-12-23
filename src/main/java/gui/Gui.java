package gui;

import javafx.stage.Stage;

public class Gui {

    private static Gui instance;
    private Stage primaryStage;

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
