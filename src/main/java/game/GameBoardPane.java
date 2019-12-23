package game;

import javafx.scene.layout.GridPane;

public class GameBoardPane extends GridPane {

    BoardField[][] boardFields;

    public GameBoardPane(double prefWidth, double prefHeight) {
        setPrefWidth(prefHeight);
        setPrefHeight(prefHeight);


    }
}
