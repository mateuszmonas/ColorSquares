package com.gmail.mateuszmonas.menu;

import com.gmail.mateuszmonas.game.GameController;
import com.gmail.mateuszmonas.game.GamePane;
import com.gmail.mateuszmonas.model.Settings;
import com.gmail.mateuszmonas.util.GuiUtil;

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
        new GameController(new GamePane(GuiUtil.getWidth(), GuiUtil.getHeight())).start();
    }

    @Override
    public void changeSettings(Settings settings) {

    }

    @Override
    public void start() {
        GuiUtil.changeScene(view);
    }
}
