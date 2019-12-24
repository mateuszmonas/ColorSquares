package com.gmail.mateuszmonas.model;

public class GameSettings {

    private int width = 10;
    private int height = 10;
    private int botCount = 2;

    public GameSettings() {
    }

    public GameSettings(int width, int height, int botCount) {
        this.width = width;
        this.height = height;
        this.botCount = botCount;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBotCount() {
        return botCount;
    }
}

