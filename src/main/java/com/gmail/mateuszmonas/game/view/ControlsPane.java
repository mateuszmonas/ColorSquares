package com.gmail.mateuszmonas.game.view;

import com.gmail.mateuszmonas.game.GameContract;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ControlsPane extends HBox {

    GameContract.Controller controller;
    Button backButton;
    Button startButton;
    Text score;
    Text maxScore;

    public ControlsPane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);

        backButton = new Button("back");
        startButton = new Button("start");
        score = new Text("3");
        maxScore = new Text("4");

        backButton.setOnMouseClicked(mouseEvent -> controller.exitGame());
        startButton.setOnMouseClicked(mouseEvent -> controller.startGame());

        getChildren().addAll(backButton, startButton, score, maxScore);
    }

    public void updatePauseButton(boolean isPaused) {
        startButton.setText(isPaused ? "start" : "stop");
    }

    public void setController(GameContract.Controller controller) {
        this.controller = controller;
    }
}
