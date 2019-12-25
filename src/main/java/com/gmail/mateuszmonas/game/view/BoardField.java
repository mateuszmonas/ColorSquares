package com.gmail.mateuszmonas.game.view;

import com.gmail.mateuszmonas.model.Field;
import com.gmail.mateuszmonas.model.FieldState;
import com.gmail.mateuszmonas.util.GuiUtil;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BoardField extends Pane {

    public BoardField(double sideLength) {
        setPrefWidth(sideLength);
        setPrefHeight(sideLength);
    }

    public void update(Field field) {
        if (field.getState() == FieldState.BLOCKED) {
            setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (field.getState() == FieldState.EMPTY) {
            setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            setBackground(new Background(new BackgroundFill(GuiUtil.getColor(field.getOwnerId()), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

}
