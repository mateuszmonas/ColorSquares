package game;

import menu.MenuViewContract;
import model.Settings;

public class GameViewContract {

    interface Controller {

        void choosePosition(int x, int y);

        void startGame();

        void pauseGame();

    }

    interface View {
        void setController(MenuViewContract.Controller controller);

        void updateGameState(GameState gameState);
    }


}
