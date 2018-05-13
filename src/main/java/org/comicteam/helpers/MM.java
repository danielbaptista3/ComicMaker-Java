package org.comicteam.helpers;

import javafx.stage.Screen;

public class MM {
    public static int DPI = 96;

    public static int toPx(int mm) {

        return (int)((mm * Screen.getPrimary().getDpi()) / 25.4);
    }
}
