package com.gmail.mateuszmonas.game;

import javafx.scene.layout.GridPane;

public class BoardPane extends GridPane {

    BoardField[][] boardFields;

    public BoardPane(double prefWidth, double prefHeight) {
        setPrefWidth(prefHeight);
        setPrefHeight(prefHeight);
    }
}
