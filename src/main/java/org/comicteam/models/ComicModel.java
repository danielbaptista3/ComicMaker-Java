package org.comicteam.models;

import javafx.scene.canvas.Canvas;
import org.comicteam.layouts.ComicLayout;

public class ComicModel {
    protected Canvas canvas;
    protected ComicLayout layout;
    protected int background;

    public ComicModel(Canvas canvas, ComicLayout layout, int background) {
        this.canvas = canvas;
        this.layout = layout;
        this.background = background;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public ComicLayout getLayout() {
        return layout;
    }

    public void setLayout(ComicLayout layout) {
        this.layout = layout;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComicModel{");
        sb.append("canvas=").append(canvas);
        sb.append(", layout=").append(layout);
        sb.append(", background=").append(background);
        sb.append('}');
        return sb.toString();
    }
}
