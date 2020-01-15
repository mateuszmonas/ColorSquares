package com.gmail.mateuszmonas.model;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ObstructionsGeneratorTest {



    @Test
    void testGetDisconnectingFields() {
        int width = 100;
        int height = 100;
        int obstructionsCount = 0;
        GameBoard gameBoard = new GameBoard(width, height, obstructionsCount);
        assertFalse(ObstructionsGenerator.isDisconnected(gameBoard.getBoard()));
        gameBoard.getFieldAt(1, 0).setState(FieldState.BLOCKED);
        gameBoard.getFieldAt(1, 1).setState(FieldState.BLOCKED);
        gameBoard.getFieldAt(0, 1).setState(FieldState.BLOCKED);
        gameBoard.getFieldAt(0, 2).setState(FieldState.BLOCKED);
        gameBoard.getFieldAt(1, 2).setState(FieldState.BLOCKED);
        gameBoard.getFieldAt(2, 2).setState(FieldState.BLOCKED);
        gameBoard.getFieldAt(2, 1).setState(FieldState.BLOCKED);
        gameBoard.getFieldAt(2, 0).setState(FieldState.BLOCKED);
        assertTrue(ObstructionsGenerator.isDisconnected(gameBoard.getBoard()));
    }

    @Test
    void testGenerateObstructions() {
        int width = 100;
        int height = 100;
        int obstructionsCount = 50;
        GameBoard gameBoard = new GameBoard(width, height, 0);
        ObstructionsGenerator.generateObstructions(obstructionsCount, gameBoard);
        assertEquals(obstructionsCount, Arrays.stream(gameBoard.getBoard()).flatMap(Arrays::stream).filter(Field::isBlocked).count());
    }

}