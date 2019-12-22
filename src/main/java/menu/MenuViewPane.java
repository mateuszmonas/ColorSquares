package menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuViewPane extends VBox implements MenuViewContract.View {

    MenuViewContract.Controller controller;
    SettingsViewPane settingsViewPane;

    public MenuViewPane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);
        setAlignment(Pos.CENTER);

        settingsViewPane = new SettingsViewPane(prefWidth / 10);

        Button startButton = new Button("start");
        Button exitButton = new Button("exit");

        startButton.setOnMouseClicked(mouseEvent -> controller.startGame());
        exitButton.setOnMouseClicked(mouseEvent -> controller.exitApplication());


        getChildren().addAll(settingsViewPane, startButton, exitButton);
    }

    @Override
    public void setController(MenuViewContract.Controller controller) {
        this.controller = controller;
        settingsViewPane.setController(controller);
    }
}
