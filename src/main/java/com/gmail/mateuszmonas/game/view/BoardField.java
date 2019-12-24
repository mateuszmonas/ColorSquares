package com.gmail.mateuszmonas.game.view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BoardField extends Pane {

    public BoardField(double prefWidth, double prefHeight) {
        setPrefWidth(prefHeight);
        setPrefHeight(prefHeight);
    }

    public void update(int i) {
        setBackground(new Background(new BackgroundFill(Color.rgb(i * 125, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
