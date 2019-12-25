package com.gmail.mateuszmonas.model;

import java.util.Set;

public interface GameObserver {

    void initialize(int boardWidth, int boardHeight);

    void update(Field[][] boardState);

    void gameFinished(Set<Player> players);

    void startingFieldSelected();

}
