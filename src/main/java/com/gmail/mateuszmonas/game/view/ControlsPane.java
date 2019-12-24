package com.gmail.mateuszmonas.game.view;

import com.gmail.mateuszmonas.game.GameContract;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlsPane extends HBox {

    GameContract.Controller controller;
    Button backButton;
    Button startButton;

    public ControlsPane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);

        backButton = new Button("back");
        startButton = new Button("start");

        backButton.setOnMouseClicked(mouseEvent -> controller.exitGame());
        startButton.setOnMouseClicked(mouseEvent -> controller.startGame());

        getChildren().addAll(backButton, startButton);
    }

    public void updatePauseButton(boolean isPaused) {
        startButton.setText(isPaused ? "start" : "stop");
    }

    public void setController(GameContract.Controller controller) {
        this.controller = controller;
    }
}
