package com.gmail.mateuszmonas.game;

import javafx.scene.layout.VBox;


public class GamePane extends VBox implements GameContract.View {

    GameContract.Controller controller;
    BoardPane boardPane;
    ControlsPane controlsPane;

    public GamePane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);
        boardPane = new BoardPane(prefWidth, prefHeight / 10 * 9);
        controlsPane = new ControlsPane(prefWidth, prefHeight / 10);

        getChildren().addAll(boardPane, controlsPane);
    }

    @Override
    public void setController(GameContract.Controller controller) {
        this.controller = controller;
        boardPane.setController(controller);
        controlsPane.setController(controller);
    }

    @Override
    public void updateStartButton(boolean gameRunning) {
        controlsPane.updateStartButton(gameRunning);
    }

    @Override
    public void updateGameState(GameState gameState) {

    }
}
