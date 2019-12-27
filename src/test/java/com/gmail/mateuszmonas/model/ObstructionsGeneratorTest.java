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
        assertEquals(0, ObstructionsGenerator.getDisconnectingFields(gameBoard.getBoard()).size());
        gameBoard.getFieldAt(1, 0).setState(FieldState.BLOCKED);
        gameBoard.getFieldAt(1, 1).setState(FieldState.BLOCKED);
        gameBoard.getFieldAt(0, 1).setState(FieldState.BLOCKED);
        assertEquals(2, ObstructionsGenerator.getDisconnectingFields(gameBoard.getBoard()).size());
    }

    @RepeatedTest(100)
    void testRemoveDisconnectingObstructions() {
        int width = 100;
        int height = 100;
        int obstructionsCount = 5000;
        GameBoard gameBoard = new GameBoard(width, height, obstructionsCount);
        ObstructionsGenerator.removeDisconnectingObstructions(gameBoard.getBoard());
        assertEquals(0, ObstructionsGenerator.getDisconnectingFields(gameBoard.getBoard()).size());
    }

}