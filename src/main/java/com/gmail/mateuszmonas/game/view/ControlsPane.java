package com.gmail.mateuszmonas.game.view;

import com.gmail.mateuszmonas.game.GameContract;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlsPane extends HBox {

    GameContract.Controller controller;
    Button backButton;
    Button startButton;
    Button pauseButton;
    Button restartButton;

    public ControlsPane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);

        backButton = new Button("back");
        startButton = new Button("start");
        pauseButton = new Button("pause");
        restartButton = new Button("restart");
        pauseButton.setVisible(false);
        restartButton.setVisible(false);

        backButton.setOnMouseClicked(mouseEvent -> controller.exitGame());
        startButton.setOnMouseClicked(mouseEvent -> controller.startGame());
        pauseButton.setOnMouseClicked(mouseEvent -> controller.pauseGame());
        restartButton.setOnMouseClicked(mouseEvent -> controller.restartGame());

        getChildren().addAll(backButton, startButton, pauseButton, restartButton);
    }

    public void showStartButton() {
        startButton.setVisible(true);
        pauseButton.setVisible(false);
        restartButton.setVisible(false);
    }

    public void showPauseButton() {
        startButton.setVisible(false);
        pauseButton.setVisible(true);
        restartButton.setVisible(false);
    }

    public void showRestartButton() {
        startButton.setVisible(false);
        pauseButton.setVisible(false);
        restartButton.setVisible(true);
    }

    public void setController(GameContract.Controller controller) {
        this.controller = controller;
    }
}
