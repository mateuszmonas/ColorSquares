package com.gmail.mateuszmonas.game;

import com.gmail.mateuszmonas.menu.MenuController;
import com.gmail.mateuszmonas.menu.MenuPane;
import com.gmail.mateuszmonas.model.GameObserver;
import com.gmail.mateuszmonas.model.GameState;
import com.gmail.mateuszmonas.util.GuiUtil;
import javafx.application.Platform;

public class GameController implements GameContract.Controller, GameObserver {

    GameContract.View view;
    private boolean gameRunning = true;
    private boolean isPaused = true;
    private GameState gameState;

    public GameController(GameContract.View view) {
        this.view = view;
        view.setController(this);
        Thread gameThread = new Thread(() -> {
            Runnable runnable = gameState::update;
            while (gameRunning) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignore) {
                }
                if (!isPaused) {
                    Platform.runLater(runnable);
                }
            }
        });
        gameThread.setDaemon(true);
        gameThread.start();
    }

    @Override
    public void choosePosition(int x, int y) {

    }

    @Override
    public void startGame() {
        isPaused = !isPaused;
        view.updatePauseButton(isPaused);
    }

    @Override
    public void update(GameState gameState) {
        view.updateGameState(gameState);
    }

    @Override
    public void exitGame() {
        gameRunning = false;
        new MenuController(new MenuPane(GuiUtil.getWidth(), GuiUtil.getHeight())).start();
    }

    @Override
    public void start() {
        GuiUtil.changeScene(view);
    }
}
