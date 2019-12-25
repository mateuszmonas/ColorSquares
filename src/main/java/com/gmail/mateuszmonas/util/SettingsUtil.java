package com.gmail.mateuszmonas.util;

import com.gmail.mateuszmonas.model.GameSettings;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class SettingsUtil {

    private static Gson gson = new Gson();
    private static String settingsPath = "settings.json";

    public static GameSettings getGameSettings() {
        GameSettings gameSettings;
        try {
            gameSettings = gson.fromJson(new FileReader(settingsPath), GameSettings.class);
        } catch (FileNotFoundException e) {
            System.err.println("could not read file " + settingsPath);
            gameSettings = new GameSettings(10, 10, 2, 10);
            setGameSettings(gameSettings);
        }
        return gameSettings;
    }

    public static void setGameSettings(GameSettings gameSettings) {
        if (gson == null) {
            gson = new Gson();
        }
        try {
            gson.toJson(gameSettings, new FileWriter(settingsPath));
        } catch (IOException e) {
            System.err.println("could not create file " + settingsPath);
        }
    }

}
