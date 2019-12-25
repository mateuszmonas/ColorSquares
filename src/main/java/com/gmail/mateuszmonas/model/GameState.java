package com.gmail.mateuszmonas.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

// TODO: 2019-12-25 improve obstructions generation
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
        humanPlayer = new Player(1);
        players.add(humanPlayer);
        for (int i = 0; i < gameSettings.getBotCount(); i++) {
            Player player = new Player(i + 2);
            getRandomUnoccupiedField().ifPresent(field -> setStartingField(player, field));
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

    void selectStartingPosition(int x, int y) {
        if (unoccupiedFields.contains(board[x][y])) {
            setStartingField(humanPlayer, board[x][y]);
            observer.update(boardToArray());
            observer.startingFieldSelected();
        }
    }

    void setStartingField(Player player, Field field) {
        if (player.startingField != null) {
            player.fields.clear();
            player.startingField.color = 0;
            unoccupiedFields.add(player.startingField);
        }
        player.startingField = field;
        unoccupiedFields.remove(player.startingField);
        player.fields.add(field);
        field.color = player.color;

    }

    void restart() {
        unoccupiedFields = Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(field -> field.color != BLOCKED)
                .collect(Collectors.toSet());
        unoccupiedFields.forEach(field -> field.color = EMPTY);
        for (Player player : players) {
            setStartingField(player, player.startingField);
        }
        observer.update(boardToArray());
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
        if (unoccupiedFields.isEmpty()) return;
        List<Player> randomOrder = new ArrayList<>(players);
        Collections.shuffle(randomOrder);
        randomOrder.forEach(player -> growFields(player.fields));
        observer.update(boardToArray());
        if (unoccupiedFields.isEmpty()) {
            observer.gameFinished(humanPlayer.fields.size(),
                    players.stream()
                            .mapToInt(player -> player.fields.size())
                            .max()
                            .orElse(humanPlayer.fields.size()) == humanPlayer.fields.size()
            );
        }
    }

    int[][] boardToArray() {
        return Arrays.stream(board)
                .map(fields -> Arrays.stream(fields)
                        .mapToInt(field -> field.color)
                        .toArray())
                .toArray(int[][]::new);
    }

    public void setObserver(GameObserver observer) {
        this.observer = observer;
        observer.initialize(gameSettings.getWidth(), gameSettings.getHeight());
        observer.update(boardToArray());
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
        Field startingField;
        Set<Field> fields = new HashSet<>();

        public Player(int color) {
            this.color = color;
        }
    }
}
