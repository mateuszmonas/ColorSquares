package com.gmail.mateuszmonas.game.view;

import javafx.scene.control.Button;

public class BoardField extends Button {

    public BoardField(double prefWidth, double prefHeight) {
        setPrefWidth(prefHeight);
        setPrefHeight(prefHeight);
        setText("0");
    }

    public void update(int i) {
        setText(String.valueOf(i));
    }

}
