import gui.Gui;
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import menu.MenuController;
import menu.MenuPane;

public class Main extends Application {

    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage stage) {
        Gui.createInstance(stage);

        primaryStage = stage;
        stage.setTitle("color game");
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setResizable(false);

        MenuPane root = new MenuPane(Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        MenuController menuController = new MenuController(root);


        menuController.start();
        stage.show();
    }


}
