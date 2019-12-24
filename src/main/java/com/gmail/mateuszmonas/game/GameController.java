package com.gmail.mateuszmonas.game;

import com.gmail.mateuszmonas.menu.MenuController;
import com.gmail.mateuszmonas.menu.MenuPane;
import com.gmail.mateuszmonas.model.Game;
import com.gmail.mateuszmonas.model.GameObserver;
import com.gmail.mateuszmonas.model.GameState;
import com.gmail.mateuszmonas.util.GuiUtil;

public class GameController implements GameContract.Controller, GameObserver {

    GameContract.View view;
    Game game;

    public GameController(GameContract.View view, Game game) {
        this.view = view;
        this.game = game;
        view.setController(this);
        game.addObserver(this);
    }

    @Override
    public void choosePosition(int x, int y) {

    }

    @Override
    public void startGame() {
        game.changePausedState();
        view.updatePauseButton(game.isPaused());
    }

    @Override
    public void update(GameState gameState) {
        view.updateGameState(gameState);
    }

    @Override
    public void exitGame() {
        game.stop();
        new MenuController(new MenuPane(GuiUtil.getWidth(), GuiUtil.getHeight())).start();
    }

    @Override
    public void start() {
        game.start();
        GuiUtil.changeScene(view);
    }
}
