package com.gmail.mateuszmonas.menu;

import com.gmail.mateuszmonas.game.GameController;
import com.gmail.mateuszmonas.game.view.GamePane;
import com.gmail.mateuszmonas.model.game.GameEngine;
import com.gmail.mateuszmonas.model.game.GameSettings;
import com.gmail.mateuszmonas.util.GuiUtil;
import com.gmail.mateuszmonas.util.SettingsUtil;

public class MenuController implements MenuContract.Controller {

    final MenuContract.View view;
    GameSettings gameSettings;

    public MenuController(MenuContract.View view) {
        this.view = view;
        this.gameSettings = SettingsUtil.readGameSettings();
        view.setController(this);
        view.setSettings(gameSettings);
    }

    @Override
    public void exitApplication() {
        GuiUtil.closeApplication();
    }

    @Override
    public void startGame() {
        new GameController(new GamePane(GuiUtil.getWidth(), GuiUtil.getHeight()), new GameEngine(gameSettings)).start();
    }

    @Override
    public void changeSettings(GameSettings gameSettings) {
        if(gameSettings.getBotCount()>18){
            view.showInvalidSettingsDialog("maximum bot count is 18");
            return;
        }
        if(gameSettings.getHeight()*gameSettings.getWidth()-1<gameSettings.getObstructionsCount()+gameSettings.getBotCount()){
            view.showInvalidSettingsDialog("bot and obstacle count can't be larger than map size");
            return;
        }
        SettingsUtil.saveGameSettings(gameSettings);
        this.gameSettings = gameSettings;
    }

    @Override
    public void settingsButtonClicked() {
        view.setSettings(gameSettings);
        view.showSettingsDialog();
    }

    @Override
    public void start() {
        GuiUtil.changeScene(view);
    }
}
