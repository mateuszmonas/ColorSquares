package menu;

import model.Settings;

public class MenuController implements MenuContract.Controller {

    final MenuContract.View view;

    public MenuController(MenuContract.View view) {
        this.view = view;
    }

    @Override
    public void exitApplication() {

    }

    @Override
    public void startGame() {

    }

    @Override
    public void changeSettings(Settings settings) {

    }

}
