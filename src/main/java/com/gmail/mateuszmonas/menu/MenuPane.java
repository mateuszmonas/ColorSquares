package com.gmail.mateuszmonas.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuPane extends VBox implements MenuContract.View {

    MenuContract.Controller controller;
    SettingsPane settingsPane;

    public MenuPane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);
        setAlignment(Pos.CENTER);

        settingsPane = new SettingsPane(prefWidth / 10, prefHeight / 10);

        Button startButton = new Button("start");
        Button exitButton = new Button("exit");

        startButton.setOnMouseClicked(mouseEvent -> controller.startGame());
        exitButton.setOnMouseClicked(mouseEvent -> controller.exitApplication());


        getChildren().addAll(settingsPane, startButton, exitButton);
    }

    @Override
    public void setController(MenuContract.Controller controller) {
        this.controller = controller;
        settingsPane.setController(controller);
    }
}
