package com.gmail.mateuszmonas.menu;


import com.gmail.mateuszmonas.BaseController;
import com.gmail.mateuszmonas.BaseView;
import com.gmail.mateuszmonas.model.GameSettings;

public interface MenuContract {

    interface Controller extends BaseController {

        void exitApplication();

        void startGame();

        void changeSettings(GameSettings gameSettings);
    }

    interface View extends BaseView<Controller> {
    }

}
