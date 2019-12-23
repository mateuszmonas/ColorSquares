package com.gmail.mateuszmonas.game;

import com.gmail.mateuszmonas.BaseController;
import com.gmail.mateuszmonas.BaseView;

public interface GameContract {

    interface Controller extends BaseController {

        void choosePosition(int x, int y);

        void startGame();

        void exitGame();

    }

    interface View extends BaseView<Controller> {

        void updateGameState(GameState gameState);

        void updateStartButton(boolean gameRunning);
    }


}
