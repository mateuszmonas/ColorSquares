package com.gmail.mateuszmonas.menu;

import com.gmail.mateuszmonas.model.GameSettings;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;


public class SettingsDialog extends Dialog<GameSettings> {

    SettingsPane settingsPane;

    public SettingsDialog() {

        settingsPane = new SettingsPane();
        setTitle("Game settings");
        setHeaderText("Settings");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        getDialogPane().setContent(settingsPane);

        setResultConverter(buttonType -> {
            if (buttonType == saveButtonType) {
                return settingsPane.getSettings();
            }
            return null;
        });
    }

    void setSettings(GameSettings gameSettings) {
        settingsPane.setSettings(gameSettings);
    }
}
