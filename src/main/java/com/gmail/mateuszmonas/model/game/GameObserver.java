package com.gmail.mateuszmonas.model.game;

import com.gmail.mateuszmonas.model.Player;
import com.gmail.mateuszmonas.model.field.Field;

import java.util.Set;

public interface GameObserver {

    void initialize(int boardWidth, int boardHeight);

    void update(Field[][] boardState);

    void gameFinished(Set<Player> players);

    void startingFieldSelected();

}
