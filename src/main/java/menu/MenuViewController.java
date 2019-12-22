package menu;

import model.Settings;

public class MenuViewController implements MenuViewContract.Controller {

    final MenuViewContract.View view;

    public MenuViewController(MenuViewContract.View view) {
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
