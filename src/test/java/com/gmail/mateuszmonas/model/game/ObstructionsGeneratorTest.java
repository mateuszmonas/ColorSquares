package com.gmail.mateuszmonas.model.game;

import com.gmail.mateuszmonas.model.field.Field;
import com.gmail.mateuszmonas.model.game.GameBoard;
import com.gmail.mateuszmonas.model.game.ObstructionsGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class ObstructionsGeneratorTest {

    private final static int UNVISITED = -1;

    static void dfs(Field field, int subGraphNo, int[][] visited) {
        Deque<Field> stack = new ArrayDeque<>();
        visited[field.x][field.y] = subGraphNo;
        stack.add(field);
        while (!stack.isEmpty()) {
            Field currentField = stack.pop();
            for (Field field1 : currentField.getAdjacent()) {
                if (visited[field1.x][field1.y]==UNVISITED && !field1.isBlocked()) {
                    visited[field1.x][field1.y] = subGraphNo;
                    stack.add(field1);
                }
            }
        }
    }

    static boolean isDisconnected(Field[][] board) {
        int[][] visited = new int[board.length][board[0].length];
        int subGraphNo = UNVISITED;
        for (int[] ints : visited) {
            Arrays.fill(ints, UNVISITED);
        }
        for (Field[] fields : board) {
            for (Field field : fields) {
                if (visited[field.x][field.y] == UNVISITED && !field.isBlocked()) {
                    if (subGraphNo > UNVISITED) return true;
                    dfs(field, ++subGraphNo, visited);
                }
            }
        }
        return false;
    }

    @Test
    void testGenerateObstructions() {
        int width = 100;
        int height = 100;
        int obstructionsCount = 75;
        GameBoard gameBoard = new GameBoard(width, height, 0);
        new ObstructionsGenerator(gameBoard.getBoard()).generateObstructions(obstructionsCount);
        assertEquals(obstructionsCount, Arrays.stream(gameBoard.getBoard()).flatMap(Arrays::stream).filter(Field::isBlocked).count());
        assertFalse(isDisconnected(gameBoard.getBoard()));
    }

}