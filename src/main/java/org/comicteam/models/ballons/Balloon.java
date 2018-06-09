package org.comicteam.models.ballons;

import javafx.scene.canvas.Canvas;
import org.comicteam.layouts.ComicLayout;
import org.comicteam.models.ComicModel;
import org.comicteam.models.Text;
import org.comicteam.models.ballons.pointers.BalloonPointer;

public abstract class Balloon extends ComicModel {
    protected Text text;
    protected BalloonPointer pointer;

    public Balloon(Canvas canvas, ComicLayout layout, int background, Text text, BalloonPointer pointer) {
        super(text.getText(), canvas, layout, background);
        this.text = text;
        this.pointer = pointer;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public BalloonPointer getPointer() {
        return pointer;
    }

    public void setPointer(BalloonPointer pointer) {
        this.pointer = pointer;
    }
}
