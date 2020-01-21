package com.gmail.mateuszmonas.menu.view;

import com.gmail.mateuszmonas.model.game.GameSettings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;

import java.util.function.UnaryOperator;

public class SettingsPane extends GridPane {

    TextField widthInput;
    TextField heightInput;
    TextField botCountInput;
    TextField obstructionsCountInput;

    public SettingsPane() {
        Label widthInputLabel = new Label("width");
        Label heightInputLabel = new Label("height");
        Label botCountInputLabel = new Label("bot count");
        Label obstructionsCountInputLabel = new Label("obstructions count");

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

        add(widthInputLabel, 0, 0);
        add(widthInput, 1, 0);
        add(heightInputLabel, 0, 1);
        add(heightInput, 1, 1);
        add(botCountInputLabel, 0, 2);
        add(botCountInput, 1, 2);
        add(obstructionsCountInputLabel, 0, 3);
        add(obstructionsCountInput, 1, 3);
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
