package com.gmail.mateuszmonas.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GameState {

    private static final int EMPTY = 0;
    private static final int BLOCKED = -1;
    private Field[][] board;
    private Set<Field> unoccupiedFields = new HashSet<>();
    private GameSettings gameSettings;
    private Set<Player> players = new HashSet<>();
    private Player humanPlayer;
    private GameObserver observer;

    public GameState(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        generateGrid(gameSettings.getWidth(), gameSettings.getHeight());
        for (int i = 0; i < gameSettings.getBotCount(); i++) {
            Player player = new Player(i + 1);
            getRandomUnoccupiedField().ifPresent(player::setStartingField);
            players.add(player);
        }
        for (int i = 0; i < gameSettings.getObstructionsCount(); i++) {
            getRandomUnoccupiedField().ifPresent(field -> {
                field.color = BLOCKED;
                unoccupiedFields.remove(field);
            });
        }
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

    void generateGrid(int width, int height) {
        board = new Field[gameSettings.getWidth()][gameSettings.getHeight()];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Field field = new Field(i, j);
                board[i][j] = field;
                unoccupiedFields.add(field);
            }
        }
        int[] x = {1, 0, -1, 0};
        int[] y = {0, 1, 0, -1};
        for (Field field : unoccupiedFields) {
            for (int i = 0; i < 4; i++) {
                int a = field.x + x[i];
                int b = field.y + y[i];
                if (0 <= a && a < board.length && 0 <= b && b < board[a].length) {
                    field.adjacent.add(board[a][b]);
                }
            }
        }
    }

    void growFields(Set<Field> fields) {
        int color = fields.stream()
                .mapToInt(field -> field.color)
                .findAny()
                .orElse(EMPTY);
        Set<Field> newFields = fields.stream()
                .map(field -> field.adjacent)
                .flatMap(Set::stream)
                .filter(field -> field.color == EMPTY)
                .collect(Collectors.toSet());
        newFields.forEach(field -> field.color = color);
        fields.addAll(newFields);
        unoccupiedFields.removeAll(fields);
    }

    public void update() {
        List<Player> randomOrder = new ArrayList<>(players);
        Collections.shuffle(randomOrder);
        randomOrder.forEach(player -> growFields(player.fields));
        observer.update(Arrays.stream(board)
                .map(fields -> Arrays.stream(fields)
                        .mapToInt(field -> field.color)
                        .toArray())
                .toArray(int[][]::new));
    }

    public void setObserver(GameObserver observer) {
        this.observer = observer;
        observer.initialize(gameSettings.getWidth(), gameSettings.getHeight());
        observer.update(Arrays.stream(board)
                .map(fields -> Arrays.stream(fields)
                        .mapToInt(field -> field.color)
                        .toArray())
                .toArray(int[][]::new));
    }

    private static class Field {
        int x, y;
        int color = GameState.EMPTY;
        Set<Field> adjacent = new HashSet<>();

        public Field(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Field)) return false;
            Field field = (Field) o;
            return x == field.x &&
                    y == field.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static class Player {
        int color;
        int fieldCount = 0;
        Set<Field> fields = new HashSet<>();

        public Player(int color) {
            this.color = color;
        }

        void setStartingField(Field field) {
            fields.add(field);
            field.color = color;
        }
    }
}
