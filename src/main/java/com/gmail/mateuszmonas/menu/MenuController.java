package com.gmail.mateuszmonas.menu;

import com.gmail.mateuszmonas.Main;
import com.gmail.mateuszmonas.game.GameController;
import com.gmail.mateuszmonas.game.GamePane;
import com.gmail.mateuszmonas.model.Settings;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MenuController implements MenuContract.Controller {

    final MenuContract.View view;

    public MenuController(MenuContract.View view) {
        this.view = view;
        view.setController(this);
    }

    @Override
    public void exitApplication() {

    }

    @Override
    public void startGame() {
        new GameController(new GamePane(Main.getPrimaryStage().getWidth(), Main.getPrimaryStage().getHeight())).start();
    }

    @Override
    public void changeSettings(Settings settings) {

    }

    @Override
    public void start() {
        Main.getPrimaryStage().setScene(new Scene((Parent) view));
    }
}
