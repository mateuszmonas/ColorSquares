package com.gmail.mateuszmonas.game.view;

import com.gmail.mateuszmonas.game.GameContract;
import com.gmail.mateuszmonas.model.GameState;
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
    public void initialize(int boardWidth, int boardHeight) {
        boardPane.initialize(boardWidth, boardHeight);
    }

    @Override
    public void setController(GameContract.Controller controller) {
        this.controller = controller;
        boardPane.setController(controller);
        controlsPane.setController(controller);
    }

    @Override
    public void updatePauseButton(boolean gameRunning) {
        controlsPane.updatePauseButton(gameRunning);
    }

    @Override
    public void updateBoardState(int[][] boardState) {
        boardPane.updateBoardState(boardState);
    }
}
