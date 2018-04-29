package org.comicteam.models;

import javafx.scene.canvas.Canvas;
import org.comicteam.layouts.ComicLayout;
import org.comicteam.models.ballons.Balloon;
import org.comicteam.models.ballons.pointers.BalloonPointer;

public final class Caption extends Balloon {
    public Caption(Canvas canvas, ComicLayout layout, int background, Text text, BalloonPointer pointer) {
        super(canvas, layout, background, text, pointer);
    }
}
