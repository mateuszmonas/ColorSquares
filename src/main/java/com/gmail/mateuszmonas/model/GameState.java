package com.gmail.mateuszmonas.model;

import java.util.HashSet;

public class GameState {

    int x = 0;
    private int[][] board;
    private GameSettings gameSettings;
    private HashSet<Player> players;
    private GameObserver observer;

    public GameState(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        board = new int[gameSettings.getWidth()][gameSettings.getHeight()];
    }

    public void update() {
        observer.update(board);
    }

    public void setObserver(GameObserver observer) {
        this.observer = observer;
        observer.initialize(gameSettings.getWidth(), gameSettings.getHeight());
    }

    private static class Player {
        int color;
        int id;
        int fieldCount;
    }


}
