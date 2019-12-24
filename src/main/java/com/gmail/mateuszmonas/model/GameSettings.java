package com.gmail.mateuszmonas.model;

public class GameSettings {

    private int width = 10;
    private int height = 10;
    private int playerCount = 2;

    public GameSettings() {
    }

    public GameSettings(int width, int height, int playerCount) {
        this.width = width;
        this.height = height;
        this.playerCount = playerCount;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}

