package com.gmail.mateuszmonas.model;

import javafx.application.Platform;

public class Game {

    GameState gameState;
    private boolean running = true;
    private boolean paused = true;
    private Thread gameThread;

    public Game(GameSettings gameSettings) {
        gameState = new GameState(gameSettings);
        gameThread = new Thread(() -> {
            Runnable runnable = gameState::update;
            while (running) {
                try {
                    Thread.sleep(10000 / Math.max(gameSettings.getWidth(), gameSettings.getHeight()));
                } catch (InterruptedException ignore) {
                }
                if (!paused) {
                    Platform.runLater(runnable);
                }
            }
        });
        gameThread.setDaemon(true);
        gameThread.start();
    }

    public void start() {
        paused = false;
        gameState.start();
    }

    public void pause() {
        paused = true;
    }

    public void stop() {
        paused = true;
        running = false;
    }

    public void selectStartingPosition(int x, int y) {
        gameState.selectStartingPosition(x, y);
    }

    public void restartGame() {
        paused = true;
        gameState.restart();
    }

    public void setObserver(GameObserver observer) {
        gameState.setObserver(observer);
    }

}
