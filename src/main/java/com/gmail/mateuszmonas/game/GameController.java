package com.gmail.mateuszmonas.game;

import com.gmail.mateuszmonas.menu.MenuController;
import com.gmail.mateuszmonas.menu.view.MenuPane;
import com.gmail.mateuszmonas.model.field.Field;
import com.gmail.mateuszmonas.model.game.GameEngine;
import com.gmail.mateuszmonas.model.game.GameObserver;
import com.gmail.mateuszmonas.model.Player;
import com.gmail.mateuszmonas.util.GuiUtil;

import java.util.Set;

public class GameController implements GameContract.Controller, GameObserver {

    GameContract.View view;
    GameEngine gameEngine;

    public GameController(GameContract.View view, GameEngine gameEngine) {
        this.view = view;
        this.gameEngine = gameEngine;
        view.setController(this);
        gameEngine.setObserver(this);
    }

    @Override
    public void choosePosition(int x, int y) {
        gameEngine.selectStartingPosition(x, y);
    }

    @Override
    public void initialize(int boardWidth, int boardHeight) {
        view.initialize(boardWidth, boardHeight);
    }

    @Override
    public void startGame() {
        gameEngine.start();
        view.showPauseButton();
    }

    @Override
    public void update(Field[][] boardState) {
        view.updateBoardState(boardState);
    }

    @Override
    public void exitGame() {
        gameEngine.stop();
        new MenuController(new MenuPane(GuiUtil.getWidth(), GuiUtil.getHeight())).start();
    }

    @Override
    public void start() {
        GuiUtil.changeScene(view);
    }

    @Override
    public void gameFinished(Set<Player> players) {
        gameEngine.pause();
        view.showRestartButton();
        view.showGameFinishedDialog(players);
    }

    @Override
    public void pauseGame() {
        gameEngine.pause();
        view.showStartButton();
    }

    @Override
    public void restartGame() {
        gameEngine.restartGame();
        view.showStartButton();
    }

    @Override
    public void startingFieldSelected() {
        view.enableStartButton();
    }
}
