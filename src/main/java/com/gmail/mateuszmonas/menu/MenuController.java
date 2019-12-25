package com.gmail.mateuszmonas.menu;

import com.gmail.mateuszmonas.game.GameController;
import com.gmail.mateuszmonas.game.view.GamePane;
import com.gmail.mateuszmonas.model.Game;
import com.gmail.mateuszmonas.model.GameSettings;
import com.gmail.mateuszmonas.util.GuiUtil;
import com.gmail.mateuszmonas.util.SettingsUtil;

public class MenuController implements MenuContract.Controller {

    final MenuContract.View view;
    GameSettings gameSettings;

    public MenuController(MenuContract.View view) {
        this.view = view;
        this.gameSettings = SettingsUtil.getGameSettings();
        view.setController(this);
        view.setSettings(gameSettings);
    }

    @Override
    public void exitApplication() {
        GuiUtil.closeApplication();
    }

    @Override
    public void startGame() {
        new GameController(new GamePane(GuiUtil.getWidth(), GuiUtil.getHeight()), new Game(gameSettings)).start();
    }

    @Override
    public void changeSettings(GameSettings gameSettings) {
        SettingsUtil.setGameSettings(gameSettings);
        this.gameSettings = gameSettings;
        view.setSettings(gameSettings);
    }

    @Override
    public void settingsButtonClicked() {
        view.showSettingsDialog();
    }

    @Override
    public void start() {
        GuiUtil.changeScene(view);
    }
}
