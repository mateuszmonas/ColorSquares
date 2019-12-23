package com.gmail.mateuszmonas.util;

import com.gmail.mateuszmonas.BaseView;
import com.gmail.mateuszmonas.Main;
import javafx.scene.Parent;
import javafx.scene.Scene;

public final class GuiUtil {

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

}
