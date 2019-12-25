package com.gmail.mateuszmonas.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

// TODO: 2019-12-25 improve obstructions generation
public class GameState {

    private Field[][] board;
    private List<Field> unoccupiedFields = new ArrayList<>();
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
            getRandomUnoccupiedFieldIndex().ifPresent(index -> setStartingField(player, unoccupiedFields.get(index)));
            players.add(player);
        }
        for (int i = 0; i < gameSettings.getObstructionsCount(); i++) {
            getRandomUnoccupiedFieldIndex().ifPresent(index -> {
                Field field = unoccupiedFields.get(index);
                field.setState(FieldState.BLOCKED);
                unoccupiedFields.remove(field);
            });
        }
    }

    OptionalInt getRandomUnoccupiedFieldIndex() {
        if (unoccupiedFields.isEmpty()) return OptionalInt.empty();
        return OptionalInt.of(ThreadLocalRandom.current().nextInt(unoccupiedFields.size()));
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
                    field.addAdjacent((board[a][b]));
                }
            }
        }
    }

    void selectStartingPosition(int x, int y) {
        if (unoccupiedFields.contains(board[x][y])) {
            setStartingField(humanPlayer, board[x][y]);
            observer.update(board);
            observer.startingFieldSelected();
        }
    }

    void setStartingField(Player player, Field field) {
        if (player.getStartingField() != null) {
            player.getStartingField().setState(FieldState.EMPTY);
            unoccupiedFields.add(player.getStartingField());
        }
        player.setStartingField(field);
        unoccupiedFields.remove(field);
    }

    void restart() {
        unoccupiedFields = Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(field -> field.getState() != FieldState.BLOCKED)
                .collect(Collectors.toList());
        unoccupiedFields.forEach(field -> field.setState(FieldState.EMPTY));
        for (Player player : players) {
            setStartingField(player, player.getStartingField());
        }
        observer.update(board);
    }

    void growFields(Player player) {
        Set<Field> newFields = player.getFields().stream()
                .map(Field::getAdjacent)
                .flatMap(Set::stream)
                .filter(field -> field.getState() == FieldState.EMPTY)
                .collect(Collectors.toSet());
        newFields.forEach(player::addField);
        unoccupiedFields.removeAll(newFields);
    }

    public void update() {
        if (unoccupiedFields.isEmpty()) return;
        List<Player> randomOrder = new ArrayList<>(players);
        Collections.shuffle(randomOrder);
        randomOrder.forEach(this::growFields);
        observer.update(board);
        if (unoccupiedFields.isEmpty()) {
            observer.gameFinished(humanPlayer.getFields().size(),
                    players.stream()
                            .mapToInt(player -> player.getFields().size())
                            .max()
                            .orElse(humanPlayer.getFields().size()) == humanPlayer.getFields().size()
            );
        }
    }

    public void setObserver(GameObserver observer) {
        this.observer = observer;
        observer.initialize(gameSettings.getWidth(), gameSettings.getHeight());
        observer.update(board);
    }
}
