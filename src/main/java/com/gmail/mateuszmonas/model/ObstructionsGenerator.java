package com.gmail.mateuszmonas.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ObstructionsGenerator {

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

    static void generateObstructions(int obstructionsCount, GameBoard gameBoard) {
    }



}
