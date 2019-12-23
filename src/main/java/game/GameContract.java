package game;

import menu.MenuContract;

public class GameContract {

    interface Controller {

        void choosePosition(int x, int y);

        void startGame();

        void pauseGame();

        void start();

    }

    interface View {
        void setController(MenuContract.Controller controller);

        void updateGameState(GameState gameState);
    }


}
