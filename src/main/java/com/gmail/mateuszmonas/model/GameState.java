package com.gmail.mateuszmonas.model;

import java.util.HashSet;

public class GameState {

    private int board[][];
    private GameSettings gameSettings;
    private HashSet<Player> players;


    public GameState(GameSettings gameSettings) {
        this.gameSettings = gameSettings;

    }

    public void update() {
        System.out.println("debug");
    }

    private static class Player {
        int color;
        int id;
        int fieldCount;
    }


}
