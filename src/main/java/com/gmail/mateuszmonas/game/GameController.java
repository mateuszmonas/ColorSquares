package com.gmail.mateuszmonas.game;

import com.gmail.mateuszmonas.Main;
import com.gmail.mateuszmonas.menu.MenuController;
import com.gmail.mateuszmonas.menu.MenuPane;
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
    public void exitGame() {
        new MenuController(new MenuPane(Main.getPrimaryStage().getWidth(), Main.getPrimaryStage().getHeight())).start();
    }

    @Override
    public void start() {
        Main.getPrimaryStage().setScene(new Scene((Parent) view));
    }
}
