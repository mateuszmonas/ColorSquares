package game;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import menu.MenuContract;


public class GamePane extends VBox implements GameContract.View {

    GameContract.Controller controller;

    public GamePane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);
    }

    @Override
    public void setController(GameContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void updateGameState(GameState gameState) {

    }
}
