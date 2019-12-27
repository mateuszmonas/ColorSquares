package com.gmail.mateuszmonas.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void isFull() {
    }

    @Test
    void isFieldEmpty() {
    }

    @Test
    void isConnected() {
    }
}