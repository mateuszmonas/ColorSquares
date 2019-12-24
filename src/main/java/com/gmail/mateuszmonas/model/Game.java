package com.gmail.mateuszmonas.model;

import javafx.application.Platform;

public class Game {

    GameState gameState;
    private boolean running = false;
    private boolean paused = true;
    private Thread gameThread;

    public Game(GameSettings gameSettings) {
        gameState = new GameState(gameSettings);
        gameThread = new Thread(() -> {
            Runnable runnable = gameState::update;
            while (running) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignore) {
                }
                if (!paused) {
                    Platform.runLater(runnable);
                }
            }
        });
        gameThread.setDaemon(true);
    }

    public void start() {
        running = true;
        paused = true;
        gameThread.start();
    }

    public void changePausedState() {
        paused = !paused;
    }

    public boolean isPaused() {
        return paused;
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
