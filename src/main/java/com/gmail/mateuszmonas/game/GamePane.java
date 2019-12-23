package com.gmail.mateuszmonas.game;

import javafx.scene.layout.VBox;


public class GamePane extends VBox implements GameContract.View {

    GameContract.Controller controller;
    BoardPane boardPane;

    public GamePane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);
        boardPane = new BoardPane(prefWidth, prefHeight / 10 * 9);

    }

    @Override
    public void setController(GameContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void updateGameState(GameState gameState) {

    }
}
