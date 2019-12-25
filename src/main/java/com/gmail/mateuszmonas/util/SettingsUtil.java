package com.gmail.mateuszmonas.util;

import com.gmail.mateuszmonas.model.GameSettings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class SettingsUtil {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static String settingsPath = "settings.json";

    public static GameSettings readGameSettings() {
        GameSettings gameSettings;
        try {
            gameSettings = gson.fromJson(new FileReader(settingsPath), GameSettings.class);
        } catch (FileNotFoundException e) {
            System.err.println("could not read file " + settingsPath);
            gameSettings = new GameSettings(10, 10, 2, 10);
            saveGameSettings(gameSettings);
        }
        return gameSettings;
    }

    public static void saveGameSettings(GameSettings gameSettings) {
        try {
            FileWriter writer = new FileWriter(settingsPath);
            gson.toJson(gameSettings, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.err.println("could not create file " + settingsPath);
        }
    }

}
