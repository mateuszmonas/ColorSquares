package com.gmail.mateuszmonas.menu;


import com.gmail.mateuszmonas.BaseController;
import com.gmail.mateuszmonas.BaseView;
import com.gmail.mateuszmonas.model.GameSettings;

public interface MenuContract {

    interface Controller extends BaseController {

        void exitApplication();

        void startGame();

        void changeSettings(GameSettings gameSettings);

        void settingsButtonClicked();
    }

    interface View extends BaseView<Controller> {

        void setSettings(GameSettings gameSettings);

        void showSettingsDialog();

        void showInvalidSettingsDialog(String errorMessage);

    }

}
