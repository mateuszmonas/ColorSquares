package com.gmail.mateuszmonas.model.game;

import com.gmail.mateuszmonas.model.Player;
import com.gmail.mateuszmonas.model.field.Field;
import com.gmail.mateuszmonas.model.field.FieldStatus;

import java.util.*;
import java.util.stream.Collectors;

// TODO: 2019-12-25 improve obstructions generation
public class GameState {

    private GameSettings gameSettings;
    private Set<Player> players = new HashSet<>();
    private Player humanPlayer;
    private GameObserver observer;
    private boolean started = false;
    GameBoard gameBoard;

    public GameState(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        gameBoard = new GameBoard(gameSettings.getWidth(), gameSettings.getHeight(), gameSettings.getObstructionsCount());
        humanPlayer = new Player(1);
        players.add(humanPlayer);
        for (int i = 0; i < gameSettings.getBotCount(); i++) {
            Player player = new Player(i + 2);
            gameBoard.getRandomUnoccupiedField().ifPresent(field -> setStartingField(player, field));
            players.add(player);
        }
    }

    void selectStartingPosition(int x, int y) {
        if(started)return;
        if (gameBoard.isFieldEmpty(x, y)) {
            setStartingField(humanPlayer, gameBoard.getFieldAt(x, y));
            observer.update(gameBoard.getBoard());
            observer.startingFieldSelected();
        }
    }

    void setStartingField(Player player, Field field) {
        if (player.getStartingField() != null && player.getStartingField() != field) {
            player.getStartingField().setState(FieldStatus.EMPTY);
        }
        player.setStartingField(field);
    }

    void restart() {
        gameBoard.reset();
        for (Player player : players) {
            setStartingField(player, player.getStartingField());
        }
        observer.update(gameBoard.getBoard());
    }

    void growFields(Player player) {
        Set<Field> newFields = player.getFields().stream()
                .map(Field::getAdjacent)
                .flatMap(Set::stream)
                .filter(Field::isEmpty)
                .collect(Collectors.toSet());
        newFields.forEach(player::addField);
    }

    public void update() {
        if (gameBoard.isFull()) return;
        List<Player> randomOrder = new ArrayList<>(players);
        Collections.shuffle(randomOrder);
        randomOrder.forEach(this::growFields);
        observer.update(gameBoard.getBoard());
        if (gameBoard.isFull()) {
            observer.gameFinished(players);
            started = false;
        }
    }

    public void start() {
        started = true;
    }

    public void setObserver(GameObserver observer) {
        this.observer = observer;
        observer.initialize(gameSettings.getWidth(), gameSettings.getHeight());
        observer.update(gameBoard.getBoard());
    }
}
