package menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import model.Settings;

import java.util.function.UnaryOperator;

public class SettingsViewPane extends HBox {

    MenuViewContract.Controller controller;

    public SettingsViewPane() {
        TextField widthInput = new TextField();
        TextField heightInput = new TextField();
        TextField playerCountInput = new TextField();
        Button saveSettingsButton = new Button("save");
        setAlignment(Pos.CENTER);

        UnaryOperator<TextFormatter.Change> filter = change -> change.getText().matches("[0-9]*") ? change : null;

        widthInput.setTextFormatter(new TextFormatter<>(filter));
        heightInput.setTextFormatter(new TextFormatter<>(filter));
        playerCountInput.setTextFormatter(new TextFormatter<>(filter));

        saveSettingsButton.setOnMouseClicked(mouseEvent -> controller.changeSettings(new Settings(
                Integer.parseInt(widthInput.getText()),
                Integer.parseInt(heightInput.getText()),
                Integer.parseInt(playerCountInput.getText()))
        ));

        getChildren().addAll(widthInput, heightInput, playerCountInput, saveSettingsButton);

    }

    public void setController(MenuViewContract.Controller controller) {
        this.controller = controller;
    }
}
