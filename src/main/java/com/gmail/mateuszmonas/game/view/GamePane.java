package com.gmail.mateuszmonas.game.view;

import com.gmail.mateuszmonas.game.GameContract;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;


public class GamePane extends VBox implements GameContract.View {

    GameContract.Controller controller;
    BoardPane boardPane;
    ControlsPane controlsPane;

    public GamePane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);
        boardPane = new BoardPane(prefWidth, prefHeight / 10 * 9);
        controlsPane = new ControlsPane(prefWidth, prefHeight / 10);

        getChildren().addAll(boardPane, controlsPane);
    }

    @Override
    public void initialize(int boardWidth, int boardHeight) {
        boardPane.initialize(boardWidth, boardHeight);
    }

    @Override
    public void setController(GameContract.Controller controller) {
        this.controller = controller;
        boardPane.setController(controller);
        controlsPane.setController(controller);
    }

    @Override
    public void showStartButton() {
        controlsPane.showStartButton();
    }

    @Override
    public void showPauseButton() {
        controlsPane.showPauseButton();
    }

    @Override
    public void showRestartButton() {
        controlsPane.showRestartButton();
    }

    @Override
    public void updateBoardState(int[][] boardState) {
        boardPane.updateBoardState(boardState);
    }

    @Override
    public void showGameFinishedDialog(int playerScore, boolean playerWin) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game is finished");
        alert.setHeaderText("You " + (playerWin ? "won" : "lost") + ", your score is: " + playerScore);

        alert.showAndWait();
    }

    @Override
    public void enableStartButton() {
        controlsPane.enableStartButton();
    }
}
