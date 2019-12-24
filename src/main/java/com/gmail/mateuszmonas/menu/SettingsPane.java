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
    TextField playerCountInput;
    Button saveSettingsButton;

    public SettingsPane(double prefWidth, double prefHeight) {
        setPrefWidth(prefWidth);
        setPrefHeight(prefHeight);

        widthInput = new TextField();
        heightInput = new TextField();
        playerCountInput = new TextField();
        saveSettingsButton = new Button("save settings");
        setAlignment(Pos.CENTER);

        widthInput.setMaxWidth(prefWidth);
        heightInput.setMaxWidth(prefWidth);
        playerCountInput.setMaxWidth(prefWidth);

        UnaryOperator<TextFormatter.Change> filter = change -> change.getText().matches("[0-9]*") ? change : null;

        widthInput.setTextFormatter(new TextFormatter<>(filter));
        heightInput.setTextFormatter(new TextFormatter<>(filter));
        playerCountInput.setTextFormatter(new TextFormatter<>(filter));

        saveSettingsButton.setOnMouseClicked(mouseEvent -> controller.changeSettings(new GameSettings(
                Integer.parseInt(widthInput.getText()),
                Integer.parseInt(heightInput.getText()),
                Integer.parseInt(playerCountInput.getText()))
        ));

        getChildren().addAll(widthInput, heightInput, playerCountInput, saveSettingsButton);

    }

    public void updateSettings(GameSettings gameSettings) {
        widthInput.setText(String.valueOf(gameSettings.getWidth()));
        heightInput.setText(String.valueOf(gameSettings.getHeight()));
        playerCountInput.setText(String.valueOf(gameSettings.getPlayerCount()));
    }

    public void setController(MenuContract.Controller controller) {
        this.controller = controller;
    }
}
