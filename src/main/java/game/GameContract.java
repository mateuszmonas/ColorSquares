package game;

public class GameContract {

    interface Controller {

        void choosePosition(int x, int y);

        void startGame();

        void pauseGame();

        void start();

    }

    interface View {
        void setController(GameContract.Controller controller);

        void updateGameState(GameState gameState);
    }


}
