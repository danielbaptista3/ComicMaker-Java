package org.comicteam.models.ballons;

import javafx.scene.canvas.Canvas;
import org.comicteam.layouts.ComicLayout;
import org.comicteam.models.Text;
import org.comicteam.models.ballons.pointers.BalloonPointer;

public final class ThoughtBalloon extends Balloon {
    public ThoughtBalloon(Canvas canvas, ComicLayout layout, int background, Text text, BalloonPointer pointer) {
        super(canvas, layout, background, text, pointer);
    }
}
