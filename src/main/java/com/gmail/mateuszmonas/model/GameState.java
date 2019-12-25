package com.gmail.mateuszmonas.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

// TODO: 2019-12-25 improve obstructions generation
public class GameState implements FieldObserver {

    private Field[][] board;
    private Set<Field> unoccupiedFields = new HashSet<>();
    private GameSettings gameSettings;
    private Set<Player> players = new HashSet<>();
    private Player humanPlayer;
    private GameObserver observer;
    private boolean started = false;

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
            getRandomUnoccupiedField().ifPresent(field -> field.setState(FieldState.BLOCKED));
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

    void selectStartingPosition(int x, int y) {
        if(started)return;
        if (unoccupiedFields.contains(board[x][y])) {
            setStartingField(humanPlayer, board[x][y]);
            observer.update(board);
            observer.startingFieldSelected();
        }
    }

    void setStartingField(Player player, Field field) {
        if (player.getStartingField() != null && player.getStartingField() != field) {
            player.getStartingField().setState(FieldState.EMPTY);
        }
        player.setStartingField(field);
    }

    void restart() {
        unoccupiedFields = Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(field -> field.getState() != FieldState.BLOCKED)
                .collect(Collectors.toSet());
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
    }

    public void update() {
        if (unoccupiedFields.isEmpty()) return;
        List<Player> randomOrder = new ArrayList<>(players);
        Collections.shuffle(randomOrder);
        randomOrder.forEach(this::growFields);
        observer.update(board);
        if (unoccupiedFields.isEmpty()) {
            observer.gameFinished(players);
            started = false;
        }
    }

    public void start() {
        started = true;
    }

    @Override
    public void onFieldStateChanged(Field field) {
        if(field.getState()==FieldState.EMPTY) unoccupiedFields.add(field);
        else unoccupiedFields.remove(field);
    }

    public void setObserver(GameObserver observer) {
        this.observer = observer;
        observer.initialize(gameSettings.getWidth(), gameSettings.getHeight());
        observer.update(board);
    }
}
