package com.gmail.mateuszmonas.game;

import com.gmail.mateuszmonas.menu.MenuController;
import com.gmail.mateuszmonas.menu.MenuPane;
import com.gmail.mateuszmonas.util.GuiUtil;

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
        new MenuController(new MenuPane(GuiUtil.getWidth(), GuiUtil.getHeight())).start();
    }

    @Override
    public void start() {
        GuiUtil.changeScene(view);
    }
}
