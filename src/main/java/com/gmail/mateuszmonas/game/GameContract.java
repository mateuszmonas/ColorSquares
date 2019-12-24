package com.gmail.mateuszmonas.game;

import com.gmail.mateuszmonas.BaseController;
import com.gmail.mateuszmonas.BaseView;

public interface GameContract {

    interface Controller extends BaseController {

        void choosePosition(int x, int y);

        void startGame();

        void exitGame();

        void restartGame();
    }

    interface View extends BaseView<Controller> {

        void initialize(int boardWidth, int boardHeight);

        void updateBoardState(int[][] boardState);

        void updatePauseButton(boolean gameRunning);

        void showGameFinishedDialog(int playerScore, boolean playerWin);

    }


}
