package com.gmail.mateuszmonas.game.view;

import com.gmail.mateuszmonas.game.GameContract;
import com.gmail.mateuszmonas.model.Field;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class BoardPane extends GridPane {

    GameContract.Controller controller;
    BoardField[][] boardFields;

    public BoardPane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);
        setAlignment(Pos.CENTER);
    }

    public void initialize(int boardWidth, int boardHeight) {
        boardFields = new BoardField[boardWidth][boardHeight];
        for (int i = 0; i < boardFields.length; i++) {
            for (int j = 0; j < boardFields[i].length; j++) {
                int x = i;
                int y = j;
                boardFields[i][j] = new BoardField(Math.min(getPrefWidth() / boardWidth, getPrefHeight() / boardHeight));
                boardFields[i][j].setOnMouseClicked(mouseEvent -> controller.choosePosition(x, y));
                add(boardFields[i][j], i + 1, j + 1);
            }
        }
    }

    public void updateBoardState(Field[][] boardState) {
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                boardFields[i][j].update(boardState[i][j]);
            }
        }
    }

    public void setController(GameContract.Controller controller) {
        this.controller = controller;
    }
}
