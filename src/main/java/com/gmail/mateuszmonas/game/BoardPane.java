package com.gmail.mateuszmonas.game;

import javafx.scene.layout.GridPane;

public class BoardPane extends GridPane {

    GameContract.Controller controller;
    BoardField[][] boardFields;

    public BoardPane(double prefWidth, double prefHeight) {
        setPrefWidth(prefHeight);
        setPrefHeight(prefHeight);
    }

    public void setController(GameContract.Controller controller) {
        this.controller = controller;
    }
}
