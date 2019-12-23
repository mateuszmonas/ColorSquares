package game;

import gui.Gui;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GameController implements GameContract.Controller {

    GameContract.View view;

    public GameController(GameContract.View view) {
        this.view = view;
    }

    @Override
    public void choosePosition(int x, int y) {

    }

    @Override
    public void startGame() {

    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void start() {
        Gui.getInstance().getPrimaryStage().setScene(new Scene((Parent) view));
    }
}
