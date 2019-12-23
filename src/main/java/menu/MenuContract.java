package menu;

import model.Settings;

public interface MenuContract {

    interface Controller {

        void exitApplication();

        void startGame();

        void changeSettings(Settings settings);

    }

    interface View {
        void setController(Controller controller);
    }

}
