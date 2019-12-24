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
        if(i==-1)setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        else if(i==0)setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        else setBackground(new Background(new BackgroundFill(Color.rgb((i * 100)%256, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
