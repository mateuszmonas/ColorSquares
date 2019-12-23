package com.gmail.mateuszmonas.menu;


import com.gmail.mateuszmonas.model.Settings;

public interface MenuContract {

    interface Controller {

        void exitApplication();

        void startGame();

        void changeSettings(Settings settings);

        void start();
    }

    interface View {
        void setController(MenuContract.Controller controller);
    }

}
