package com.gmail.mateuszmonas.menu.view;

import com.gmail.mateuszmonas.menu.MenuContract;
import com.gmail.mateuszmonas.model.game.GameSettings;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuPane extends VBox implements MenuContract.View {

    MenuContract.Controller controller;
    SettingsDialog settingsDialog;

    public MenuPane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);
        setAlignment(Pos.CENTER);

        Button startButton = new Button("start");
        Button settingsButton = new Button("settings");
        Button exitButton = new Button("exit");
        settingsDialog = new SettingsDialog();

        startButton.setOnMouseClicked(mouseEvent -> controller.startGame());
        settingsButton.setOnMouseClicked(mouseEvent -> controller.settingsButtonClicked());
        exitButton.setOnMouseClicked(mouseEvent -> controller.exitApplication());

        getChildren().addAll(startButton, settingsButton, exitButton);
    }

    @Override
    public void setController(MenuContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void setSettings(GameSettings gameSettings) {
        settingsDialog.setSettings(gameSettings);
    }

    @Override
    public void showSettingsDialog() {
        settingsDialog.showAndWait().ifPresent(controller::changeSettings);
    }

    @Override
    public void showInvalidSettingsDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Game settings error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
