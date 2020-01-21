package com.gmail.mateuszmonas.game.view;

import com.gmail.mateuszmonas.game.GameContract;
import com.gmail.mateuszmonas.model.field.Field;
import com.gmail.mateuszmonas.model.Player;
import com.gmail.mateuszmonas.util.GuiUtil;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.Set;


public class GamePane extends VBox implements GameContract.View {

    GameContract.Controller controller;
    BoardPane boardPane;
    ControlsPane controlsPane;
    Dialog<Void> resultsDialog;

    public GamePane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);
        boardPane = new BoardPane(prefWidth, prefHeight / 10 * 9);
        controlsPane = new ControlsPane(prefWidth, prefHeight / 10);
        resultsDialog = new Dialog<>();
        resultsDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        resultsDialog.setTitle("Game finished");
        resultsDialog.setHeaderText("score");

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
    public void updateBoardState(Field[][] boardState) {
        boardPane.updateBoardState(boardState);
    }

    @Override
    public void showGameFinishedDialog(Set<Player> players) {
        GridPane grid = new GridPane();
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPrefWidth(getPrefWidth() / 10);
        int maxScore = players.stream()
                .mapToInt(player -> player.getFields().size())
                .max()
                .orElse(0);
        int i = 0;
        for (Player player : players) {
            Pane pane = new Pane();
            pane.setBackground(new Background(new BackgroundFill(GuiUtil.getColor(player.getId()), CornerRadii.EMPTY, Insets.EMPTY)));
            pane.setPrefWidth(getPrefWidth() / 5);
            grid.add(pane, 0, i);
            grid.add(new Text(String.valueOf(player.getFields().size())), 1, i);
            if (player.getFields().size() == maxScore) {
                grid.add(new Text("W"), 2, i);
            }
            i++;
        }
        resultsDialog.getDialogPane().setContent(grid);
        resultsDialog.showAndWait();
    }

    @Override
    public void enableStartButton() {
        controlsPane.enableStartButton();
    }
}
