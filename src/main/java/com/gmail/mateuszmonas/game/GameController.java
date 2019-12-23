package com.gmail.mateuszmonas.game;

import com.gmail.mateuszmonas.Main;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GameController implements GameContract.Controller {

    GameContract.View view;

    public GameController(GameContract.View view) {
        this.view = view;
        view.setController(this);
    }

    @Override
    public void choosePosition(int x, int y) {

    }

    @Override
    public void startGame() {

    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void start() {
        Main.getPrimaryStage().setScene(new Scene((Parent) view));
    }
}
