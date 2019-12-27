package com.gmail.mateuszmonas.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard implements FieldObserver {

    public static final int UNVISITED = -1;
    private Field[][] board;
    private Set<Field> unoccupiedFields = new HashSet<>();

    public GameBoard(int width, int height, int obstructionsCount) {
        board = new Field[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Field field = new Field(i, j);
                board[i][j] = field;
                board[i][j].setObserver(this);


            }
        }
        int[] x = {1, 0, -1, 0};
        int[] y = {0, 1, 0, -1};
        for (Field field : unoccupiedFields) {
            for (int i = 0; i < 4; i++) {
                int a = field.x + x[i];
                int b = field.y + y[i];
                if (0 <= a && a < board.length && 0 <= b && b < board[a].length) {
                    field.addAdjacent((board[a][b]));
                }
            }
        }
        generateObstructions(obstructionsCount);
    }

    void reset() {
        for (Field[] fields : board) {
            for (Field field : fields) {
                if (!field.isBlocked()) {
                    field.setState(FieldState.EMPTY);
                    unoccupiedFields.add(field);
                }
            }
        }
    }

    boolean isFull() {
        return unoccupiedFields.isEmpty();
    }

    public Field[][] getBoard() {
        return board;
    }

    Field getFieldAt(int x, int y) {
        return board[x][y];
    }

    boolean isFieldEmpty(int x, int y) {
        return board[x][y].isEmpty();
    }

    @Override
    public void onFieldStateChanged(Field field) {
        if(field.isEmpty()) unoccupiedFields.add(field);
        else unoccupiedFields.remove(field);
    }

    Optional<Field> getRandomUnoccupiedField() {
        int r = ThreadLocalRandom.current().nextInt(unoccupiedFields.size());
        int i = 0;
        Field field = null;
        for (Field unoccupiedField : unoccupiedFields) {
            if (i == r) {
                field = unoccupiedField;
                break;
            }
            i++;
        }
        return Optional.ofNullable(field);
    }

    void dfs(Field field, int subGraphNo) {
        Deque<Field> stack = new ArrayDeque<>();
        stack.add(field);
        while (!stack.isEmpty()) {
            Field currentField = stack.pop();
            currentField.setSubGraphNo(subGraphNo);
            for (Field field1 : currentField.getAdjacent()) {
                if (!field1.isVisited() && !field1.isBlocked()) {
                    stack.add(field1);
                }
            }
        }
    }

    boolean isConnected() {
        int subGraphNo = UNVISITED;
        for (Field[] fields : board) {
            for (Field field : fields) {
                if (!field.isVisited()) {
                    dfs(field, subGraphNo++);
                }
            }
        }
        return subGraphNo == UNVISITED + 1;
    }

    void generateObstructions(int obstructionsCount) {
        for (int i = 0; i < obstructionsCount; i++) {
            getRandomUnoccupiedField().ifPresent(field -> field.setState(FieldState.BLOCKED));
        }
    }
}
