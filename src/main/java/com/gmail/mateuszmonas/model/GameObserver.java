package com.gmail.mateuszmonas.model;

public interface GameObserver {

    void initialize(int boardWidth, int boardHeight);

    void update(Field[][] boardState);

    void gameFinished(int playerScore, boolean playerWin);

    void startingFieldSelected();

}
