package com.gmail.mateuszmonas.menu;

import com.gmail.mateuszmonas.model.GameSettings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

import java.util.function.UnaryOperator;

public class SettingsPane extends VBox {

    MenuContract.Controller controller;

    TextField widthInput;
    TextField heightInput;
    TextField botCountInput;
    Button saveSettingsButton;

    public SettingsPane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);

        widthInput = new TextField();
        heightInput = new TextField();
        botCountInput = new TextField();
        saveSettingsButton = new Button("save settings");
        setAlignment(Pos.CENTER);

        widthInput.setMaxWidth(prefWidth);
        heightInput.setMaxWidth(prefWidth);
        botCountInput.setMaxWidth(prefWidth);

        UnaryOperator<TextFormatter.Change> filter = change -> change.getText().matches("[0-9]*") ? change : null;

        widthInput.setTextFormatter(new TextFormatter<>(filter));
        heightInput.setTextFormatter(new TextFormatter<>(filter));
        botCountInput.setTextFormatter(new TextFormatter<>(filter));

        saveSettingsButton.setOnMouseClicked(mouseEvent -> controller.changeSettings(new GameSettings(
                Integer.parseInt(widthInput.getText()),
                Integer.parseInt(heightInput.getText()),
                Integer.parseInt(botCountInput.getText()))
        ));

        getChildren().addAll(widthInput, heightInput, botCountInput, saveSettingsButton);

    }

    public void updateSettings(GameSettings gameSettings) {
        widthInput.setText(String.valueOf(gameSettings.getWidth()));
        heightInput.setText(String.valueOf(gameSettings.getHeight()));
        botCountInput.setText(String.valueOf(gameSettings.getBotCount()));
    }

    public void setController(MenuContract.Controller controller) {
        this.controller = controller;
    }
}
