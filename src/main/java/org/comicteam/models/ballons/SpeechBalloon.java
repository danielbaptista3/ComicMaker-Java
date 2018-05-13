package org.comicteam.models.ballons;

import javafx.scene.canvas.Canvas;
import org.comicteam.layouts.ComicLayout;
import org.comicteam.models.Text;
import org.comicteam.models.ballons.pointers.BalloonPointer;

import java.io.Serializable;

public final class SpeechBalloon extends Balloon implements Serializable {
    public SpeechBalloon(Canvas canvas, ComicLayout layout, int background, Text text, BalloonPointer pointer) {
        super(canvas, layout, background, text, pointer);
    }
}
