package com.gmail.mateuszmonas.game;

import com.gmail.mateuszmonas.menu.MenuController;
import com.gmail.mateuszmonas.menu.MenuPane;
import com.gmail.mateuszmonas.model.Field;
import com.gmail.mateuszmonas.model.Game;
import com.gmail.mateuszmonas.model.GameObserver;
import com.gmail.mateuszmonas.util.GuiUtil;

public class GameController implements GameContract.Controller, GameObserver {

    GameContract.View view;
    Game game;

    public GameController(GameContract.View view, Game game) {
        this.view = view;
        this.game = game;
        view.setController(this);
        game.setObserver(this);
    }

    @Override
    public void choosePosition(int x, int y) {
        game.selectStartingPosition(x, y);
    }

    @Override
    public void initialize(int boardWidth, int boardHeight) {
        view.initialize(boardWidth, boardHeight);
    }

    @Override
    public void startGame() {
        game.start();
        view.showPauseButton();
    }

    @Override
    public void update(Field[][] boardState) {
        view.updateBoardState(boardState);
    }

    @Override
    public void exitGame() {
        game.stop();
        new MenuController(new MenuPane(GuiUtil.getWidth(), GuiUtil.getHeight())).start();
    }

    @Override
    public void start() {
        GuiUtil.changeScene(view);
    }

    @Override
    public void gameFinished(int playerScore, boolean playerWin) {
        game.pause();
        view.showRestartButton();
        view.showGameFinishedDialog(playerScore, playerWin);
    }

    @Override
    public void pauseGame() {
        game.pause();
        view.showStartButton();
    }

    @Override
    public void restartGame() {
        game.restartGame();
        view.showStartButton();
    }

    @Override
    public void startingFieldSelected() {
        view.enableStartButton();
    }
}
