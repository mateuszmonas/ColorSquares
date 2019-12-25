package com.gmail.mateuszmonas.game;

import com.gmail.mateuszmonas.BaseController;
import com.gmail.mateuszmonas.BaseView;
import com.gmail.mateuszmonas.model.Field;

public interface GameContract {

    interface Controller extends BaseController {

        void choosePosition(int x, int y);

        void startGame();

        void pauseGame();

        void exitGame();

        void restartGame();
    }

    interface View extends BaseView<Controller> {

        void initialize(int boardWidth, int boardHeight);

        void updateBoardState(Field[][] boardState);

        void showGameFinishedDialog(int playerScore, boolean playerWin);

        void showStartButton();

        void showPauseButton();

        void showRestartButton();

        void enableStartButton();

    }


}
