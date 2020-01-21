package com.gmail.mateuszmonas.game;

import com.gmail.mateuszmonas.BaseController;
import com.gmail.mateuszmonas.BaseView;
import com.gmail.mateuszmonas.model.field.Field;
import com.gmail.mateuszmonas.model.Player;

import java.util.Set;

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

        void showGameFinishedDialog(Set<Player> player);

        void showStartButton();

        void showPauseButton();

        void showRestartButton();

        void enableStartButton();

    }


}
