package com.gmail.mateuszmonas.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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

    // get all blocked fields connected to given subGraph
    Set<Field> getDisconnectingElements(Field field, int subGraphNo, int[][] visited) {
        Set<Field> disconnectingElements = new HashSet<>();
        Deque<Field> stack = new ArrayDeque<>();
        stack.add(field);
        while (!stack.isEmpty()) {
            Field currentField = stack.pop();
            visited[currentField.x][currentField.y] = subGraphNo;
            for (Field field1 : currentField.getAdjacent()) {
                if (visited[field1.x][field1.y]==UNVISITED && !field1.isBlocked()) {
                    stack.add(field1);
                } else if (field1.isBlocked()) {
                    disconnectingElements.add(field1);
                }
            }
        }
        return disconnectingElements;
    }


    Map<Field, List<Integer>> getDisconnectingFields() {
        int[][] visited = new int[board.length][board[0].length];
        for (int[] ints : visited) {
            Arrays.fill(ints, UNVISITED);
        }
        // map of border elements and list of subGraphs they are connected to
        Map<Field, List<Integer>> disconnectingElements = new HashMap<>();
        int subGraphNo = UNVISITED;
        for (Field[] fields : board) {
            for (Field field : fields) {
                if (visited[field.x][field.y]==UNVISITED && !field.isBlocked()) {
                    for (Field disconnectingElement : getDisconnectingElements(field, ++subGraphNo, visited)) {
                        if (!disconnectingElements.containsKey(disconnectingElement)) {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(subGraphNo);
                            disconnectingElements.put(disconnectingElement, temp);
                        } else {
                            disconnectingElements.get(disconnectingElement).add(subGraphNo);
                        }
                    }
                }
            }
        }
        return disconnectingElements.entrySet().stream()
                .filter(entry -> 1<entry.getValue().size())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    void generateObstructions(int obstructionsCount) {
        for (int i = 0; i < obstructionsCount; i++) {
            getRandomUnoccupiedField().ifPresent(field -> field.setState(FieldState.BLOCKED));
        }
    }
}
