package com.gmail.mateuszmonas.model;

import java.util.*;
import java.util.stream.Collectors;

public class GameState {

    private int[][] board;
    private GameSettings gameSettings;
    private HashSet<Player> players = new HashSet<>();
    private GameObserver observer;

    public GameState(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        board = new int[gameSettings.getWidth()][gameSettings.getHeight()];

        Player player = new Player(1);
        player.fields.add(new Field(4, 4, 1));
        board[4][4] = 1;
        players.add(player);
    }

    Set<Field> growField(Field field) {
        Set<Field> newFields = new HashSet<>();
        newFields.add(field);
        int[] x = {1, 0, -1, 0};
        int[] y = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int a = field.x + x[i];
            int b = field.y + y[i];
            if (0 <= a && a < board.length && 0 <= b && b < board[a].length && board[a][b] == 0) {
                board[a][b] = field.color;
                newFields.add(new Field(a, b, field.color));
            }
        }
        return newFields;
    }

    Set<Field> growFields(Set<Field> fields) {
        return fields.stream()
                .map(this::growField)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public void update() {
        players.forEach(player -> player.fields=growFields(player.fields));
        observer.update(board);
    }

    public void setObserver(GameObserver observer) {
        this.observer = observer;
        observer.initialize(gameSettings.getWidth(), gameSettings.getHeight());
        observer.update(board);
    }

    private static class Field {
        int x, y, color;

        public Field(int x, int y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
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
        int id;
        int fieldCount = 0;
        Set<Field> fields = new HashSet<>();

        public Player(int id) {
            this.id = id;
        }
    }
}
