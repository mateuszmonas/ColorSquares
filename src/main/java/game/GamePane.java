package game;

import javafx.scene.layout.VBox;
import menu.MenuContract;

public class GamePane extends VBox implements GameContract.View {

    MenuContract.Controller controller;

    public GamePane(double prefWidth, double prefHeight) {
        setPrefWidth(prefHeight);
        setPrefHeight(prefHeight);
    }

    @Override
    public void setController(MenuContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void updateGameState(GameState gameState) {

    }
}
