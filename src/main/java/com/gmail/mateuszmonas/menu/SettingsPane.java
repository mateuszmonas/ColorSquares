package com.gmail.mateuszmonas.menu;

import com.gmail.mateuszmonas.model.GameSettings;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

import java.util.function.UnaryOperator;

public class SettingsPane extends VBox {

    TextField widthInput;
    TextField heightInput;
    TextField botCountInput;
    TextField obstructionsCountInput;

    public SettingsPane() {
        widthInput = new TextField();
        heightInput = new TextField();
        botCountInput = new TextField();
        obstructionsCountInput = new TextField();
        setAlignment(Pos.CENTER);

        UnaryOperator<TextFormatter.Change> filter = change -> change.getText().matches("[0-9]*") ? change : null;

        widthInput.setTextFormatter(new TextFormatter<>(filter));
        widthInput.setPromptText("width");
        heightInput.setTextFormatter(new TextFormatter<>(filter));
        heightInput.setPromptText("height");
        botCountInput.setTextFormatter(new TextFormatter<>(filter));
        botCountInput.setPromptText("bot count");
        obstructionsCountInput.setTextFormatter(new TextFormatter<>(filter));
        obstructionsCountInput.setPromptText("obstructions count");

        getChildren().addAll(widthInput, heightInput, botCountInput, obstructionsCountInput);

    }

    public GameSettings getSettings() {
        return new GameSettings(
                Integer.parseInt(widthInput.getText()),
                Integer.parseInt(heightInput.getText()),
                Integer.parseInt(botCountInput.getText()),
                Integer.parseInt(obstructionsCountInput.getText()));
    }

    public void setSettings(GameSettings gameSettings) {
        widthInput.setText(String.valueOf(gameSettings.getWidth()));
        heightInput.setText(String.valueOf(gameSettings.getHeight()));
        botCountInput.setText(String.valueOf(gameSettings.getBotCount()));
        obstructionsCountInput.setText(String.valueOf(gameSettings.getObstructionsCount()));
    }
}
