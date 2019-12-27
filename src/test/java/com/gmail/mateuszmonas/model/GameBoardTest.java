package com.gmail.mateuszmonas.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void reset() {
        int width = 10;
        int height = 10;
        int obstructionsCount = 3;
        Player dummyPlayer = new Player(1);
        GameBoard gameBoard = new GameBoard(width, height, obstructionsCount);
        for (Field[] fields : gameBoard.getBoard()) {
            for (Field field : fields) {
                if (!field.isBlocked()) {
                    field.setOwner(dummyPlayer);
                }
            }
        }
        assertTrue(gameBoard.isFull());
        gameBoard.reset();
        assertEquals(width * height - obstructionsCount, Arrays.stream(gameBoard.getBoard()).flatMap(Arrays::stream).filter(Field::isEmpty).count());
    }

    @Test
    void isConnected() {
        int width = 10;
        int height = 10;
        int obstructionsCount = 0;
        GameBoard gameBoard = new GameBoard(width, height, obstructionsCount);
        assertTrue(gameBoard.isConnected());
        gameBoard.getFieldAt(1, 0).setState(FieldState.BLOCKED);
        gameBoard.getFieldAt(1, 1).setState(FieldState.BLOCKED);
        gameBoard.getFieldAt(0, 1).setState(FieldState.BLOCKED);
        assertFalse(gameBoard.isConnected());
    }
}