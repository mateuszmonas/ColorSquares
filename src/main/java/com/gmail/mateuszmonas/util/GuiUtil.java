package com.gmail.mateuszmonas.util;

import com.gmail.mateuszmonas.BaseView;
import com.gmail.mateuszmonas.Main;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.util.Iterator;

public final class GuiUtil {

    private static final Color[] COLORS = {
            Color.web("0xFFB300"),    // Vivid Yellow
            Color.web("0x803E75"),    // Strong Purple
            Color.web("0xFF6800"),    // Vivid Orange
            Color.web("0xA6BDD7"),    // Very Light Blue
            Color.web("0xC10020"),    // Vivid Red
            Color.web("0xCEA262"),    // Grayish Yellow
            Color.web("0x817066"),    // Medium Gray

            Color.web("0x007D34"),    // Vivid Green
            Color.web("0xF6768E"),    // Strong Purplish Pink
            Color.web("0x00538A"),    // Strong Blue
            Color.web("0xFF7A5C"),    // Strong Yellowish Pink
            Color.web("0x53377A"),    // Strong Violet
            Color.web("0xFF8E00"),    // Vivid Orange Yellow
            Color.web("0xB32851"),    // Strong Purplish Red
            Color.web("0xF4C800"),    // Vivid Greenish Yellow
            Color.web("0x7F180D"),    // Strong Reddish Brown
            Color.web("0x93AA00"),    // Vivid Yellowish Green
            Color.web("0x593315"),    // Deep Yellowish Brown
            Color.web("0xF13A13"),    // Vivid Reddish Orange
            Color.web("0x232C16"),    // Dark Olive Green
    };

    public static void changeScene(BaseView<?> view) {
        if (!(view instanceof Parent)) {
            throw new IllegalArgumentException("view must be subclass of javafx.scene.Parent");
        }
        Main.getPrimaryStage().setScene(new Scene((Parent) view));
    }

    public static double getWidth() {
        return Main.getPrimaryStage().getWidth();
    }


    public static double getHeight() {
        return Main.getPrimaryStage().getHeight();
    }

    public static void closeApplication() {
        Main.getPrimaryStage().close();
    }

    public static Color getColor(int i) {
        return COLORS[i];
    }

}
