package org.comicteam.helpers;

import javafx.stage.Screen;

public class MM {
    private static final double CONST = 25.4;
    private static final double DPI = Screen.getPrimary().getDpi();

    public static int toPx(int mm) {
        return (int) (mm * DPI / CONST);
    }

    public static int toMM(int px) {
        return (int) (px * CONST / DPI) + 1;
    }
}
