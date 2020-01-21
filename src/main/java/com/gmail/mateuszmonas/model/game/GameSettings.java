package com.gmail.mateuszmonas.model.game;

public class GameSettings {

    private int width = 10;
    private int height = 10;
    private int botCount = 2;
    private int obstructionsCount = 10;

    public GameSettings(int width, int height, int botCount, int obstructionsCount) {
        this.width = width;
        this.height = height;
        this.botCount = botCount;
        this.obstructionsCount = obstructionsCount;
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

    public int getObstructionsCount() {
        return obstructionsCount;
    }
}

