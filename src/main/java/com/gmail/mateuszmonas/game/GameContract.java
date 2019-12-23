package com.gmail.mateuszmonas.game;

public interface GameContract {

    interface Controller {

        void choosePosition(int x, int y);

        void startGame();

        void exitGame();

        void start();

    }

    interface View {
        void setController(GameContract.Controller controller);

        void updateGameState(GameState gameState);

        void updateStartButton(boolean gameRunning);
    }


}
