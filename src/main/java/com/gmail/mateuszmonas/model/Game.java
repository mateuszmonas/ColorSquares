package com.gmail.mateuszmonas.model;

import javafx.application.Platform;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private boolean running = false;
    private boolean paused = true;
    private Thread gameThread;
    private Set<GameObserver> observers = new HashSet<>();

    public Game(GameSettings gameSettings) {
        GameState gameState = new GameState(gameSettings);
        gameThread = new Thread(() -> {
            Runnable runnable = gameState::update;
            while (running) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignore) {
                }
                if (!paused) {
                    Platform.runLater(runnable);
                    observers.forEach(observer -> observer.update(gameState));
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

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

}
