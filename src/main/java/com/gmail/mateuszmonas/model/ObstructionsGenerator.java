package com.gmail.mateuszmonas.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ObstructionsGenerator {

    private static final int UNVISITED = -1;

    // get all blocked fields connected to given subGraph
    static Set<Field> getBorderFields(Field field, int subGraphNo, int[][] visited) {
        Set<Field> disconnectingElements = new HashSet<>();
        Deque<Field> stack = new ArrayDeque<>();
        visited[field.x][field.y] = subGraphNo;
        stack.add(field);
        while (!stack.isEmpty()) {
            Field currentField = stack.pop();
            for (Field field1 : currentField.getAdjacent()) {
                if (visited[field1.x][field1.y]==UNVISITED && !field1.isBlocked()) {
                    visited[field1.x][field1.y] = subGraphNo;
                    stack.add(field1);
                } else if (field1.isBlocked()) {
                    disconnectingElements.add(field1);
                }
            }
        }
        return disconnectingElements;
    }


    static Map<Field, Set<Integer>> getDisconnectingFields(Field[][] board) {
        int[][] visited = new int[board.length][board[0].length];
        for (int[] ints : visited) {
            Arrays.fill(ints, UNVISITED);
        }
        // map of border elements and list of subGraphs they are connected to
        Map<Field, Set<Integer>> disconnectingElements = new HashMap<>();
        int subGraphNo = UNVISITED;
        for (Field[] fields : board) {
            for (Field field : fields) {
                if (visited[field.x][field.y]==UNVISITED && !field.isBlocked()) {
                    for (Field disconnectingElement : getBorderFields(field, ++subGraphNo, visited)) {
                        if (!disconnectingElements.containsKey(disconnectingElement)) {
                            Set<Integer> temp = new HashSet<>();
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
                .filter(e -> e.getValue().size()>1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    static int removeDisconnectingObstructions(Field[][] board) {
        int removedObstructionsCount = 0;
        Map<Field, Set<Integer>> disconnectingElements = getDisconnectingFields(board);
        // sometimes not all disconnecting elements are removed so we repeat the process until they are
        while (!disconnectingElements.isEmpty()) {
            while (!disconnectingElements.isEmpty()) {
                // find field disconnecting most subGraphs
                Map.Entry<Field, Set<Integer>> fieldEntry = disconnectingElements.entrySet().stream().findAny().orElse(null);
                // set its state to empty
                fieldEntry.getKey().setState(FieldState.EMPTY);
                // remove all elements disconnecting same subGraphs as fieldEntry from map
                disconnectingElements.entrySet().stream()
                        .filter(entry -> entry.getValue().equals(fieldEntry.getValue()))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toSet())
                        .forEach(disconnectingElements::remove);
                removedObstructionsCount++;
            }
            disconnectingElements = getDisconnectingFields(board);
        }
        return removedObstructionsCount;
    }

    // do dfs on board and remove fields reached last so not to disconnect the graph
    static void generatePseudoRandomObstructions(int obstructionsToReAdd, Field[][] board) throws NoSuchElementException {
        boolean[][] visited = new boolean[board.length][board[0].length];

        Field field = null;
        for (Field[] fields : board) {
            for (Field field1 : fields) {
                if (field1.isEmpty()) {
                    field = field1;
                    break;
                }
            }
            if (field != null) {
                break;
            }
        }
        if(field == null) return;

        LinkedList<Field> removalOrder = new LinkedList<>();
        Deque<Field> stack = new ArrayDeque<>();
        visited[field.x][field.y] = true;
        stack.add(field);
        while (!stack.isEmpty()) {
            Field currentField = stack.pop();
            removalOrder.addFirst(currentField);
            for (Field field1 : currentField.getAdjacent()) {
                if (!visited[field1.x][field1.y] && !field1.isBlocked()) {
                    visited[field1.x][field1.y] = true;
                    stack.add(field1);
                }
            }
        }
        if (removalOrder.size() < obstructionsToReAdd) {
            for (Field[] fields : board) {
                for (Field field1 : fields) {
                    field1.setState(FieldState.EMPTY);
                }
            }
        }
        for (int i = 0; i < obstructionsToReAdd; i++) {
            removalOrder.removeFirst().setState(FieldState.BLOCKED);
        }
    }

    // FIXME: 2019-12-28 obstructions generation doesn't work when we generate a subGraph separated by two or more layers of blocked fields
    static void generateObstructions(int obstructionsCount, GameBoard gameBoard) {
        for (int i = 0; i < obstructionsCount; i++) {
            gameBoard.getRandomUnoccupiedField().ifPresent(field -> field.setState(FieldState.BLOCKED));
        }
        int removedObstructionsCount = removeDisconnectingObstructions(gameBoard.getBoard());
    }
}
