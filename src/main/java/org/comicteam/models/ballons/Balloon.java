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
        super(canvas, layout, background);
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Balloon{");
        sb.append("canvas=").append(canvas);
        sb.append(", layout=").append(layout);
        sb.append(", background=").append(background);
        sb.append(", text=").append(text);
        sb.append(", pointer=").append(pointer);
        sb.append('}');
        return sb.toString();
    }
}
