package com.gmail.mateuszmonas.model;

public interface GameObserver {

    void initialize(int boardWidth, int boardHeight);

    void update(int[][] boardState);

}
