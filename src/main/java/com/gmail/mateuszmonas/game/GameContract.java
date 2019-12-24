package com.gmail.mateuszmonas.game;

import com.gmail.mateuszmonas.BaseController;
import com.gmail.mateuszmonas.BaseView;
import com.gmail.mateuszmonas.model.GameState;

public interface GameContract {

    interface Controller extends BaseController {

        void choosePosition(int x, int y);

        void startGame();

        void exitGame();

    }

    interface View extends BaseView<Controller> {

        void initialize(int boardWidth, int boardHeight);

        void updateBoardState(int[][] boardState);

        void updatePauseButton(boolean gameRunning);
    }


}
