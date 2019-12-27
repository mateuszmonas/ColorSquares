package com.gmail.mateuszmonas.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard implements FieldObserver {

    private Field[][] board;
    private Node[][] graph;
    private static final int UNVISITED = -1;
    private Set<Field> unoccupiedFields = new HashSet<>();

    public GameBoard(int width, int height) {
        generateGrid(width, height);
    }

    void reset() {
        for (Field[] fields : board) {
            for (Field field : fields) {
                if (field.getState() != FieldState.BLOCKED) {
                    field.setState(FieldState.EMPTY);
                    unoccupiedFields.add(field);
                }
            }
        }
    }

    boolean isFull() {
        return unoccupiedFields.isEmpty();
    }

    void generateGrid(int width, int height) {
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
    }

    public Field[][] getBoard() {
        return board;
    }

    Field getFieldAt(int x, int y) {
        return board[x][y];
    }

    boolean isFieldEmpty(int x, int y) {
        return board[x][y].getState() == FieldState.EMPTY;
    }

    @Override
    public void onFieldStateChanged(Field field) {
        if(field.getState()==FieldState.EMPTY) unoccupiedFields.add(field);
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

    void dfs(Node node, int subGraphNo) {
        Deque<Node> stack = new ArrayDeque<>();
        stack.add(node);
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            currentNode.setSubGraphNo(subGraphNo);
            for (Node node1 : currentNode.getAdjacent()) {
                if (!node1.isVisited() && !node1.isBlocked()) {
                    stack.add(node1);
                }
            }
        }
    }

    boolean isConnected() {
        int subGraphNo = UNVISITED;
        for (Node[] nodes : graph) {
            for (Node node : nodes) {
                if (node.isVisited()) {
                    dfs(node, subGraphNo++);
                }
            }
        }
        return UNVISITED + 1 < subGraphNo;
    }

    private static class Node {
        Field field;
        int subGraphNo;
        Set<Node> adjacent;

        public Node(Field field) {
            this.field = field;
            subGraphNo = -1;
        }

        public void setSubGraphNo(int subGraphNo) {
            this.subGraphNo = subGraphNo;
        }

        public Set<Node> getAdjacent() {
            return adjacent;
        }

        public void setAdjacent(Set<Node> adjacent) {
            this.adjacent = adjacent;
        }

        boolean isBlocked() {
            return field.getState() == FieldState.BLOCKED;
        }

        boolean isVisited() {
            return subGraphNo == UNVISITED;
        }
    }

}
