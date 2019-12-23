package com.gmail.mateuszmonas;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.gmail.mateuszmonas.menu.MenuController;
import com.gmail.mateuszmonas.menu.MenuPane;

public class Main extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        stage.setTitle("color com.gmail.mateuszmonas.game");
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setResizable(false);

        MenuPane root = new MenuPane(Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        MenuController menuController = new MenuController(root);


        menuController.start();
        stage.show();
    }


}
